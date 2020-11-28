package com.yogurt.api.auth.dto;

import com.yogurt.validation.annotation.EmailValid;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class VerifyVerificationCodeRequest {

    @EmailValid
    @NotEmpty(message = "이메일은 필수 값입니다.")
    private String email;

    @NotEmpty(message = "인증코드는 필수 값입니다.")
    private String verificationCode;

    @NotEmpty(message = "인증번호 타입은 필수 값입니다.")
    private String verificationType;
}
