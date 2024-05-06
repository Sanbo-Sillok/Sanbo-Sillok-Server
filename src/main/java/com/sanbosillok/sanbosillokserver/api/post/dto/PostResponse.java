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
    private PostStatus status;
    private LocalDateTime updatedAt;

    public PostResponse(Post post) {
        id = post.getId();
        title = post.getTitle();
        contents = post.getContents();
        status = post.getStatus();
        updatedAt = post.getUpdatedAt();
    }
}
