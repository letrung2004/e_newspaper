package com.newspaper.searchservice.controller;

import com.newspaper.event.dto.ArticleCreatedForSearchEvent;
import com.newspaper.searchservice.entity.ArticleDocument;
import com.newspaper.searchservice.mapper.ArticleMapper;
import com.newspaper.searchservice.service.ArticleSearchService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ArticleEventController {
    ArticleSearchService articleSearchService;
    ArticleMapper articleMapper;

    @KafkaListener(topics = "create-search-article")
    public void listenArticleCreated(ArticleCreatedForSearchEvent event) {
        ArticleDocument articleDocument = articleMapper.toArticleDocument(event);
        articleSearchService.saveArticleDocument(articleDocument);
    }
}
