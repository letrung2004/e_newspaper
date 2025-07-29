package com.newspaper.contentservice.repository.httpClient;

import com.newspaper.contentservice.configuration.AuthenticationRequestInterceptor;
import com.newspaper.contentservice.dto.ApiResponse;
import com.newspaper.contentservice.dto.response.SummarizationResponse;
import com.newspaper.contentservice.dto.response.TextToSpeechResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ai-service"
        , url = "http://localhost:8084/ai"
        , configuration = {AuthenticationRequestInterceptor.class})
public interface AiClient {
    @PostMapping(value = "/summary", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<SummarizationResponse> createSummary(@RequestBody String content);

    @PostMapping(value = "/tts", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<TextToSpeechResponse> createTTS(@RequestBody String content);
}
