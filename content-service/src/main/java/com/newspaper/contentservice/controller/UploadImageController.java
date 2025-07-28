package com.newspaper.contentservice.controller;

import com.newspaper.contentservice.dto.ApiResponse;
import com.newspaper.contentservice.dto.response.UploadImageResponse;
import com.newspaper.contentservice.service.UploadImageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UploadImageController {
    UploadImageService uploadImageService;

    @PostMapping("/upload")
    ApiResponse<UploadImageResponse> uploadImage(@RequestParam("image") MultipartFile imageFile) {
        return ApiResponse.<UploadImageResponse>builder()
                .result(uploadImageService.uploadImage(imageFile))
                .build();
    }
}
