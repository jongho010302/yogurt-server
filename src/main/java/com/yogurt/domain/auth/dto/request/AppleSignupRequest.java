package com.yogurt.domain.auth.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class AppleSignupRequest {

    @NotEmpty(message = "idToken은 필수 값입니다.")
    private Long idToken;

    @NotEmpty(message = "nonce는 필수 값입니다.")
    private String nonce;

    @NotEmpty(message = "이름은 필수 값입니다.")
    private String name;
}
