package com.newspaper.contentservice.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.newspaper.contentservice.dto.response.UploadImageResponse;
import com.newspaper.contentservice.exception.AppException;
import com.newspaper.contentservice.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UploadImageService {
    Cloudinary cloudinary;

    public UploadImageResponse uploadImage(MultipartFile imageFile) {
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                Map result = cloudinary.uploader().upload(imageFile.getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));

                return UploadImageResponse.builder()
                        .urlImage(result.get("secure_url").toString())
                        .build();
            } catch (IOException ex) {
                log.error("Upload failed: {}", ex.getMessage());
            }
        }
        log.error("Upload failed: Khong upload duoc");
        return null;
    }

}
