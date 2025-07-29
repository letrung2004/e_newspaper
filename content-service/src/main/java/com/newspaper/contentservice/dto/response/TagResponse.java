package com.newspaper.contentservice.dto.response;


import com.newspaper.contentservice.entity.Article;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TagResponse {
    String id;
    String name;
    String slug;
    String description;
}
