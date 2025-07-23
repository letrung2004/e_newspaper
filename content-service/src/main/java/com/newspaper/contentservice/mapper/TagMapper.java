package com.newspaper.contentservice.mapper;

import com.newspaper.contentservice.dto.request.TagCreateRequest;
import com.newspaper.contentservice.dto.response.TagResponse;
import com.newspaper.contentservice.entity.Tag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {
    Tag toTag(TagCreateRequest request);
    TagResponse toTagResponse(Tag tag);
}
