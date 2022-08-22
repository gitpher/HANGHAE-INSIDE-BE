package com.week07.hanghaeinside.domain.subcomment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubCommentRequestDto {
    private String nickname;
    private String password;
    private Long commentId;
    @Lob
    private String content;
}
