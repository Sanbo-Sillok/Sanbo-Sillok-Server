package com.sanbosillok.sanbosillokserver.api.auth.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String refreshToken;
    private String accessToken;
    private String role;

    public LoginResponse(String refreshToken, String accessToken, String role) {
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
        this.role = role;
    }
}
