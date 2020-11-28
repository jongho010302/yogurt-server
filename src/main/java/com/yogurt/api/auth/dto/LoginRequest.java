package com.yogurt.api.auth.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginRequest {

    @NotEmpty(message = "아이디는 필수 값입니다.")
    private String username;

    @NotEmpty(message = "비밀번호는 필수 값입니다.")
    private String password;
}
