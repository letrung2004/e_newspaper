package com.newspaper.aiservice.repository;

import com.newspaper.aiservice.entity.ArticleEmbedding;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleEmbeddingRepository extends MongoRepository<ArticleEmbedding, String> {
    List<ArticleEmbedding> findByArticleId(String articleId);
    void deleteByArticleId(String articleId);
}
