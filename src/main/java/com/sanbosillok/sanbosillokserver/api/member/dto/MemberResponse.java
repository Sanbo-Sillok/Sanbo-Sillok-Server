package com.sanbosillok.sanbosillokserver.api.member.dto;

import com.sanbosillok.sanbosillokserver.api.member.domain.Member;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberResponse {
    private String username;
    private LocalDateTime createdAt;
    private String studentIdImagePath;

    public MemberResponse(Member member) {
        username = member.getUsername();
        createdAt = member.getCreatedAt();
        studentIdImagePath = member.getStudentIdImagePath();
    }
}
