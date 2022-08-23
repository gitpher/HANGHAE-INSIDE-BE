package com.week07.hanghaeinside.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    //commentId
    private Long id;
    private Long postId;
    private String nickname;
//    private String password;
    @Lob
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
