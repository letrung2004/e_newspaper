package com.newspaper.contentservice.service;

import com.newspaper.contentservice.dto.PageResponse;
import com.newspaper.contentservice.dto.request.ArticleCreateRequest;
import com.newspaper.contentservice.dto.response.ArticleResponse;
import com.newspaper.contentservice.entity.Article;
import com.newspaper.contentservice.entity.Category;
import com.newspaper.contentservice.entity.Tag;
import com.newspaper.contentservice.exception.AppException;
import com.newspaper.contentservice.exception.ErrorCode;
import com.newspaper.contentservice.mapper.ArticleMapper;
import com.newspaper.contentservice.repository.ArticleRepository;
import com.newspaper.contentservice.repository.CategoryRepository;
import com.newspaper.contentservice.repository.TagRepository;
import com.newspaper.contentservice.repository.httpClient.AiClient;
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
    AiClient aiClient;
    SlugService slugService;
    DateTimeFormatter formatter;

    @PreAuthorize("hasRole('ADMIN') or hasRole('EDITOR')")
    public ArticleResponse createArticle(ArticleCreateRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String slug = slugService.generateArticleSlug(request.getTitle());

        Category category = categoryRepository.findById(request.getCategory())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        Set<Tag> tags = request.getTags().stream()
                .map(tagId -> tagRepository.findById(tagId)
                        .orElseThrow(() -> new AppException(ErrorCode.TAG_NOT_FOUND)))
                .collect(Collectors.toSet());
        // gọi ai-service chuyển audio và tóm tắc(gọi qua open-feign)

//        String summary = aiClient.createSummary(request.getContent()).getResult().getSummary();
//        String audioUrl = aiClient.createTTS(request.getContent()).getResult().getAudioUrl();
//        log.info("Summary from ai-service: {}", summary);
//        log.info("Url audio from ai-service: {}", audioUrl);
//         tạm tắc để test
        String summary = "off ai-service";
        String audioUrl = "off ai-service";

        Article article = Article.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .userId(authentication.getName())
                .slug(slug)
                .featuredImage(request.getFeaturedImage())
                .category(category)
                .tags(tags)
                .authors(request.getAuthors())
                .publishDate(Instant.now())
                .audioUrl(audioUrl)
                .embedding(null)
                .summary(summary)
                .build();

        Article savedArticle = articleRepository.save(article);

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
//        return articleRepository.findAll().stream()
//                .map(articleMapper::toArticleResponse)
//                .collect(Collectors.toList());
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
        return articleMapper.toArticleResponse(article);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('EDITOR')")
    public void deleteArticleById(String id) {
        articleRepository.deleteById(id);
    }

    //get all article by category
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

    //update article
    @PreAuthorize("hasRole('ADMIN') or hasRole('EDITOR')")
    public ArticleResponse updateArticle(String articleId, ArticleCreateRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new AppException(ErrorCode.ARTICLE_NOT_FOUND));


        if (request.getTitle() != null && !request.getTitle().equals(article.getTitle())) {
            article.setTitle(request.getTitle());
            article.setSlug(slugService.generateArticleSlug(request.getTitle()));
        }

        if (request.getContent() != null && !request.getContent().equals(article.getContent())) {
            try {
                article.setContent(request.getContent());
//                String summary = aiClient.createSummary(request.getContent()).getResult().getSummary();
//                String audioUrl = aiClient.createTTS(request.getContent()).getResult().getAudioUrl();
                String summary = "off ai-service-check";
                String audioUrl = "off ai-service-check";

                article.setSummary(summary);
                article.setAudioUrl(audioUrl);
            } catch (Exception e) {
                log.error("Loi ai-service: {}", articleId, e);
                throw new AppException(ErrorCode.AI_SERVICE_ERROR);
            }

        }

        if (request.getFeaturedImage() != null && !request.getFeaturedImage().equals(article.getFeaturedImage())) {
            article.setFeaturedImage(request.getFeaturedImage());
        }

        if (!request.getCategory().equals(article.getCategory().getId())) {
            Category category = categoryRepository.findById(request.getCategory())
                    .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
            article.setCategory(category);
        }

        Set<Tag> newTags = request.getTags().stream()
                .map(tagId -> tagRepository.findById(tagId)
                        .orElseThrow(() -> new AppException(ErrorCode.TAG_NOT_FOUND)))
                .collect(Collectors.toSet());
        if (!newTags.equals(article.getTags())) {
            article.setTags(newTags);
        }

        if (!request.getAuthors().equals(article.getAuthors())) {
            article.setAuthors(request.getAuthors());
        }
        article.setUserId(authentication.getName());
        article = articleRepository.save(article);


        return articleMapper.toArticleResponse(article);
    }

}
