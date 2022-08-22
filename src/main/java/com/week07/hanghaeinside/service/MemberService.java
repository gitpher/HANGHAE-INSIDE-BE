package com.week07.hanghaeinside.service;

import com.week07.hanghaeinside.domain.TokenDto;
import com.week07.hanghaeinside.domain.UserDetailsImpl;
import com.week07.hanghaeinside.domain.member.Member;
import com.week07.hanghaeinside.domain.member.dto.CheckEmailRequestDto;
import com.week07.hanghaeinside.domain.member.dto.CheckNicknameRequestDto;
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
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;

    private final TokenProvider tokenProvider;

    private final RefreshTokenRepository refreshTokenRepository;



    public Long register(RegisterRequestDto registerRequestDto) {

        String password = passwordEncoder.encode(registerRequestDto.getPassword());

        Member member = Member.builder()
                .memberEmail(registerRequestDto.getEmail())
                .memberNickname(registerRequestDto.getNickname())
                .memberPassword(password)
                .build();

        return memberRepository.save(member).getId();
    }


    public TokenDto login(LoginRequestDto loginRequestDto) {

        String memberEmail = loginRequestDto.getEmail();

        // 아이디와 비밀번호 검증
        Member member = memberRepository.findByMemberEmail(memberEmail)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("아이디를 확인해주세요.");
                });
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), member.getMemberPassword())) {
            throw new IllegalArgumentException("비밀번호를 확인해주세요.");
        }

        UserDetailsImpl userDetails = new UserDetailsImpl(member);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "");

        return tokenProvider.generateTokenDto(authentication);
    }

    @Transactional
    public Long logout(HttpServletRequest httpServletRequest) {
        if (!tokenProvider.validateToken(httpServletRequest.getHeader("RefreshToken"))) {
            throw new IllegalArgumentException("Token이 유효하지 않습티다.");
        }
        Member member = tokenProvider.getMemberFromAuthentication();
        if (null == member) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }

        refreshTokenRepository.deleteByMember(member);
        return member.getId();
    }

    public boolean checkEmail(CheckEmailRequestDto checkEmailRequestDto) {
        return memberRepository.existsByMemberEmail(checkEmailRequestDto.getEmail());
    }

    // 닉네임 중복 체크
    public boolean checkNickname(CheckNicknameRequestDto checkNicknameRequestDto) {
        return memberRepository.existsByMemberNickname(checkNicknameRequestDto.getNickname());
    }

    public String getNicknameByEmail(String email) {
        Member member = memberRepository.findByMemberEmail(email)
                .orElseThrow(()-> {
                    throw new IllegalArgumentException("존재하지 않는 이메일입니다.");
                });
        return member.getMemberNickname();
    }
}
