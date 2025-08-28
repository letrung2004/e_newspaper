package com.newspaper.searchservice.repository;

import com.newspaper.searchservice.entity.ArticleDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleSearchRepository extends ElasticsearchRepository<ArticleDocument, String> {
}
