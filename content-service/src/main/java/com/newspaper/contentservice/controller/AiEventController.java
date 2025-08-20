package com.newspaper.contentservice.controller;

import com.newspaper.contentservice.entity.Article;
import com.newspaper.contentservice.exception.AppException;
import com.newspaper.contentservice.exception.ErrorCode;
import com.newspaper.contentservice.repository.ArticleRepository;
import com.newspaper.event.dto.ArticleAiProcessedEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Component
@RequiredArgsConstructor
public class AiEventController {
    ArticleRepository articleRepository;

    @KafkaListener(topics = "article-ai-processed")
    public void listenArticleAiProcessed(ArticleAiProcessedEvent event) {
        log.info("Received ArticleAiProcessedEvent: {}", event);

        Article article = articleRepository.findById(event.getArticleId())
                .orElseThrow(() -> new AppException(ErrorCode.ARTICLE_NOT_FOUND));

        article.setSummary(event.getSummary());
        article.setAudioUrl(event.getAudioUrl());
        article.setEmbedding(event.getEmbeddingArticle());

        articleRepository.save(article);
    }
}
