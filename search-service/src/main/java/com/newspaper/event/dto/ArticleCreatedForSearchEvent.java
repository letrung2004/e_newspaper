package com.newspaper.event.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleCreatedForSearchEvent {
    String id;
    String title;
    String slug;
    String featuredImage;
    String summary;
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
