package com.week07.hanghaeinside.service;

import com.week07.hanghaeinside.domain.UserDetailsImpl;
import com.week07.hanghaeinside.domain.member.Member;
import com.week07.hanghaeinside.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException {
        Optional<Member> member = memberRepository.findByMemberEmail(memberEmail);
        return member
                .map(UserDetailsImpl::new)
                .orElseThrow(()-> new UsernameNotFoundException("아이디를 찾을 수 없습니다."));
    }
}
