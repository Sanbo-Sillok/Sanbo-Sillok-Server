package com.sanbosillok.sanbosillokserver.api.post.dto;

import com.sanbosillok.sanbosillokserver.api.post.domain.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostTitleResponse {
    private String title;

    public PostTitleResponse(Post post) {
        title = post.getTitle();
    }
}