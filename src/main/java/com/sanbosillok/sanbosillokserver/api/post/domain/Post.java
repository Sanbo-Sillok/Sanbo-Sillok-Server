package com.sanbosillok.sanbosillokserver.api.post.domain;

import com.sanbosillok.sanbosillokserver.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "post")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Post extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String lastModifier;

    private PostStatus status;

    @Builder
    public Post(String title, String content, String lastModifier, PostStatus status) {
        this.title = title;
        this.content = content;
        this.lastModifier = lastModifier;
        this.status = status;
    }

    public String getLastModifier() {
        return lastModifier != null ? lastModifier : "Unknown";
    }

    public void update(String content, String lastModifier) {
        this.content = content;
        this.lastModifier = lastModifier;
    }

}
