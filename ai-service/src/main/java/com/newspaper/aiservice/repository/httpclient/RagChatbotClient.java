package com.newspaper.aiservice.repository.httpclient;

import com.newspaper.aiservice.dto.request.ChatRequest;
import com.newspaper.aiservice.dto.request.EmbeddingRequest;
import com.newspaper.aiservice.dto.response.ChatResponse;
import com.newspaper.aiservice.dto.response.EmbeddingDeleteResponse;
import com.newspaper.aiservice.dto.response.EmbeddingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "rag-chatbot-client"
        ,url = "http://localhost:5000/api")
public interface RagChatbotClient {
    @PostMapping("/embedding")
    EmbeddingResponse embeddingArticle(@RequestBody EmbeddingRequest request);

    @GetMapping("/ask")
    ChatResponse askArticle(@RequestBody ChatRequest request);

    @DeleteMapping("/embedding/{articleId}")
    EmbeddingDeleteResponse deleteArticle(@PathVariable String articleId);
}
