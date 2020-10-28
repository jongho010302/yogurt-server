package com.yogurt.staff.dto;

import com.yogurt.validation.annotation.UserValid;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ResetStaffPasswordRequest {

    @UserValid
    @NotNull(message = "유저는 필수 값입니다.")
    private Long userId;
}
