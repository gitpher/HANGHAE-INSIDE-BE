package com.week07.hanghaeinside.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {

    @NotBlank(message = "이메일 주소를 입력해주세요.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String memberEmail;

    @NotBlank(message = "닉네임을 입력해주세요")
    private String memberNickname;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, max = 32, message = "영어 대소문자, 특수문자(!@#$%^&*)를 이용한 비밀번호는 8자 이상 32자 이하로 입력해주세요.")
    @Pattern(regexp = "[a-zA-Z\\d!@#$%^&*]*${8,32}")
    private String memberPassword;

    @NotBlank(message = "비밀번호를 확인해주세요.")
    private String memberPasswordConfirm;
}
