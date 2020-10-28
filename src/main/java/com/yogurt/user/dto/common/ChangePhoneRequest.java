package com.yogurt.user.dto.common;

import com.yogurt.validation.annotation.PhoneValid;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ChangePhoneRequest {

    @PhoneValid
    @NotEmpty(message = "핸드폰 번호는 필수 값입니다.")
    private String phone;
}
