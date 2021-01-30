package com.yogurt.api.auth.dto;

import com.yogurt.validation.annotation.StudioValid;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AppleLoginRequest {

    @StudioValid
    @NotNull(message = "센터는 필수 값입니다.")
    private Long studioId;

    @NotEmpty(message = "idToken은 필수 값입니다.")
    private Long idToken;

    @NotEmpty(message = "nonce는 필수 값입니다.")
    private String nonce;
}
