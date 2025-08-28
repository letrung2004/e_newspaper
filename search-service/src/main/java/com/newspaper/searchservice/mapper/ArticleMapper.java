package com.newspaper.searchservice.mapper;

import com.newspaper.searchservice.dto.response.ArticleSearchResponse;
import com.newspaper.searchservice.entity.ArticleDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface  ArticleMapper {
    @Mapping(target = "publishDate", source = "publishDate", dateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    ArticleSearchResponse toResponse(ArticleDocument document);

    List<ArticleSearchResponse> toResponseList(List<ArticleDocument> documents);
}
