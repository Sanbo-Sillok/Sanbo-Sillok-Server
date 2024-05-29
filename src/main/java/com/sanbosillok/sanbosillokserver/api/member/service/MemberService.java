package com.sanbosillok.sanbosillokserver.api.member.service;

import com.sanbosillok.sanbosillokserver.api.member.domain.Member;
import com.sanbosillok.sanbosillokserver.api.member.domain.MemberRole;
import com.sanbosillok.sanbosillokserver.api.member.dto.MemberResponse;
import com.sanbosillok.sanbosillokserver.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public List<MemberResponse> getInactiveMembers() {
        return memberRepository.findAllByRoleEquals(MemberRole.INACTIVE).stream()
                .map(MemberResponse::new)
                .collect(Collectors.toList());
    }

    public void activateMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원 가입 신청이 존재하지 않습니다."));

        member.updateRole(MemberRole.ACTIVE);
        memberRepository.save(member);
    }
}
