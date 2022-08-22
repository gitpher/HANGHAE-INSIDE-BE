package com.week07.hanghaeinside.controller;

import com.week07.hanghaeinside.domain.TokenDto;
import com.week07.hanghaeinside.domain.UserDetailsImpl;
import com.week07.hanghaeinside.domain.member.Member;
import com.week07.hanghaeinside.domain.member.dto.CheckEmailRequestDto;
import com.week07.hanghaeinside.domain.member.dto.CheckNicknameRequestDto;
import com.week07.hanghaeinside.domain.member.dto.LoginRequestDto;
import com.week07.hanghaeinside.domain.member.dto.RegisterRequestDto;
import com.week07.hanghaeinside.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@RestController @RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto registerRequestDto) {

        Long memberId = memberService.register(registerRequestDto);

        return ResponseEntity.ok()
                .body(Map.of("memberId", memberId, "msg", "회원가입이 완료되었습니다."));
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {

        TokenDto token = memberService.login(loginRequestDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token.getAuthorization());
        headers.add("RefreshToken", token.getRefreshToken());

        String nickname = memberService.getNicknameByEmail(loginRequestDto.getEmail());

        return ResponseEntity.ok()
                .headers(headers)
                .body(Map.of("nickname", nickname));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@AuthenticationPrincipal UserDetails userDetails) {

        Member member = ((UserDetailsImpl) userDetails).getMember();

        Long memberId = memberService.logout(member);

        return ResponseEntity.ok()
                .body(Map.of("memberId", memberId, "msg", "로그아웃이 완료되었습니다."));

    }

    @PostMapping("/emailCheck")
    public ResponseEntity<Boolean> checkEmail(@RequestBody CheckEmailRequestDto checkEmailRequestDto){

        return ResponseEntity.ok(memberService.checkEmail(checkEmailRequestDto));
    }

    @PostMapping("/nicknameCheck")
    public ResponseEntity<Boolean> checkNickname(@RequestBody CheckNicknameRequestDto checkNicknameRequestDto){

        return ResponseEntity.ok(memberService.checkNickname(checkNicknameRequestDto));
    }
}
