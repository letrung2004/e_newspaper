package com.newspaper.commentservice.mapper;

import com.newspaper.commentservice.dto.response.CommentResponse;
import com.newspaper.commentservice.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentResponse toCommentResponse(Comment comment);
}
