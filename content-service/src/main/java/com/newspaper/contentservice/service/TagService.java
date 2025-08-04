package com.newspaper.contentservice.service;

import com.newspaper.contentservice.dto.request.TagCreateRequest;
import com.newspaper.contentservice.dto.response.TagResponse;
import com.newspaper.contentservice.entity.Tag;
import com.newspaper.contentservice.mapper.TagMapper;
import com.newspaper.contentservice.repository.TagRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TagService {
    TagRepository tagRepository;
    TagMapper tagMapper;
    SlugService slugService;

    @PreAuthorize("hasRole('ADMIN')")
    public TagResponse createTag(TagCreateRequest request) {
        Tag tag = tagMapper.toTag(request);
        tag.setSlug(slugService.generateTagSlug(request.getName()));
        Tag saveTag = tagRepository.save(tag);
        return tagMapper.toTagResponse(saveTag);
    }

    public List<TagResponse> getAllTags() {
        return tagRepository.findAll().stream().map(tagMapper::toTagResponse).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTag(String tagId) {
        tagRepository.deleteById(tagId);
    }

}
