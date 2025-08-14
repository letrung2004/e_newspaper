package com.newspaper.commentservice.repository.httpclient;

import com.newspaper.commentservice.dto.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "identity-service", url = "${app.services.identity.url}")
public interface UserClient {

    @GetMapping("/internal/username/{userId}")
    ApiResponse<String> userName(@PathVariable String userId);
}
