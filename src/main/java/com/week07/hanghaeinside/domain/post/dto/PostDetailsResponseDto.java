package com.week07.hanghaeinside.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDetailsResponseDto {
    private Long postId;
    private String nickname;
    private String title;
    private String content;
    private String postImg;
    private LocalDateTime createAt;
    private int viewCnt;
    private Long heartCnt;
    private Long unHeartCnt;
}
