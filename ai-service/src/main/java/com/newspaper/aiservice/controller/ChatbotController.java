package com.newspaper.aiservice.controller;

import com.newspaper.aiservice.dto.ApiResponse;
import com.newspaper.aiservice.dto.request.ChatRequest;
import com.newspaper.aiservice.dto.request.EmbeddingRequest;
import com.newspaper.aiservice.dto.response.ChatResponse;
import com.newspaper.aiservice.dto.response.EmbeddingDeleteResponse;
import com.newspaper.aiservice.dto.response.EmbeddingResponse;
import com.newspaper.aiservice.service.ChatbotService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ChatbotController {
    ChatbotService chatbotService;

    @PostMapping("/embedding")
    ApiResponse<EmbeddingResponse> embeddingArticle(@RequestBody EmbeddingRequest request) {
        return ApiResponse.<EmbeddingResponse>builder()
                .result(chatbotService.embeddingArticle(request))
                .build();
    }

    @PostMapping("/ask")
    ApiResponse<ChatResponse> askArticle(@RequestBody ChatRequest request) {
        return ApiResponse.<ChatResponse>builder()
                .result(chatbotService.askArticle(request))
                .build();
    }

    @DeleteMapping("/embedding/{articleId}")
    ApiResponse<EmbeddingDeleteResponse> deleteArticleEmbedding(@PathVariable("articleId") String articleId) {
        return ApiResponse.<EmbeddingDeleteResponse>builder()
                .result(chatbotService.deleteArticleEmbedding(articleId))
                .build();
    }
}
