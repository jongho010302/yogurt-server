package com.yogurt.staff.dto;

import com.yogurt.validation.annotation.StaffValid;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DeleteStaffRequest {

    @StaffValid
    @NotNull(message = "스태프는 필수 값입니다.")
    private Long staffId;
}
