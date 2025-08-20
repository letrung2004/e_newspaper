package com.newspaper.aiservice.service;

import com.newspaper.aiservice.dto.request.ChatRequest;
import com.newspaper.aiservice.dto.request.EmbeddingRequest;
import com.newspaper.aiservice.dto.response.ChatResponse;
import com.newspaper.aiservice.dto.response.EmbeddingDeleteResponse;
import com.newspaper.aiservice.dto.response.EmbeddingResponse;
import com.newspaper.aiservice.repository.httpclient.RagChatbotClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatbotService {
    RagChatbotClient ragChatbotClient;

    private String removeHtmlTags(String text) {
        return Jsoup.parse(text).text().replaceAll("\\s+", " ").trim();
    }

    public EmbeddingResponse embeddingArticle(EmbeddingRequest request) {
        String cleanContent = removeHtmlTags(request.getContent());
        request.setContent(cleanContent);
        return ragChatbotClient.embeddingArticle(request);
    }

    public ChatResponse askArticle(ChatRequest request) {
        return ragChatbotClient.askArticle(request);
    }

    public EmbeddingDeleteResponse deleteArticleEmbedding(String articleId){
        return ragChatbotClient.deleteArticle(articleId);
    }
}
