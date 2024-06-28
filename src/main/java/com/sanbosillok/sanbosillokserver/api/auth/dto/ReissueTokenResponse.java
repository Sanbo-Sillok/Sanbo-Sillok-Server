package com.sanbosillok.sanbosillokserver.api.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReissueTokenResponse {
    private String accessToken;
    private String refreshToken;
}
