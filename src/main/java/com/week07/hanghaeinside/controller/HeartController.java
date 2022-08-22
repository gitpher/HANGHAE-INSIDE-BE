package com.week07.hanghaeinside.controller;

import com.week07.hanghaeinside.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class HeartController {
    private final HeartService heartService;

    @PostMapping("/api/posts/like/{postId}")
    public ResponseEntity<?> makePostHeart(@PathVariable Long postId, HttpServletRequest httpServletRequest) {
        return heartService.makePostHeart(postId, httpServletRequest);
    }

    @PostMapping("/api/posts/dislike/{postId}")
    public ResponseEntity<?> makePostUnHeart(@PathVariable Long postId, HttpServletRequest httpServletRequest){
        return  heartService.makePostUnHeart(postId, httpServletRequest);
    }
}
