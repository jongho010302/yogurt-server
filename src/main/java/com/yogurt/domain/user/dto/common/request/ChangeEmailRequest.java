package com.yogurt.domain.user.dto.common.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ChangeEmailRequest {

    @NotEmpty(message = "이메일은 필수 값입니다.")
    private String email;

    @NotEmpty(message = "인증코드는 필수 값입니다.")
    private String verificationCode;
}
