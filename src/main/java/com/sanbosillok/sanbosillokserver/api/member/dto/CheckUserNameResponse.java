package com.sanbosillok.sanbosillokserver.api.member.dto;

import lombok.Data;

@Data
public class CheckUserNameResponse {
    private Boolean isExist;

    public CheckUserNameResponse(Boolean isExist) {
        this.isExist = isExist;
    }
}