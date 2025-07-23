package com.newspaper.contentservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    String slug;
    String description;
    @ManyToMany(mappedBy = "tags")
    Set<Article> articles;
}
