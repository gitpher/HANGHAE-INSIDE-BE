package com.week07.hanghaeinside.service;

import com.week07.hanghaeinside.domain.member.Member;
import com.week07.hanghaeinside.domain.member.dto.RegisterRequestDto;
import com.week07.hanghaeinside.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final PasswordEncoder passwordEncoder;

    private final MemberRepository memberRepository;



    public Long register(RegisterRequestDto registerRequestDto) {

        String password = passwordEncoder.encode(registerRequestDto.getMemberPassword());

        Member member = Member.builder()
                .memberNickname(registerRequestDto.getMemberNickname())
                .memberEmail(registerRequestDto.getMemberEmail())
                .memberPassword(password)
                .build();

        return memberRepository.save(member).getId();
    }







}
