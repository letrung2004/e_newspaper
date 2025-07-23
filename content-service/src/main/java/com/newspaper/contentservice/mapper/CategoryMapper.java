package com.newspaper.contentservice.mapper;

import com.newspaper.contentservice.dto.request.CategoryCreateRequest;
import com.newspaper.contentservice.dto.response.CategoryResponse;
import com.newspaper.contentservice.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponse toCategoryResponse(Category category);
    Category toCategory(CategoryCreateRequest request);
}
