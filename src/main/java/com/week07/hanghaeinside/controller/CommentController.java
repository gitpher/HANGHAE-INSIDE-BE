package com.week07.hanghaeinside.controller;

import com.week07.hanghaeinside.domain.comment.dto.CommentPasswordDto;
import com.week07.hanghaeinside.domain.comment.dto.CommentRequestDto;
import com.week07.hanghaeinside.domain.comment.dto.CommentResponseDto;
import com.week07.hanghaeinside.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    //댓글 작성
    @PostMapping("api/comments")
    public ResponseEntity<?> createComment(@RequestBody CommentRequestDto commentRequestDto) {
        CommentResponseDto commentResponseDto = commentService.createComment(commentRequestDto);
        return ResponseEntity.ok().body(Map.of("commentId", commentResponseDto.getId(), "msg", "댓글이 작성되었습니다."));
    }

    //댓글 조회
    @GetMapping("api/comments")
    public ResponseEntity<?> getComment(@RequestParam("postId") Long postId) {
        List<CommentResponseDto> commentResponseDtoList = commentService.getComment(postId);
        return ResponseEntity.ok().body(commentResponseDtoList);
    }

    //댓글 수정
    @PutMapping("/api/comments/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto) {
        CommentResponseDto commentResponseDto = commentService.updateComment(commentId, commentRequestDto);
        return ResponseEntity.ok().body(Map.of("commentId", commentResponseDto.getId(), "msg", "수정이 완료되었습니다."));
    }

    //댓글 삭제
    @DeleteMapping("/api/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId, @RequestBody CommentPasswordDto commentPasswordDto) {
        commentService.deleteComment(commentId, commentPasswordDto);
        return ResponseEntity.ok().body(Map.of("commentId", commentId, "msg", "삭제 완료되었습니다."));
    }

    //비밀번호 확인
    @GetMapping("/api/comments/{commentId}")
    public ResponseEntity<Boolean> checkCommentPassword(@PathVariable Long commentId, @RequestBody CommentPasswordDto commentPasswordDto) {
        return ResponseEntity.ok().body(commentService.checkCommentPassword(commentId, commentPasswordDto));
    }
}
