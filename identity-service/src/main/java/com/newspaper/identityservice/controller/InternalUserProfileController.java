package com.newspaper.identityservice.controller;

import com.newspaper.identityservice.dto.request.ApiResponse;
import com.newspaper.identityservice.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InternalUserProfileController {
    UserService userService;

    @GetMapping("/username/{userId}")
    ApiResponse<String> getUsername(@PathVariable String userId){
        return ApiResponse.<String>builder()
                .result(userService.getUsernameByUserId(userId))
                .build();
    }
}
