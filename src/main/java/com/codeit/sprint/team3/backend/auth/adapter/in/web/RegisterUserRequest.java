package com.codeit.sprint.team3.backend.auth.adapter.in.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterUserRequest {
    @NotBlank(message = "이름은 필수값입니다.")
    private String name;

    @Email(message = "유효한 이메일 주소를 입력하세요")
    @NotBlank(message = "이메일은 필수값입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수값입니다.")
    private String password;

    @NotBlank(message = "닉네임은 필수값입니다.")
    private String nickName;

    private String description;
}
