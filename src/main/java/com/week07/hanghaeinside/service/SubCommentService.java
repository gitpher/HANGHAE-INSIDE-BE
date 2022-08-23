package com.week07.hanghaeinside.service;

import com.week07.hanghaeinside.domain.comment.Comment;
import com.week07.hanghaeinside.domain.subcomment.SubComment;
import com.week07.hanghaeinside.domain.subcomment.dto.SubCommentPasswordDto;
import com.week07.hanghaeinside.domain.subcomment.dto.SubCommentRequestDto;
import com.week07.hanghaeinside.domain.subcomment.dto.SubCommentResponseDto;
import com.week07.hanghaeinside.repository.CommentRepository;
import com.week07.hanghaeinside.repository.SubCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SubCommentService {
    private final CommentRepository commentRepository;
    private final SubCommentRepository subCommentRepository;

    //대댓글 작성 메소드
    public SubCommentResponseDto createSubComment(SubCommentRequestDto subCommentRequestDto) {
        Comment comment = commentRepository.findById(subCommentRequestDto.getCommentId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 댓글입니다.")
        );
        SubComment subComment = SubComment.builder()
                .comment(comment)
                .nickname(subCommentRequestDto.getNickname())
                .password(subCommentRequestDto.getPassword())
                .content(subCommentRequestDto.getContent())
                .build();

        subCommentRepository.save(subComment);

        return buildSubCommentResponseDto(subComment);
    }

    //대댓글 조회 메소드
    public List<SubCommentResponseDto> getSubComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 댓글입니다.")
        );
        List<SubComment> subCommentList = subCommentRepository.findAllByCommentId(commentId);
        List<SubCommentResponseDto> subCommentResponseDtoList = new ArrayList<>();
        for(SubComment subComment : subCommentList){
            subCommentResponseDtoList.add(
                    SubCommentResponseDto.builder()
                            .id(subComment.getId())
                            .commentId(commentId)
                            .nickname(subComment.getNickname())
                            .content(subComment.getContent())
                            .createdAt(subComment.getCreatedAt())
                            .modifiedAt(subComment.getModifiedAt())
                            .build()
            );
        }
        return subCommentResponseDtoList;
    }

    //대댓글 수정 메소드
    @Transactional
    public SubCommentResponseDto updateSubComment(Long subcommentId, SubCommentRequestDto subCommentRequestDto) {
        SubComment subComment = subCommentRepository.findById(subcommentId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 대댓글입니다.")
        );
        if (!subComment.getNickname().equals(subCommentRequestDto.getNickname())) {
            throw new IllegalArgumentException("작성자가 일치하지 않습니다.");
        }
        if (!subComment.getPassword().equals(subCommentRequestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        subComment.update(subCommentRequestDto);
        return buildSubCommentResponseDto(subComment);
    }

    //대댓글 삭제 메소드
    public void deleteSubComment(Long subcommentId, SubCommentPasswordDto subCommentPasswordDto) {
        SubComment subComment = subCommentRepository.findById(subcommentId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 대댓글입니다.")
        );
        if (!subComment.getPassword().equals(subCommentPasswordDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        subCommentRepository.delete(subComment);
    }

    //대댓글 비밀번호 확인 메소드

    public boolean checkSubCommentPassword(Long subcommentId, SubCommentPasswordDto subCommentPasswordDto) {
        SubComment subComment = subCommentRepository.findById(subcommentId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 대댓글입니다.")
        );
        return subComment.getPassword().equals(subCommentPasswordDto.getPassword());
    }

    //공통 작업(responseDto build 작업) 메소드화
    private SubCommentResponseDto buildSubCommentResponseDto(SubComment subComment) {
        return SubCommentResponseDto.builder()
                .id(subComment.getId())
                .nickname(subComment.getNickname())
//                .password(subComment.getPassword())
                .commentId(subComment.getComment().getId())
                .content(subComment.getContent())
                .createdAt(subComment.getCreatedAt())
                .modifiedAt(subComment.getModifiedAt())
                .build();
    }
}
