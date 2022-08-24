package com.week07.hanghaeinside.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PostResponseDto {
    private Long postId;
    private String nickname;
    private String title;
    private String postImg;
    private String createAt;
    private int viewCnt;
    private int heartCnt;
    private int unHeartCnt;

}
