package com.sanbosillok.sanbosillokserver.api.post.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostUpdateRequest {
    private String contents;
}