package com.sanbosillok.sanbosillokserver.api.member.controller;

import com.sanbosillok.sanbosillokserver.api.member.dto.CheckUserNameResponse;
import com.sanbosillok.sanbosillokserver.api.member.dto.JoinRequest;
import com.sanbosillok.sanbosillokserver.api.member.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/signup")
    public void join(@ModelAttribute JoinRequest joinRequest) {
        joinService.join(joinRequest);
    }

    @GetMapping("/checkUserName/{username}")
    public CheckUserNameResponse checkUserName(@PathVariable String username) {
        return joinService.isDuplicated(username);
    }
}