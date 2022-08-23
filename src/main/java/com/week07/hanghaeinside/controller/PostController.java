package com.week07.hanghaeinside.controller;

import com.week07.hanghaeinside.domain.UserDetailsImpl;
import com.week07.hanghaeinside.domain.member.Member;
import com.week07.hanghaeinside.domain.post.dto.PostRequestDto;
import com.week07.hanghaeinside.domain.post.dto.PostResponseDto;
import com.week07.hanghaeinside.repository.MemberRepository;
import com.week07.hanghaeinside.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor    // 생성자 주입
@RequestMapping("/api")
public class PostController {

    private final PostService postService;
    // 게시글 생성
    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@ModelAttribute PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetails userDetails) throws IOException {
        // @ModelAttribute : 들어온 값만 넣어주고 안 들어온 값이면 null을 반환
        Member member = ((UserDetailsImpl) userDetails).getMember();

        Long postId = postService.createPost(postRequestDto, member);
        //Map 인터페이스를 구현한 Map 컬렉션 클래스들은 키와 값을 하나의 쌍으로 저장하는 방식(key-value 방식)
        // Map.of 에는  key와 value를 최대 10개 까지 넣을 수 있는 메소드를 지원
        //Key를 통해 Value를 찾는다.
        //Key는 중복될 수 없지만, Value는 중복될 수 있다.
        return ResponseEntity.ok(Map.of("postId", postId));

    }

    // 게시글 삭제
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetails userDetails) {
        Member member = ((UserDetailsImpl) userDetails).getMember();

        String deleteMsg = postService.deletePost(postId, member);

        return new ResponseEntity<>(Map.of("msg", deleteMsg), HttpStatus.OK);
    }

    // 게시글 전체 조회
    @GetMapping("/posts")
    public ResponseEntity<?> findAllPost(@PageableDefault(size = 12) Pageable pageable){
        // size = -한 페이지당 보여질 개수
        Page<PostResponseDto> posts = postService.findAllPost(pageable);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    // 게시글 상세 조회
    @GetMapping("/posts/{postId}")
    public ResponseEntity<?> findPost(@PathVariable Long postId){
        postService.updateView(postId); // view ++
        return new ResponseEntity<>(postService.findPost(postId), HttpStatus.OK);
    }

/*
    // 개념글 조회 (페이지네이션 적용)
    @GetMapping("/posts/top")
    public ResponseEntity<?> findAllPostTop(@PageableDefault(size = 12) Pageable pageable) {
        Page<PostResponseDto> postResponseDtos = postService.findAllPostTop(pageable);
        return ResponseEntity.ok().body(postResponseDtos);
    }
*/

    @GetMapping("/posts/top")
    public ResponseEntity<?> findAllPostTop() {
        List<PostResponseDto> postResponseDtoList = postService.findAllPostTop();
        return ResponseEntity.ok().body(postResponseDtoList);
    }

}
