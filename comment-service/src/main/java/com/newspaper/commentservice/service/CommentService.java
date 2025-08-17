package com.newspaper.commentservice.service;

import com.newspaper.commentservice.dto.PageResponse;
import com.newspaper.commentservice.dto.request.CommentRequest;
import com.newspaper.commentservice.dto.response.CommentResponse;
import com.newspaper.commentservice.entity.Comment;
import com.newspaper.commentservice.entity.CommentStatus;
import com.newspaper.commentservice.exception.AppException;
import com.newspaper.commentservice.exception.ErrorCode;
import com.newspaper.commentservice.mapper.CommentMapper;
import com.newspaper.commentservice.repository.CommentRepository;
import com.newspaper.commentservice.repository.httpclient.UserClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
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
    UserClient userClient;

    public CommentResponse createComment(CommentRequest commentRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        var username = userClient.userName(authentication.getName()).getResult();

        Comment comment = Comment.builder()
                .content(commentRequest.getContent())
                .userName(username)
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

        var pageData = commentRepository.findByArticleIdAndStatus(articleId, CommentStatus.APPROVED, pageable);

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
    }


    @PreAuthorize("hasRole('ADMIN') or hasRole('EDITOR')")
    public PageResponse<CommentResponse> getAllComments(int page, int size) {
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        var pageData = commentRepository.findAll(pageable);
        var commentList = pageData.getContent().stream()
                .map(comment -> {
                    var commentResponse = commentMapper.toCommentResponse(comment);
                    commentResponse.setCreated(dateTimeFormatter.format(comment.getCreatedDate()));
                    return commentResponse;
                })
                .toList();

        return PageResponse.<CommentResponse>builder()
                .currentPage(page)
                .pageSize(size)
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .data(commentList)
                .build();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('EDITOR')")
    public CommentResponse updateCommentStatus(String commentId, CommentStatus status) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_EXIST));
        comment.setStatus(status);
        commentRepository.save(comment);
        return commentMapper.toCommentResponse(comment);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('EDITOR')")
    public void deleteComment(String commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new AppException(ErrorCode.COMMENT_NOT_EXIST);
        }
        commentRepository.deleteById(commentId);
    }

}
