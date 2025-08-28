package com.newspaper.searchservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(indexName = "articles")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleDocument {

    @Id
    String id;

    @Field(type = FieldType.Text, analyzer = "standard")
    String title;

    @Field(type = FieldType.Keyword)
    String slug;

    @Field(type = FieldType.Keyword)
    String featuredImage;

    @Field(type = FieldType.Text, analyzer = "standard")
    String summary;

    @Field(type = FieldType.Date)
    String publishDate;

    @Field(type = FieldType.Text, analyzer = "standard")
    List<String> authors;

    @Field(type = FieldType.Nested)
    List<Tag> tags;

    @Field(type = FieldType.Nested)
    Category category;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Tag {
        @Field(type = FieldType.Keyword)
        private String id;

        @Field(type = FieldType.Text)
        private String name;

        @Field(type = FieldType.Keyword)
        private String slug;

        @Field(type = FieldType.Text)
        private String description;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Category {
        @Field(type = FieldType.Keyword)
        private String id;

        @Field(type = FieldType.Text)
        private String name;

        @Field(type = FieldType.Keyword)
        private String slug;

        @Field(type = FieldType.Text)
        private String description;
    }
}
