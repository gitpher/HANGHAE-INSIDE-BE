package com.week07.hanghaeinside.controller;

import com.week07.hanghaeinside.domain.member.dto.LoginRequestDto;
import com.week07.hanghaeinside.domain.member.dto.RegisterRequestDto;
import com.week07.hanghaeinside.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController @RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/register")
    public ResponseEntity<?> register(RegisterRequestDto registerRequestDto) {

        Long memberId = memberService.register(registerRequestDto);

        return ResponseEntity.ok()
                .body(Map.of("memberId", memberId, "msg", "회원가입이 완료되었습니다."));
    }



}
