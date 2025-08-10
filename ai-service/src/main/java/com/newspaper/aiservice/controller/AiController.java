package com.newspaper.aiservice.controller;

import com.newspaper.aiservice.dto.ApiResponse;
import com.newspaper.aiservice.dto.request.ArticleEmbeddingRequest;
import com.newspaper.aiservice.dto.response.ArticleEmbeddingResponse;
import com.newspaper.aiservice.dto.response.SummarizationResponse;
import com.newspaper.aiservice.dto.response.TextToSpeechResponse;
import com.newspaper.aiservice.service.AiService;
import com.newspaper.aiservice.service.EmbeddingService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AiController {
    AiService aiService;
    EmbeddingService embeddingService;

    @PostMapping("/summary")
    ApiResponse<SummarizationResponse> summaryArticle(@RequestBody String content) {
        return ApiResponse.<SummarizationResponse>builder()
                .result(aiService.summarize(content))
                .build();
    }

    @PostMapping("/tts")
    ApiResponse<TextToSpeechResponse> textToSpeechArticle(@RequestBody String content) {
        return ApiResponse.<TextToSpeechResponse>builder()
                .result(aiService.convertTextToSpeech(content))
                .build();
    }

    @PostMapping("/embeddings")
    ApiResponse<ArticleEmbeddingResponse> embeddingArticle(@RequestBody ArticleEmbeddingRequest request) {
        return ApiResponse.<ArticleEmbeddingResponse>builder()
                .result(embeddingService.createArticleEmbedding(request.getArticleId() ,request.getContent()))
                .build();
    }

}
