package com.sanbosillok.sanbosillokserver.api.member.domain;

import com.sanbosillok.sanbosillokserver.common.domain.BaseEntity;
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
public class Member extends BaseEntity {

    @Id @GeneratedValue
    private Long id;

    private String username;

    private String password;

    private MemberRole role;

    private String studentIdImagePath;

    @Builder
    public Member(String username, String password, MemberRole role, String studentIdImagePath) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.studentIdImagePath = studentIdImagePath;
    }

    public void updateRole(MemberRole role) {
        this.role = role;
    }
}
