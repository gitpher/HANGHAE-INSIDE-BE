package com.week07.hanghaeinside.service;

import com.week07.hanghaeinside.domain.heart.Heart;
import com.week07.hanghaeinside.domain.member.Member;
import com.week07.hanghaeinside.domain.post.Post;
import com.week07.hanghaeinside.domain.unheart.UnHeart;
import com.week07.hanghaeinside.repository.HeartRepository;
import com.week07.hanghaeinside.repository.PostRepository;
import com.week07.hanghaeinside.repository.UnHeartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HeartService {
    private final PostRepository postRepository;
    private final HeartRepository heartRepository;
    private final UnHeartRepository unHeartRepository;

    @Transactional
    //게시글 좋아요 메소드
    public ResponseEntity<?> makePostHeart(Long postId, HttpServletRequest httpServletRequest) {
        if (null == httpServletRequest.getHeader("RefreshToken")) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        if (null == httpServletRequest.getHeader("Authorization")) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        Member member = validateMember(httpServletRequest);
        if (null == member) {
            throw new IllegalArgumentException("Token이 유효하지 않습니다.");
        }
        Optional<Heart> optionalHeart = heartRepository.findByPostIdAndMember(postId, member);
        if (optionalHeart.isPresent()) {
            return ResponseEntity.ok().body(Map.of("msg","이미 추천을 했습니다."));
        } else {
            Heart heart = Heart.builder()
                    .post(post)
                    .heartBy(member.getMemberNickname())
                    .postHeart(1L)
                    .build();
            heartRepository.save(heart);
        }
        Long heartCnt = heartRepository.countAllByPostId(post.getId());
        post.updateHeart(heartCnt);
        return ResponseEntity.ok().body(Map.of("msg","추천이 완료되었습니다."));
    }

    @Transactional
    public ResponseEntity<?> makePostUnHeart(Long postId, HttpServletRequest httpServletRequest){
        if (null == httpServletRequest.getHeader("RefreshToken")) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        if (null == httpServletRequest.getHeader("Authorization")) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        Member member = validateMember(httpServletRequest);
        if (null == member) {
            throw new IllegalArgumentException("Token이 유효하지 않습니다.");
        }
        Optional<UnHeart> optionalUnHeart = unHeartRepository.findByPostIdAndMember(postId, member);
        if(optionalUnHeart.isPresent()){
            return ResponseEntity.ok().body(Map.of("msg","이미 비추천을 했습니다."));
        }else{
            UnHeart unHeart = UnHeart.builder()
                    .post(post)
                    .postUnHeart(1L)
                    .unHeartBy(member.getMemberNickname())
                    .build();
            unHeartRepository.save(unHeart);
        }
        Long unHeartCnt = unHeartRepository.countAllByPostId(post.getId());
        post.updateUnHeart(unHeartCnt);
        return ResponseEntity.ok().body(Map.of("msg","비추천이 완료되었습니다."));
    }


    public Member validateMember(HttpServletRequest httpServletRequest) {
        if (!tokenProvider.validateToken(httpServletRequest.getHeader("RefreshToken"))) {
            return null;
        }
        return tokenProvider.getMemberFromAuthentication();
    }

}
