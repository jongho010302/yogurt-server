package com.yogurt.api.user.dto.common;

import com.yogurt.validation.annotation.PasswordValid;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ChangePasswordRequest {

    @PasswordValid
    @NotEmpty(message = "비밀번호는 필수 값입니다.")
    private String password;
}
