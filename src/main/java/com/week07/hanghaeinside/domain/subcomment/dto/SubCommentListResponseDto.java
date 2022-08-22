package com.week07.hanghaeinside.domain.subcomment.dto;

import com.week07.hanghaeinside.domain.subcomment.SubComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubCommentListResponseDto {
    List<SubComment> subCommentList;
}
