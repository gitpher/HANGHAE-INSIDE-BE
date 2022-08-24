package com.week07.hanghaeinside.repository;

import com.week07.hanghaeinside.domain.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    // 게시글 전체 조회(페이지네이션 적용)
//    Page<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);

    // 게시글 전체 조회
    List<Post> findAllByOrderByCreatedAtDesc();


//    Page<Post> findAllByHeartCntGreaterThanOrderByCreatedAtDesc(int heartCnt, Pageable pageable);

    List<Post> findAllByHeartCntGreaterThanOrderByCreatedAtDesc(int heartCnt);


    @Modifying
    @Query("update Post p set p.viewCnt = p.viewCnt + 1 where p.id = :id")
    int updateView(@Param("id") Long id);

    List<Post> findByCreatedById(String memberNickname);


}
