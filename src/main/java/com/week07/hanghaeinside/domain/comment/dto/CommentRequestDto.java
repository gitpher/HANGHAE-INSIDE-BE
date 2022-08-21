package com.week07.hanghaeinside.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.Lob;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {

    private String nickname;

    private String password;

    @Nullable
    private Long postId;

    @Lob
    @Nullable
    private String content;
}
