package com.week07.hanghaeinside.repository;

import com.week07.hanghaeinside.domain.RefreshToken;
import com.week07.hanghaeinside.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    void deleteByMember(Member member);

    Optional<RefreshToken> findByMember(Member member);

}
