package com.newspaper.contentservice.controller;

import com.newspaper.contentservice.dto.ApiResponse;
import com.newspaper.contentservice.dto.request.TagCreateRequest;
import com.newspaper.contentservice.dto.response.TagResponse;
import com.newspaper.contentservice.service.TagService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class TagController {
    TagService tagService;

    @PostMapping("/create")
    ApiResponse<TagResponse> createTag(@RequestBody TagCreateRequest request) {
        return ApiResponse.<TagResponse>builder()
                .result(tagService.createTag(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<TagResponse>> getTags() {
        return ApiResponse.<List<TagResponse>>builder()
                .result(tagService.getAllTags())
                .build();
    }

    @DeleteMapping("/{tagId}")
    ApiResponse<Void> delete(@PathVariable(value = "tagId") String tagId){
        tagService.deleteTag(tagId);
        return ApiResponse.<Void>builder().build();
    }
}
