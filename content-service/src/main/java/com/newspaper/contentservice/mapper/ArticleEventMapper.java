package com.newspaper.contentservice.mapper;


import com.newspaper.contentservice.entity.Article;
import com.newspaper.contentservice.entity.Category;
import com.newspaper.contentservice.entity.Tag;
import com.newspaper.event.dto.ArticleCreatedForSearchEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface ArticleEventMapper {
    @Mapping(target = "category", source = "category")
    @Mapping(target = "tags", source = "tags")
    ArticleCreatedForSearchEvent toSearchEvent(Article article);

    default ArticleCreatedForSearchEvent.Category mapCategory(Category category) {
        if (category == null) return null;
        return new ArticleCreatedForSearchEvent.Category(
                category.getId(),
                category.getName(),
                category.getSlug(),
                category.getDescription()
        );
    }

    default List<ArticleCreatedForSearchEvent.Tag> mapTags(Set<Tag> tags) {
        return tags.stream()
                .map(tag -> new ArticleCreatedForSearchEvent.Tag(
                        tag.getId(),
                        tag.getName(),
                        tag.getSlug(),
                        tag.getDescription()
                ))
                .toList();
    }
}
