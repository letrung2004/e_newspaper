package com.newspaper.contentservice.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleCreateRequest {
    String title;
    String featuredImage;
    String content;
    String category;
    Set<String> tags;
    Set<String> authors;
}
