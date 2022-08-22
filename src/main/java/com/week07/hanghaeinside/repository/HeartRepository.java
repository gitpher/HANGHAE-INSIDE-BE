package com.week07.hanghaeinside.repository;

import com.week07.hanghaeinside.domain.heart.Heart;
import com.week07.hanghaeinside.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Optional<Heart> findByPostIdAndMember(Long postId, Member member);

    Long countAllByPostId(Long postId);
}
