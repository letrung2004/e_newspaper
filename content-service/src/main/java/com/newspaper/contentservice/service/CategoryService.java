package com.newspaper.contentservice.service;

import com.newspaper.contentservice.dto.request.CategoryCreateRequest;
import com.newspaper.contentservice.dto.response.CategoryResponse;
import com.newspaper.contentservice.entity.Category;
import com.newspaper.contentservice.mapper.CategoryMapper;
import com.newspaper.contentservice.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;
    SlugService slugService;

    @PreAuthorize("hasRole('ADMIN')")
    public CategoryResponse createCategory(CategoryCreateRequest request){
        Category category = categoryMapper.toCategory(request);
        category.setSlug(slugService.generateCategorySlug(request.getName()));
        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.toCategoryResponse(savedCategory);
    }

    public List<CategoryResponse> getAllCategories(){
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toCategoryResponse)
                .collect(Collectors.toList());
    }


    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCategory(String categoryId){
        categoryRepository.deleteById(categoryId);
    }

}
