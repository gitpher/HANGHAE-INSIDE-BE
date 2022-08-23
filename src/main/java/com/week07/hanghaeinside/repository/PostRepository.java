package com.week07.hanghaeinside.repository;

import com.week07.hanghaeinside.domain.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);


    Page<Post> findAllByHeartCntGreaterThanOrderByCreatedAtDesc(Long heartCnt, Pageable pageable);


    @Modifying
    @Query("update Post p set p.viewCnt = p.viewCnt + 1 where p.id = :id")
    int updateView(Long id);

}
