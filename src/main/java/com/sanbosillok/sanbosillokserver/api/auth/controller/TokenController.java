package com.sanbosillok.sanbosillokserver.api.auth.controller;

import com.sanbosillok.sanbosillokserver.api.auth.dto.RefreshTokenRequest;
import com.sanbosillok.sanbosillokserver.api.auth.dto.TokenResponse;
import com.sanbosillok.sanbosillokserver.api.auth.service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
public class TokenController {
    private final TokenService tokenService;

    @PostMapping("/refresh")
    public TokenResponse getNewToken(@RequestBody @Valid RefreshTokenRequest refreshTokenRequest) {
        return tokenService.reIssueAccessToken(refreshTokenRequest.getRefreshToken());
    }
}
