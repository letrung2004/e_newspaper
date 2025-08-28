package com.newspaper.contentservice.mapper;

import com.newspaper.contentservice.dto.request.ArticleCreateRequest;
import com.newspaper.contentservice.dto.response.ArticleResponse;
import com.newspaper.contentservice.entity.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleResponse toArticleResponse(Article article);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "tags", ignore = true)
    Article toArticle(ArticleCreateRequest articleCreateRequest);
}
