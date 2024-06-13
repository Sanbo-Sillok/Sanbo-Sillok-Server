package com.sanbosillok.sanbosillokserver.api.auth.controller;

import com.sanbosillok.sanbosillokserver.api.auth.dto.AccessTokenResponse;
import com.sanbosillok.sanbosillokserver.api.auth.dto.RefreshTokenRequest;
import com.sanbosillok.sanbosillokserver.api.auth.service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
public class TokenController {
    private final TokenService tokenService;

    @GetMapping("/refresh")
    public AccessTokenResponse getNewToken(@RequestBody @Valid RefreshTokenRequest refreshTokenRequest) {
        return tokenService.reIssueAccessToken(refreshTokenRequest.getRefreshToken());
    }
}
