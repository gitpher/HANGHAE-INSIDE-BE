package com.week07.hanghaeinside.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor  // 기본 생성자를 만들어줌
@AllArgsConstructor // 모든 생성자를 만들어줌
public class PostRequestDto {

    private String title;
    private String content;
    //MultipartFile이라는 인터페이스로 멀티파트 파일을 매우 편리하게 지원
    private MultipartFile postImg;

    /*AllArgsConstructor이 있으면 필드 수에 상관없이 자동으로 넣어주지는 않고
    UserDTO(id,name,email,age,mobile,profile_image,gender,birthyear,birthday){
        this.id=id;
        이런식으로 this.email, age 다 만들어주는거임
    }*/
}
