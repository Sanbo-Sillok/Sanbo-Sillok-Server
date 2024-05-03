package com.sanbosillok.sanbosillokserver.post.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostRequest {
    private String title;
    private String contents;
}
