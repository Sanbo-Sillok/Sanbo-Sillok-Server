package com.sanbosillok.sanbosillokserver.api.auth.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String accessToken;
    private String role;

    public LoginResponse(String accessToken, String role) {
        this.accessToken = accessToken;
        this.role = role;
    }
}
