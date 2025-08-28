package com.newspaper.contentservice.controller;

import com.newspaper.contentservice.dto.response.ArticleResponse;
import com.newspaper.contentservice.entity.Article;
import com.newspaper.contentservice.exception.AppException;
import com.newspaper.contentservice.exception.ErrorCode;
import com.newspaper.contentservice.mapper.ArticleEventMapper;
import com.newspaper.contentservice.repository.ArticleRepository;
import com.newspaper.contentservice.service.ArticleService;
import com.newspaper.event.dto.ArticleAiProcessedEvent;
import com.newspaper.event.dto.ArticleCreatedForSearchEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Component
@RequiredArgsConstructor
public class AiEventController {
    ArticleRepository articleRepository;
    KafkaTemplate<String, ArticleCreatedForSearchEvent> kafkaTemplateSearchEvent;
    ArticleEventMapper articleEventMapper;

    @Transactional
    @KafkaListener(topics = "article-ai-processed")
    public void listenArticleAiProcessed(ArticleAiProcessedEvent event) {
        try {
            log.info("Received ArticleAiProcessedEvent: {}", event);

            Article article = articleRepository.findById(event.getArticleId())
                    .orElseThrow(() -> new AppException(ErrorCode.ARTICLE_NOT_FOUND));

            article.setSummary(event.getSummary());
            article.setAudioUrl(event.getAudioUrl());
            article.setEmbedding(event.getEmbeddingArticle());

            Article updated = articleRepository.save(article);

            // gửi event sang search-service
            ArticleCreatedForSearchEvent searchEvent = articleEventMapper.toSearchEvent(updated);
            kafkaTemplateSearchEvent.send("create-search-article", searchEvent);

            log.info("Article {} updated and sent to search-service", updated.getId());
        } catch (Exception e) {
            log.error("Error processing ArticleAiProcessedEvent: {}", event, e);
        }
    }

}
