package com.sanbosillok.sanbosillokserver.api.member.controller;

import com.sanbosillok.sanbosillokserver.api.member.dto.MemberResponse;
import com.sanbosillok.sanbosillokserver.api.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("")
    public List<MemberResponse> getDeactivatedMembers() {
        return memberService.getInactiveMembers();
    }

    @PatchMapping("/{id}")
    public void activateMember(@PathVariable Long id) {
        memberService.activateMember(id);
    }
}
