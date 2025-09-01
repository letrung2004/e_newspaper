package com.newspaper.commentservice.repository;

import com.newspaper.commentservice.entity.Comment;
import com.newspaper.commentservice.entity.CommentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    Page<Comment> findByArticleId(String articleId, Pageable pageable);
    Page<Comment> findByArticleIdAndStatus(String articleId, CommentStatus status, Pageable pageable);
    void deleteByArticleId(String articleId);
}
