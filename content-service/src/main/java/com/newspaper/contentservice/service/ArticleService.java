package com.newspaper.contentservice.service;

import com.newspaper.contentservice.dto.ApiResponse;
import com.newspaper.contentservice.dto.request.ArticleCreateRequest;
import com.newspaper.contentservice.dto.response.ArticleResponse;
import com.newspaper.contentservice.dto.response.SummarizationResponse;
import com.newspaper.contentservice.dto.response.TextToSpeechResponse;
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
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ArticleService {
    ArticleRepository articleRepository;
    TagService tagService;
    CategoryRepository categoryRepository;
    TagRepository tagRepository;
    ArticleMapper articleMapper;
    AiClient aiClient;

    public ArticleResponse createArticle(ArticleCreateRequest request) {
        String slug = tagService.generateSlug(request.getTitle());

        Category category = categoryRepository.findById(request.getCategory())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        Set<Tag> tags = request.getTags().stream()
                .map(tagId -> tagRepository.findById(tagId)
                        .orElseThrow(() -> new IllegalArgumentException("Tag ID không hợp lệ: " + tagId)))
                .collect(Collectors.toSet());
        // gọi ai-service chuyển audio và tóm tắc(gọi qua open-feign)
        String summary = aiClient.createSummary(request.getContent()).getResult().getSummary();
        String audioUrl = aiClient.createTTS(request.getContent()).getResult().getAudioUrl();
        log.info("Summary from ai-service: {}", summary);
        log.info("Url audio from ai-service: {}", audioUrl);

        Article article = Article.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .slug(slug)
                .featuredImage(request.getFeaturedImage())
                .category(category)
                .tags(tags)
                .authors(request.getAuthors())
                .publishDate(LocalDate.now())
                .audioUrl(audioUrl)
                .embedding(null)
                .summary(summary)
                .build();

        Article savedArticle = articleRepository.save(article);

        return articleMapper.toArticleResponse(savedArticle);
    }
}
