package com.sanbosillok.sanbosillokserver.api.member.repository;

import com.sanbosillok.sanbosillokserver.api.member.domain.Member;
import com.sanbosillok.sanbosillokserver.api.member.domain.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByUsername(String username);

    Boolean existsByUsername(String username);

    List<Member> findAllByRoleEquals(MemberRole role);
}
