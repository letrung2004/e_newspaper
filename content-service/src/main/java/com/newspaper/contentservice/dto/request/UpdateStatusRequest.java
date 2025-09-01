package com.newspaper.contentservice.dto.request;

import com.newspaper.contentservice.entity.ArticleStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateStatusRequest {
    ArticleStatus status;
}
