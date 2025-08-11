package com.newspaper.aiservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleEmbeddingResponse {
    String articleId;
    int chunksCount;
}
