package com.newspaper.aiservice.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Getter
@Setter
@Builder
@Document(value = "article_embeddings")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ArticleEmbedding {
    @MongoId
    String id;

    @Indexed
    String articleId;

    List<Chunk> chunks;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Chunk {
        int chunkIndex;
        String chunkContent;
        List<Float> embeddingVector;
    }
}
