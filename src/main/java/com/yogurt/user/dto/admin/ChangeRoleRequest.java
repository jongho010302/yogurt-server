package com.yogurt.user.dto.admin;

import com.yogurt.validation.annotation.UserRoleValid;
import com.yogurt.validation.annotation.UserValid;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ChangeRoleRequest {

    @UserValid
    @NotNull(message = "유저는 필수 값입니다.")
    private Long userId;

    @UserRoleValid
    @NotEmpty(message = "직급은 필수 값입니다.")
    private String role;
}
