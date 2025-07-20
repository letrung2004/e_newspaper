package com.newspaper.commentservice.controller;

import com.newspaper.commentservice.dto.ApiResponse;
import com.newspaper.commentservice.dto.request.CommentRequest;
import com.newspaper.commentservice.dto.response.CommentResponse;
import com.newspaper.commentservice.entity.Comment;
import com.newspaper.commentservice.service.CommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentController {
    CommentService commentService;

    @PostMapping("/create")
    ApiResponse<CommentResponse> createComment(@RequestBody CommentRequest request) {
        return ApiResponse.<CommentResponse>builder()
                .result(commentService.createComment(request))
                .build();
    }
}
