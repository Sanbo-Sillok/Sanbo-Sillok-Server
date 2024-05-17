package com.sanbosillok.sanbosillokserver.api.auth.service;

import com.sanbosillok.sanbosillokserver.api.auth.dto.CustomUserDetails;
import com.sanbosillok.sanbosillokserver.api.member.domain.Member;
import com.sanbosillok.sanbosillokserver.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByUsername(username);

        if (member != null) {
            return new CustomUserDetails(member);
        }

        return null;
    }
}
