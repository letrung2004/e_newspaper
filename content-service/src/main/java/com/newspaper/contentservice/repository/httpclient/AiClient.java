package com.newspaper.contentservice.repository.httpclient;

import com.newspaper.contentservice.configuration.AuthenticationRequestInterceptor;
import com.newspaper.contentservice.dto.ApiResponse;
import com.newspaper.contentservice.dto.response.EmbeddingDeleteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ai-service",
        url = "${app.services.ai.url}",
        configuration = {AuthenticationRequestInterceptor.class}
)
public interface AiClient {
    @DeleteMapping("/embedding/{articleId}")
    ApiResponse<EmbeddingDeleteResponse> deleteArticleEmbedding(@PathVariable("articleId") String articleId);
}
