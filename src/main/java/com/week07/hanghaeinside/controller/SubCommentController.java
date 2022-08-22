package com.week07.hanghaeinside.controller;

import com.week07.hanghaeinside.domain.subcomment.dto.SubCommentListResponseDto;
import com.week07.hanghaeinside.domain.subcomment.dto.SubCommentPasswordDto;
import com.week07.hanghaeinside.domain.subcomment.dto.SubCommentRequestDto;
import com.week07.hanghaeinside.domain.subcomment.dto.SubCommentResponseDto;
import com.week07.hanghaeinside.service.SubCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class SubCommentController {
    private final SubCommentService subCommentService;

    //대댓글 작성
    @PostMapping("/api/subcomments")
    public ResponseEntity<?> createSubComment(@RequestBody SubCommentRequestDto subCommentRequestDto) {
        SubCommentResponseDto subCommentResponseDto = subCommentService.createSubComment(subCommentRequestDto);
        return ResponseEntity.ok().body(Map.of("subcommentId", subCommentResponseDto.getId(), "msg", "대댓글이 작성되었습니다."));
    }

    //대댓글 조회
    @GetMapping("/api/subcomments")
    public ResponseEntity<?> getSubComment(@RequestParam("commentId") Long commentId) {
        SubCommentListResponseDto subCommentListResponseDto = subCommentService.getSubComment(commentId);
        return ResponseEntity.ok().body(subCommentListResponseDto);
    }

    //대댓글 수정
    @PutMapping("/api/subcomments/{subcommentId}")
    public ResponseEntity<?> updateSubComment(@PathVariable Long subcommentId, @RequestBody SubCommentRequestDto subCommentRequestDto) {
        SubCommentResponseDto subCommentResponseDto = subCommentService.updateSubComment(subcommentId, subCommentRequestDto);
        return ResponseEntity.ok().body(Map.of("subcommentId", subCommentResponseDto.getId(), "msg", "수정 완료되었습니다."));
    }

    //대댓글 삭제
    @DeleteMapping("/api/subcomments/{subcommentId}")
    public ResponseEntity<?> deleteSubComment(@PathVariable Long subcommentId, @RequestBody SubCommentPasswordDto subCommentPasswordDto) {
        subCommentService.deleteSubComment(subcommentId, subCommentPasswordDto);
        return ResponseEntity.ok().body(Map.of("subcommentId",subcommentId,"msg","삭제 완료되었습니다."));
    }

    //대댓글 비밀번호 확인
    @GetMapping("/api/subcomments/{subcommmentId}")
    public ResponseEntity<Boolean> checkSubCommentPassword(@PathVariable Long subcommmentId, @RequestBody SubCommentPasswordDto subCommentPasswordDto){
        return ResponseEntity.ok().body(subCommentService.checkSubCommentPassword(subcommmentId, subCommentPasswordDto));
    }
}
