package com.week07.hanghaeinside.repository;

import com.week07.hanghaeinside.domain.subcomment.SubComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCommentRepository extends JpaRepository<SubComment, Long> {

    List<SubComment>  findAllByCommentId(Long commentId);
}
