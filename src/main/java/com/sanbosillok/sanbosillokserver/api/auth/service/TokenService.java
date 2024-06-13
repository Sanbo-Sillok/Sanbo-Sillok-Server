package com.sanbosillok.sanbosillokserver.api.auth.service;

import com.sanbosillok.sanbosillokserver.api.auth.dto.AccessTokenResponse;
import com.sanbosillok.sanbosillokserver.api.member.domain.Member;
import com.sanbosillok.sanbosillokserver.api.member.repository.MemberRepository;
import com.sanbosillok.sanbosillokserver.config.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    public AccessTokenResponse reIssueAccessToken(String refreshToken) {

        if (refreshToken == null) {
            throw new IllegalArgumentException("토큰이 비어있습니다.");
        }

        if (jwtTokenProvider.isExpired(refreshToken)) {
            throw new IllegalArgumentException("만료된 refresh token 입니다.");
        }

        Long userId = jwtTokenProvider.getUserId(refreshToken);
        Member member = memberRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("잘못된 token 형식입니다."));

        String username = member.getUsername();
        String role = member.getRole().toString();

        return new AccessTokenResponse(jwtTokenProvider.createAccessToken(username, role));
    }
}
