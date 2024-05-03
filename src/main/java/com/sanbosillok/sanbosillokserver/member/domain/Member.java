package com.sanbosillok.sanbosillokserver.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String username;

    private String password;

    private MemberRole role;

    @Builder
    public Member(String username, String password, MemberRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public void updateRole(MemberRole role) {
        this.role = role;
    }
}
