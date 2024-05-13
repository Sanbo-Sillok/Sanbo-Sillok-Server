package com.sanbosillok.sanbosillokserver.api.post.dto;

import com.sanbosillok.sanbosillokserver.api.post.domain.PostStatus;
import com.sanbosillok.sanbosillokserver.api.post.domain.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostResponse {

    private Long id;
    private String title;
    private String contents;
    private String lastModifier;
    private PostStatus status;
    private LocalDateTime updatedAt;
    private Boolean isExist;

    public PostResponse(Post post) {
        id = post.getId();
        title = post.getTitle();
        contents = post.getContents();
        lastModifier = post.getLastModifier();
        status = post.getStatus();
        updatedAt = post.getUpdatedAt();
        isExist = (id != null);
    }
}
