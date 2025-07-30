package com.newspaper.contentservice.controller;

import com.newspaper.contentservice.dto.ApiResponse;
import com.newspaper.contentservice.dto.request.ArticleCreateRequest;
import com.newspaper.contentservice.dto.response.ArticleResponse;
import com.newspaper.contentservice.service.ArticleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ArticleController {
    ArticleService articleService;

    @PostMapping("/create")
    ApiResponse<ArticleResponse> createArticle(@RequestBody ArticleCreateRequest request) {
        return ApiResponse.<ArticleResponse>builder()
                .result(articleService.createArticle(request))
                .build();
    }

    @GetMapping("/all")
    ApiResponse<List<ArticleResponse>> getArticles() {
        return ApiResponse.<List<ArticleResponse>>builder()
                .result(articleService.getAllArticles())
                .build();
    }

    @GetMapping("/detail/{articleId}")
    ApiResponse<ArticleResponse> getArticle(@PathVariable("articleId") String articleId) {
        return ApiResponse.<ArticleResponse>builder()
                .result(articleService.getArticleById(articleId))
                .build();
    }

    @GetMapping("/detail/{articleSlug}")
    ApiResponse<ArticleResponse> getArticleBySlug(@PathVariable("articleSlug") String articleSlug) {
        return ApiResponse.<ArticleResponse>builder()
                .result(articleService.getArticleBySlug(articleSlug))
                .build();
    }
}
