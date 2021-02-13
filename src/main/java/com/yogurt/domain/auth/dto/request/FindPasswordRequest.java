package com.yogurt.domain.auth.dto.request;

import com.yogurt.validation.annotation.EmailValid;
import com.yogurt.validation.annotation.PasswordValid;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class FindPasswordRequest {

    @EmailValid
    @NotEmpty(message = "이메일은 필수 값입니다.")
    private String email;

    @NotEmpty(message = "인증코드는 필수 값입니다.")
    private String verificationCode;

    @PasswordValid
    @NotEmpty(message = "비밀번호는 필수 값입니다.")
    private String password;
}
