package com.week07.hanghaeinside.repository;

import com.week07.hanghaeinside.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberEmail(String memberEmail);

    boolean existsByMemberEmail(String memberEmail);

    boolean existsByMemberNickname(String memberNickname);
}
