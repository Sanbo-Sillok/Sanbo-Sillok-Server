package com.sanbosillok.sanbosillokserver.api.auth.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class TokenResponse {
    private String accessToken;

    public TokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
