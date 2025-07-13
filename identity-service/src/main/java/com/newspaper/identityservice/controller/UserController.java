package com.newspaper.identityservice.controller;

import com.newspaper.identityservice.dto.request.ApiResponse;
import com.newspaper.identityservice.dto.request.UserCreationRequest;
import com.newspaper.identityservice.dto.request.UserUpdateRequest;
import com.newspaper.identityservice.entity.User;
import com.newspaper.identityservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {

    UserService userService;

    @PostMapping("/registration")
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<User>builder()
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<User>> getAllUsers() {
        return ApiResponse.<List<User>>builder()
                .result(userService.getAllUsers())
                .build();
    }

    @GetMapping("/{userId}")
    User getUserById(@PathVariable("userId") String userId) {
        return userService.getUserById(userId);
    }


    @PutMapping("/{userId}")
    User updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    void deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
    }
}
