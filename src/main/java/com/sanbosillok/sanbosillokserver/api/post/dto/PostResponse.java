package com.sanbosillok.sanbosillokserver.post.dto;

import com.sanbosillok.sanbosillokserver.post.domain.Post;
import com.sanbosillok.sanbosillokserver.post.domain.PostStatus;
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
