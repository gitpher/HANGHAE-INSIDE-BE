package com.week07.hanghaeinside.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
    private String memberNickname;
    private String memberEmail;
    private String memberPassword;
    private String memberPasswordConfirm;
}
