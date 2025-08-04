package com.newspaper.contentservice.controller;

import com.newspaper.contentservice.dto.ApiResponse;
import com.newspaper.contentservice.dto.PageResponse;
import com.newspaper.contentservice.dto.request.ArticleCreateRequest;
import com.newspaper.contentservice.dto.response.ArticleResponse;
import com.newspaper.contentservice.service.ArticleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


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
    ApiResponse<PageResponse<ArticleResponse>> getArticles(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size" , required = false, defaultValue = "10") int size
    ) {
        return ApiResponse.<PageResponse<ArticleResponse>>builder()
                .result(articleService.getAllArticles(page,size))
                .build();
    }

    @GetMapping("/detail-by-id/{articleId}")
    ApiResponse<ArticleResponse> getArticle(@PathVariable("articleId") String articleId) {
        return ApiResponse.<ArticleResponse>builder()
                .result(articleService.getArticleById(articleId))
                .build();
    }

    @GetMapping("/detail-by-slug/{articleSlug}")
    ApiResponse<ArticleResponse> getArticleBySlug(@PathVariable("articleSlug") String articleSlug) {
        return ApiResponse.<ArticleResponse>builder()
                .result(articleService.getArticleBySlug(articleSlug))
                .build();
    }

    @DeleteMapping("/delete/{articleId}")
    ApiResponse<Void> delete(@PathVariable(value = "articleId") String articleId){
        articleService.deleteArticleById(articleId);
        return ApiResponse.<Void>builder().build();
    }

    @GetMapping("/all/{categorySlug}")
    ApiResponse<PageResponse<ArticleResponse>> getArticlesByCategorySlug(
            @PathVariable("categorySlug") String categorySlug,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size" , required = false, defaultValue = "10") int size
    ) {
        return ApiResponse.<PageResponse<ArticleResponse>>builder()
                .result(articleService.getAllArticlesByCategorySlug(categorySlug,page,size))
                .build();
    }
}
