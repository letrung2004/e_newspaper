package com.newspaper.contentservice.service;

import com.newspaper.contentservice.entity.Article;
import com.newspaper.event.dto.ArticleCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ArticleEventProducer {
    private final KafkaTemplate<String, ArticleCreatedEvent> kafkaTemplate;
    private static final String TOPIC_ARTICLE_CONTENT_CHANGED = "create-article";

    public void sendArticleContentChangedEvent(Article article) {
        ArticleCreatedEvent event = ArticleCreatedEvent.builder()
                .articleId(article.getId())
                .content(article.getContent())
                .build();
        try {
            kafkaTemplate.send(TOPIC_ARTICLE_CONTENT_CHANGED, event);
            log.info("Article {} send message to ai-service", article.getId());
        } catch (Exception e) {
            log.error("failed to send message to ai-service {}: {}", article.getId(), e.getMessage(), e);
        }
    }
}
