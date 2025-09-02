package com.newspaper.contentservice.repository.httpclient;

import com.newspaper.contentservice.configuration.AuthenticationRequestInterceptor;
import com.newspaper.contentservice.dto.ApiResponse;
import com.newspaper.contentservice.dto.request.EmbeddingRequest;
import com.newspaper.contentservice.dto.response.EmbeddingDeleteResponse;
import com.newspaper.contentservice.dto.response.EmbeddingResponse;
import com.newspaper.contentservice.dto.response.SummarizationResponse;
import com.newspaper.contentservice.dto.response.TextToSpeechResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ai-service",
        url = "${app.services.ai.url}",
        configuration = {AuthenticationRequestInterceptor.class}
)
public interface AiClient {
    @DeleteMapping("/embedding/{articleId}")
    ApiResponse<EmbeddingDeleteResponse> deleteArticleEmbedding(@PathVariable("articleId") String articleId);

    @PostMapping(value = "/summary", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<SummarizationResponse> createSummary(@RequestBody String content);

    @PostMapping(value = "/tts", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<TextToSpeechResponse> createTTS(@RequestBody String content);

    @PostMapping(value = "/embedding", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<EmbeddingResponse> createEmbedding(@RequestBody EmbeddingRequest request);
}
