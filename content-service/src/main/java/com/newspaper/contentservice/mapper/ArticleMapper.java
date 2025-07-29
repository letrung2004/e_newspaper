package com.newspaper.contentservice.mapper;

import com.newspaper.contentservice.dto.response.ArticleResponse;
import com.newspaper.contentservice.entity.Article;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleResponse toArticleResponse(Article article);
}
