package com.week07.hanghaeinside.domain.subcomment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class SubCommentResponseDto {
    //subcommentId
    private Long id;
    private String nickname;
//    private String password;
    private Long commentId;
    @Lob
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
