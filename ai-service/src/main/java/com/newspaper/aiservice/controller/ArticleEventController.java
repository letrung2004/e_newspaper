package com.newspaper.aiservice.controller;

import com.newspaper.aiservice.dto.request.EmbeddingRequest;
import com.newspaper.aiservice.dto.response.EmbeddingResponse;
import com.newspaper.aiservice.dto.response.SummarizationResponse;
import com.newspaper.aiservice.dto.response.TextToSpeechResponse;
import com.newspaper.aiservice.service.AiService;
import com.newspaper.aiservice.service.ChatbotService;
import com.newspaper.event.dto.ArticleAiProcessedEvent;
import com.newspaper.event.dto.ArticleCreatedEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Component
@RequiredArgsConstructor
public class ArticleEventController {
    ChatbotService chatbotService;
    AiService aiService;
    private final KafkaTemplate<String, ArticleAiProcessedEvent> kafkaTemplate;

    @KafkaListener(topics = "create-article")
    public void listenArticleCreated(ArticleCreatedEvent event) {
        log.info("received article created event: {}", event);
        EmbeddingRequest request = EmbeddingRequest.builder()
                .articleId(event.getArticleId())
                .content(event.getContent())
                .build();
        String content = event.getContent();

        EmbeddingResponse responseArticleEmbedding = chatbotService.embeddingArticle(request);
        SummarizationResponse summaryArticle = aiService.summarize(content);
        TextToSpeechResponse audioUrlArticle = aiService.convertTextToSpeech(content);

        ArticleAiProcessedEvent articleAiProcessedEvent = ArticleAiProcessedEvent.builder()
                .articleId(event.getArticleId())
                .audioUrl(audioUrlArticle.getAudioUrl())
                .summary(summaryArticle.getSummary())
                .embeddingArticle(String.valueOf(responseArticleEmbedding.getChunks()))
                .build();
        kafkaTemplate.send("article-ai-processed", articleAiProcessedEvent);
    }
}
