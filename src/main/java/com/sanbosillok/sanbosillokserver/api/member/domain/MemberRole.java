package com.sanbosillok.sanbosillokserver.member.domain;

public enum MemberRole {
    ADMIN,
    ACTIVE, // 승인 후
    GUEST, // read only
    INACTIVE // 승인 전
}
