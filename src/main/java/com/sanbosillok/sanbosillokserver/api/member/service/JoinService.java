package com.sanbosillok.sanbosillokserver.api.member.service;

import com.sanbosillok.sanbosillokserver.api.image.dto.ImagePathResponse;
import com.sanbosillok.sanbosillokserver.api.image.service.ImageService;
import com.sanbosillok.sanbosillokserver.api.member.domain.Member;
import com.sanbosillok.sanbosillokserver.api.member.domain.MemberRole;
import com.sanbosillok.sanbosillokserver.api.member.dto.CheckUserNameResponse;
import com.sanbosillok.sanbosillokserver.api.member.dto.JoinRequest;
import com.sanbosillok.sanbosillokserver.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JoinService {
    private final MemberRepository memberRepository;
    private final ImageService imageService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void join(JoinRequest joinRequest) {
        if (!memberRepository.existsByUsername(joinRequest.getUsername())) {

            String studentIdImagePath = imageService.upload(joinRequest.getStudentIdImage()).getImagePath();

            Member member = Member.builder()
                    .username(joinRequest.getUsername())
                    .password(bCryptPasswordEncoder.encode(joinRequest.getPassword()))
                    .role(MemberRole.INACTIVE)
                    .studentIdImagePath(studentIdImagePath)
                    .build();

            memberRepository.save(member);
        }
    }

    public CheckUserNameResponse isDuplicated(String username) {
        return new CheckUserNameResponse(memberRepository.existsByUsername(username));
    }

}