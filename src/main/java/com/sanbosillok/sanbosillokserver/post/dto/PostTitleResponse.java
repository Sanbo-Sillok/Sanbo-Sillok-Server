package com.sanbosillok.sanbosillokserver.post.dto;

import com.sanbosillok.sanbosillokserver.post.domain.Post;
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
