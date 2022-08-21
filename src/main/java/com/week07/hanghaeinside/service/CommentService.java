package com.week07.hanghaeinside.service;

import com.week07.hanghaeinside.domain.comment.Comment;
import com.week07.hanghaeinside.domain.comment.dto.CommentListResponseDto;
import com.week07.hanghaeinside.domain.comment.dto.CommentPasswordDto;
import com.week07.hanghaeinside.domain.comment.dto.CommentRequestDto;
import com.week07.hanghaeinside.domain.comment.dto.CommentResponseDto;
import com.week07.hanghaeinside.domain.post.Post;
import com.week07.hanghaeinside.repository.CommentRepository;
import com.week07.hanghaeinside.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    //댓글 작성 메소드
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto) {
        Post post = postRepository.findById(commentRequestDto.getPostId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );
        Comment comment = Comment.builder()
//                .post(post)
                .content(commentRequestDto.getContent())
                .nickname(commentRequestDto.getNickname())
                .password(commentRequestDto.getPassword())
                .content(commentRequestDto.getContent())
                .build();

        commentRepository.save(comment);
        return buildCommentResponseDto(comment);
    }

    //댓글 조회 메소드(특정 게시글의 댓글 목록 전체 조회)
    //::TODO 댓글 조회 메소드 내 대댓글 목록 추가
    public CommentListResponseDto getComment(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );
        List<Comment> commentList = commentRepository.findAllByPostId(postId);


        return CommentListResponseDto.builder()
                .commentList(commentList)
                .build();
    }

    //댓글 업데이트 메소드
    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 댓글입니다.")
        );
        if (!comment.getNickname().equals(commentRequestDto.getNickname())) {
            throw new IllegalArgumentException("작성자가 일치하지 않습니다.");
        }
        if (!comment.getPassword().equals(commentRequestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        comment.update(commentRequestDto);
        return buildCommentResponseDto(comment);
    }

    //댓글 삭제 메소드
    public void deleteComment(Long commentId, CommentRequestDto commentRequestDto){
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 댓글입니다.")
        );
        if(!comment.getNickname().equals(commentRequestDto.getNickname())){
            throw new IllegalArgumentException("작성자가 일치하지 않습니다.");
        }
        if(!comment.getPassword().equals(commentRequestDto.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        commentRepository.delete(comment);
    }

    //댓글 비밀번호 확인 메소드
    public boolean checkCommentPassword(Long commentId, CommentPasswordDto commentPasswordDto){
      Comment comment =  commentRepository.findById(commentId).orElseThrow(
              ()-> new IllegalArgumentException("존재하지 않는 댓글입니다.")
      );
      if(comment.getPassword().equals(commentPasswordDto)){
          return true;
      }else {
          return false;
      }
    }

    //공통 작업(responseDto build 작업) 메소드화
    private CommentResponseDto buildCommentResponseDto(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
//                .postId(comment.getPost().getId())
                .nickname(comment.getNickname())
                .password(comment.getPassword())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .modifiedAt(comment.getModifiedAt())
                .build();
    }
}

