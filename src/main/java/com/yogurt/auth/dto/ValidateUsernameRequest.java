package com.yogurt.auth.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ValidateUsernameRequest {

    @NotEmpty(message = "이메일은 필수 값입니다.")
    private String username;
}
