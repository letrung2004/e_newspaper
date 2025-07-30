package com.newspaper.contentservice.service;

import com.github.slugify.Slugify;
import com.newspaper.contentservice.repository.ArticleRepository;
import com.newspaper.contentservice.repository.CategoryRepository;
import com.newspaper.contentservice.repository.TagRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SlugService {
    ArticleRepository articleRepository;
    CategoryRepository categoryRepository;
    TagRepository tagRepository;

    private final Slugify slugify = Slugify.builder()
            .underscoreSeparator(true)
            .locale(Locale.forLanguageTag("vi"))
            .lowerCase(true)
            .build();

    public String createSlug(String title) {
        if (title == null || title.trim().isEmpty()) {
            return "";
        }
        return slugify.slugify(title.trim());
    }

    public String generateArticleSlug(String title) {
        String baseSlug = createSlug(title);
        String slug = baseSlug;

        while (articleRepository.existsBySlug(slug)) {
            slug = baseSlug + "_" + UUID.randomUUID().toString().substring(0, 8);
        }
        return slug;
    }

    public String generateCategorySlug(String title) {
        String baseSlug = createSlug(title);
        String slug = baseSlug;

        while (categoryRepository.existsBySlug(slug)) {
            slug = baseSlug + "_" + UUID.randomUUID().toString().substring(0, 8);
        }
        return slug;
    }

    public String generateTagSlug(String title) {
        String baseSlug = createSlug(title);
        String slug = baseSlug;

        while (tagRepository.existsBySlug(slug)) {
            slug = baseSlug + "_" + UUID.randomUUID().toString().substring(0, 8);
        }
        return slug;
    }



}
