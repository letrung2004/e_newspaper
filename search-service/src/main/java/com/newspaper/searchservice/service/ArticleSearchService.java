package com.newspaper.searchservice.service;

import com.newspaper.searchservice.dto.response.ArticleSearchResponse;
import com.newspaper.searchservice.entity.ArticleDocument;
import com.newspaper.searchservice.mapper.ArticleMapper;
import com.newspaper.searchservice.repository.ArticleSearchRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ArticleSearchService {
    ArticleSearchRepository articleSearchRepository;
    ElasticsearchClient elasticsearchClient;
    private final ArticleMapper articleMapper;


    public ArticleDocument saveArticleDocument(ArticleDocument article) {
        return articleSearchRepository.save(article);
    }

    public Iterable<ArticleDocument> getArticleDocuments() {
        return articleSearchRepository.findAll();
    }

    public List<ArticleSearchResponse> searchByKeyword(String keyword) throws IOException {
        Query query = MultiMatchQuery.of(m -> m
                .query(keyword)
                .fields("title", "summary", "tags.name", "category.name")
        )._toQuery();

        SearchRequest request = SearchRequest.of(s -> s
                .index("articles")
                .query(query)
        );

        SearchResponse<ArticleDocument> response = elasticsearchClient.search(request, ArticleDocument.class);

        List<ArticleDocument> docs = response.hits().hits().stream()
                .map(Hit::source)
                .toList();

        // map sang DTO
        return articleMapper.toResponseList(docs);
    }

    // chưa viết controller
    // phải convert publishDate tu Instance sang String luc luu xuong elastic

    public void deleteArticleDocument(String id) {
        articleSearchRepository.deleteById(id);
    }
}
