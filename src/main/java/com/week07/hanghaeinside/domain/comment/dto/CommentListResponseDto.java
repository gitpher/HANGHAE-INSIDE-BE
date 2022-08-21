package com.week07.hanghaeinside.domain.comment.dto;

import com.week07.hanghaeinside.domain.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentListResponseDto {
    private List<Comment> commentList;
}
