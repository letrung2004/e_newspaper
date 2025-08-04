package com.newspaper.contentservice.repository;

import com.newspaper.contentservice.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {
    boolean existsBySlug(String slug);
    Article findBySlug(String slug);
    Page<Article> findAllBy(Pageable pageable);
    Page<Article> findAllByCategory_Slug(String categorySlug, Pageable pageable);
}
