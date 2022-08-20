package com.week07.hanghaeinside.service;

import com.week07.hanghaeinside.domain.TokenDto;
import com.week07.hanghaeinside.domain.UserDetailsImpl;
import com.week07.hanghaeinside.domain.member.Member;
import com.week07.hanghaeinside.domain.member.dto.LoginRequestDto;
import com.week07.hanghaeinside.domain.member.dto.RegisterRequestDto;
import com.week07.hanghaeinside.jwt.TokenProvider;
import com.week07.hanghaeinside.repository.MemberRepository;
import com.week07.hanghaeinside.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;

    private final TokenProvider tokenProvider;

    private final RefreshTokenRepository refreshTokenRepository;



    public Long register(RegisterRequestDto registerRequestDto) {

        String password = passwordEncoder.encode(registerRequestDto.getMemberPassword());

        Member member = Member.builder()
                .memberEmail(registerRequestDto.getMemberEmail())
                .memberNickname(registerRequestDto.getMemberNickname())
                .memberPassword(password)
                .build();

        return memberRepository.save(member).getId();
    }


    public TokenDto login(LoginRequestDto loginRequestDto) {

        String memberEmail = loginRequestDto.getMemberEmail();

        // 아이디와 비밀번호 검증
        Member member = memberRepository.findByMemberEmail(memberEmail)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("아이디를 확인해주세요.");
                });
        if (!passwordEncoder.matches(loginRequestDto.getMemberPassword(), member.getMemberPassword())) {
            throw new IllegalArgumentException("비밀번호를 확인해주세요.");
        }

        UserDetailsImpl userDetails = new UserDetailsImpl(member);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "");

        return tokenProvider.generateTokenDto(authentication);
    }

    public Long logout(Member member) {
        refreshTokenRepository.deleteByMember(member);
        return member.getId();
    }

    public boolean checkEmail(String memberEmail) {
        return memberRepository.existsByMemberEmail(memberEmail);
    }

    // 닉네임 중복 체크
    public boolean checkNickname(String memberNickname) {
        return memberRepository.existsByMemberNickname(memberNickname);
    }
}
