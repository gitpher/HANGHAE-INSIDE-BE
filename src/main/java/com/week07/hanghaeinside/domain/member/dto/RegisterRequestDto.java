package com.week07.hanghaeinside.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {

    private String email;

    private String nickname;

    private String password;

    private String passwordConfirm;
}
