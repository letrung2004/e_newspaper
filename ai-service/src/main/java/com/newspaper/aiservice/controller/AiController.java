package com.newspaper.aiservice.controller;

import com.newspaper.aiservice.dto.ApiResponse;
import com.newspaper.aiservice.dto.response.SummarizationResponse;
import com.newspaper.aiservice.dto.response.TextToSpeechResponse;
import com.newspaper.aiservice.service.AiService;
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

}
