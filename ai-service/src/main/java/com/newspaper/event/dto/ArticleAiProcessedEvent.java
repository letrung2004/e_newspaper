package com.newspaper.event.dto;

import com.newspaper.aiservice.dto.response.EmbeddingResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleAiProcessedEvent {
    String articleId;
    String summary;
    String audioUrl;
    String embeddingArticle;
}
