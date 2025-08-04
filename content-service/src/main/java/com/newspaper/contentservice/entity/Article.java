package com.newspaper.contentservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String title;
    String slug;
    String featuredImage;

    @ElementCollection
    @CollectionTable(name = "article_authors")
    @OnDelete(action = OnDeleteAction.CASCADE)
    Set<String> authors;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    String content;

    @Column(columnDefinition = "TEXT")
    String summary;

    String audioUrl;
    String embedding;
    Instant publishDate;
    Integer viewCount = 0;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    @ManyToMany
    Set<Tag> tags;
}
