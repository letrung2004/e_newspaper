package com.newspaper.searchservice.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleSearchResponse {
    String id;
    String title;
    String slug;
    String featuredImage;
    String summary;
    String publishDate;
    List<String> authors;
    List<Tag> tags;
    Category category;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Tag {
        private String id;
        private String name;
        private String slug;
        private String description;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Category {
        private String id;
        private String name;
        private String slug;
        private String description;
    }
}
