package com.sanbosillok.sanbosillokserver.api.member.service;

import com.sanbosillok.sanbosillokserver.api.member.domain.Member;
import com.sanbosillok.sanbosillokserver.api.member.domain.MemberRole;
import com.sanbosillok.sanbosillokserver.api.member.dto.CheckUserNameResponse;
import com.sanbosillok.sanbosillokserver.api.member.dto.JoinRequest;
import com.sanbosillok.sanbosillokserver.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void join(JoinRequest joinRequest) {
        if (!memberRepository.existsByUsername(joinRequest.getUsername())) {

            Member member = Member.builder()
                    .username(joinRequest.getUsername())
                    .password(bCryptPasswordEncoder.encode(joinRequest.getPassword()))
                    .role(MemberRole.INACTIVE)
                    .build();

            memberRepository.save(member);
        }
    }

    public CheckUserNameResponse isDuplicated(String username) {
        return new CheckUserNameResponse(memberRepository.existsByUsername(username));
    }

}