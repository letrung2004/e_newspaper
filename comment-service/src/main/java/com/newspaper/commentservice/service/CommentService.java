package com.newspaper.commentservice.service;

import com.newspaper.commentservice.dto.PageResponse;
import com.newspaper.commentservice.dto.request.CommentRequest;
import com.newspaper.commentservice.dto.response.CommentResponse;
import com.newspaper.commentservice.entity.Comment;
import com.newspaper.commentservice.entity.CommentStatus;
import com.newspaper.commentservice.mapper.CommentMapper;
import com.newspaper.commentservice.repository.CommentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    DateTimeFormatter dateTimeFormatter;

    public CommentResponse createComment(CommentRequest commentRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Comment comment = Comment.builder()
                .content(commentRequest.getContent())
                .userId(authentication.getName())
                .createdDate(Instant.now())
                .articleId(commentRequest.getArticleId()) // tam thoi do chua tạo content-service
                .status(CommentStatus.PENDING)
                .build();

        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toCommentResponse(savedComment);
    }

    public PageResponse<CommentResponse> getAllCommentsInArticle(String articleId, int page, int size) {
        //yeu cau phan trang xuong repo
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page-1, size,sort);

        var pageData = commentRepository.findByArticleId(articleId, pageable);

        var commentList = pageData.getContent().stream().map(
                comment -> {
                    var commentResponse = commentMapper.toCommentResponse(comment);
                    commentResponse.setCreated(dateTimeFormatter.format(comment.getCreatedDate()));
                    return commentResponse;
                }
        ).toList();

        return PageResponse.<CommentResponse>builder()
                .currentPage(page)
                .pageSize(size)
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .data(commentList)
                .build();
//        return commentRepository.findByArticleId(articleId)
//                .stream()
//                .map(commentMapper::toCommentResponse)
//                .toList();
    }
}
