package com.newspaper.contentservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleResponse {
    String id;
    String title;
    String slug;
    String featuredImage;
    String content;
    String summary;
    String audioUrl;
    String embedding;
    LocalDate publishDate;
    Integer viewCount;
    Set<TagResponse> tags;
    CategoryResponse category;
    Set<String> authors;
}
