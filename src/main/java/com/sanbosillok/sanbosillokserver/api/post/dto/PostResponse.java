package com.sanbosillok.sanbosillokserver.api.post.dto;

import com.sanbosillok.sanbosillokserver.api.post.domain.PostStatus;
import com.sanbosillok.sanbosillokserver.api.post.domain.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostResponse {

    private static final String MASK_CHAR = "*****";

    private Long id;
    private String title;
    private String content;
    private String lastModifier;
    private PostStatus status;
    private LocalDateTime updatedAt;
    private Boolean isExist;

    public PostResponse(Post post) {
        id = post.getId();
        title = post.getTitle();
        content = post.getContent();
        lastModifier = post.getLastModifier().substring(0, 3) + MASK_CHAR;
        status = post.getStatus();
        updatedAt = post.getUpdatedAt();
        isExist = (id != null);
    }
}
