package com.week07.hanghaeinside.repository;

import com.week07.hanghaeinside.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
