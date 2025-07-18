package com.newspaper.identityservice.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ExchangeTokenResponse {
    //do param của google dạng client_id, grant_type,... nên ta cần JsonNaming để chuyển
    String accessToken;
    String tokenType;
    Long expiresIn;
    String refreshToken;
    String scope;
}
