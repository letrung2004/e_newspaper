package com.newspaper.contentservice.controller;

import com.newspaper.contentservice.dto.ApiResponse;
import com.newspaper.contentservice.dto.request.CategoryCreateRequest;
import com.newspaper.contentservice.dto.response.CategoryResponse;
import com.newspaper.contentservice.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CategoryController {
    CategoryService categoryService;

    @PostMapping("/create")
    ApiResponse<CategoryResponse> createTag(@RequestBody CategoryCreateRequest request) {
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.createCategory(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<CategoryResponse>> getTags() {
        return ApiResponse.<List<CategoryResponse>>builder()
                .result(categoryService.getAllCategories())
                .build();
    }

    @DeleteMapping("/delete/{categoryId}")
    ApiResponse<Void> delete(@PathVariable(value = "categoryId") String categoryId){
        categoryService.deleteCategory(categoryId);
        return ApiResponse.<Void>builder().build();
    }

    @GetMapping("/detail/{categorySlug}")
    ApiResponse<CategoryResponse> getDetailCategory(@PathVariable(value = "categorySlug") String categorySlug){
        categoryService.getCategory(categorySlug);
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.getCategory(categorySlug))
                .build();
    }
}
