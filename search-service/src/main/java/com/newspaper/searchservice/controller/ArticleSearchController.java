package com.newspaper.searchservice.controller;

import com.newspaper.searchservice.dto.ApiResponse;
import com.newspaper.searchservice.dto.response.ArticleSearchResponse;
import com.newspaper.searchservice.entity.ArticleDocument;
import com.newspaper.searchservice.service.ArticleSearchService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ArticleSearchController {
    ArticleSearchService articleSearchService;

    @PostMapping
    public ApiResponse<ArticleDocument> save(@RequestBody ArticleDocument article) {
        return ApiResponse.<ArticleDocument>builder()
                .result(articleSearchService.saveArticleDocument(article))
                .build();
    }

    @GetMapping
    public ApiResponse<Iterable<ArticleDocument>> getArticleDocuments() {
        return ApiResponse.<Iterable<ArticleDocument>>builder()
                .result(articleSearchService.getArticleDocuments())
                .build();
    }
    @GetMapping("/search")
    public ApiResponse<List<ArticleSearchResponse>> searchArticleDocuments(@RequestParam(required = false) String keyword) throws IOException {
        return ApiResponse.<List<ArticleSearchResponse>>builder()
                .result(articleSearchService.searchByKeyword(keyword))
                .build();
    }
}
