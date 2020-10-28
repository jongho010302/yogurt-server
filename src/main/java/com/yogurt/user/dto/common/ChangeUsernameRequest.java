package com.yogurt.user.dto.common;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ChangeUsernameRequest {

    @NotEmpty(message = "아이디는 필수 값입니다.")
    private String username;
}
