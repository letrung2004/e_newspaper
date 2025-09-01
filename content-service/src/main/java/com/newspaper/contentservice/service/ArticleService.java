package com.newspaper.contentservice.service;

import com.newspaper.contentservice.dto.PageResponse;
import com.newspaper.contentservice.dto.request.ArticleCreateRequest;
import com.newspaper.contentservice.dto.response.ArticleResponse;
import com.newspaper.contentservice.entity.Article;
import com.newspaper.contentservice.entity.ArticleStatus;
import com.newspaper.contentservice.entity.Category;
import com.newspaper.contentservice.entity.Tag;
import com.newspaper.contentservice.exception.AppException;
import com.newspaper.contentservice.exception.ErrorCode;
import com.newspaper.contentservice.mapper.ArticleMapper;
import com.newspaper.contentservice.repository.ArticleRepository;
import com.newspaper.contentservice.repository.CategoryRepository;
import com.newspaper.contentservice.repository.TagRepository;

import com.newspaper.contentservice.repository.httpclient.AiClient;
import com.newspaper.contentservice.repository.httpclient.CommentClient;
import com.newspaper.contentservice.repository.httpclient.SearchClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ArticleService {
    ArticleRepository articleRepository;
    CategoryRepository categoryRepository;
    TagRepository tagRepository;
    ArticleMapper articleMapper;
    SlugService slugService;
    DateTimeFormatter formatter;
    ArticleEventProducer articleEventProducer;
    CommentClient commentClient;
    AiClient aiClient;
    SearchClient searchClient;


    private Category resolveCategory(String categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
    }

    private Set<Tag> resolveTags(Set<String> tagIds) {
        return tagIds.stream()
                .map(tagId -> tagRepository.findById(tagId)
                        .orElseThrow(() -> new AppException(ErrorCode.TAG_NOT_FOUND)))
                .collect(Collectors.toSet());
    }



    @PreAuthorize("hasRole('ADMIN') or hasRole('EDITOR')")
    public ArticleResponse createArticle(ArticleCreateRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Article article = articleMapper.toArticle(request);
        article.setSlug(slugService.generateArticleSlug(request.getTitle()));
        article.setUserId(authentication.getName());
        article.setPublishDate(Instant.now());
        article.setCategory(resolveCategory(request.getCategory()));
        article.setTags(resolveTags(request.getTags()));
        article.setStatus(ArticleStatus.PENDING);

        Article savedArticle = articleRepository.save(article);
        articleEventProducer.sendArticleContentChangedEvent(savedArticle);

        return articleMapper.toArticleResponse(savedArticle);
    }

    // ai cũng có the lay xem danh sach bao ke da da dang nhap hay chua dang nhap
    public PageResponse<ArticleResponse> getAllArticles(int page, int size) {
        Sort sort = Sort.by("publishDate").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        var pageData = articleRepository.findAllBy(pageable);
        var articleList = pageData.getContent().stream()
                .map(article -> {
                    var articleResponse = articleMapper.toArticleResponse(article);
                    articleResponse.setCreated(formatter.format(article.getPublishDate()));
                    return articleResponse;
                }).toList();

        return PageResponse.<ArticleResponse>builder()
                .currentPage(page)
                .pageSize(size)
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .data(articleList)
                .build();
    }

    public ArticleResponse getArticleById(String id) {
        return articleMapper.toArticleResponse(
                articleRepository.findById(id)
                        .orElseThrow(() -> new AppException(ErrorCode.ARTICLE_NOT_FOUND))
        );
    }

    public ArticleResponse getArticleBySlug(String slug) {
        Article article = articleRepository.findBySlug(slug);
        if (article == null) {
            throw new AppException(ErrorCode.ARTICLE_NOT_FOUND);
        }
        var articleResponse = articleMapper.toArticleResponse(article);
        articleResponse.setCreated(formatter.format(article.getPublishDate()));
        return articleResponse;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('EDITOR')")
    public void deleteArticleById(String id) {
        articleRepository.deleteById(id);
        commentClient.deleteByArticleId(id);
        aiClient.deleteArticleEmbedding(id);
        searchClient.deleteByArticleId(id);
    }

    public PageResponse<ArticleResponse> getAllArticlesByCategorySlug(String categorySlug, int page, int size) {
        Sort sort = Sort.by("publishDate").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        var pageData = articleRepository.findAllByCategory_Slug(categorySlug, pageable);

        var articleList = pageData.getContent().stream()
                .map(article -> {
                    var articleResponse = articleMapper.toArticleResponse(article);
                    articleResponse.setCreated(formatter.format(article.getPublishDate()));
                    return articleResponse;
                }).toList();

        return PageResponse.<ArticleResponse>builder()
                .currentPage(page)
                .pageSize(size)
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .data(articleList)
                .build();
    }


    @PreAuthorize("hasRole('ADMIN') or hasRole('EDITOR')")
    public ArticleResponse updateArticle(String articleId, ArticleCreateRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new AppException(ErrorCode.ARTICLE_NOT_FOUND));

        boolean contentChanged = false;

        if (request.getTitle() != null && !request.getTitle().equals(article.getTitle())) {
            article.setTitle(request.getTitle());
            article.setSlug(slugService.generateArticleSlug(request.getTitle()));
        }
        if (request.getContent() != null && !request.getContent().equals(article.getContent())) {
            article.setContent(request.getContent());
            contentChanged = true;
        }
        if (request.getFeaturedImage() != null && !request.getFeaturedImage().equals(article.getFeaturedImage())) {
            article.setFeaturedImage(request.getFeaturedImage());
        }
        if (!request.getCategory().equals(article.getCategory().getId())) {
            article.setCategory(resolveCategory(request.getCategory()));
        }
        Set<Tag> newTags = resolveTags(request.getTags());
        if (!newTags.equals(article.getTags())) {
            article.setTags(newTags);
        }
        if (!request.getAuthors().equals(article.getAuthors())) {
            article.setAuthors(request.getAuthors());
        }

        article.setUserId(authentication.getName());
        Article savedArticle = articleRepository.save(article);

        if (contentChanged) {
            articleEventProducer.sendArticleContentChangedEvent(savedArticle);
        }

        return articleMapper.toArticleResponse(savedArticle);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('EDITOR')")
    public ArticleResponse updateStatus(String articleId, ArticleStatus newStatus) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new AppException(ErrorCode.ARTICLE_NOT_FOUND));
        article.setStatus(newStatus);
        article = articleRepository.save(article);

        return articleMapper.toArticleResponse(article);
    }


}
