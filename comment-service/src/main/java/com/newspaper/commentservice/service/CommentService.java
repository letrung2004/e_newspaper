package com.newspaper.commentservice.service;

import com.newspaper.commentservice.dto.request.CommentRequest;
import com.newspaper.commentservice.dto.response.CommentResponse;
import com.newspaper.commentservice.entity.Comment;
import com.newspaper.commentservice.entity.CommentStatus;
import com.newspaper.commentservice.mapper.CommentMapper;
import com.newspaper.commentservice.repository.CommentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentService {
    CommentRepository commentRepository;
    CommentMapper commentMapper;

    public CommentResponse createComment(CommentRequest commentRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Comment comment = Comment.builder()
                .content(commentRequest.getContent())
                .userId(authentication.getName())
                .createdDate(Instant.now())
                .articleId(authentication.getName()) // tam thoi do chua tạo content-service
                .status(CommentStatus.PENDING)
                .build();

        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toCommentResponse(savedComment);
    }

    public List<CommentResponse> getAllCommentsInArticle(String articleId) {
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(commentMapper::toCommentResponse)
                .toList();
    }
}
