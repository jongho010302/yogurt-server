package com.yogurt.api.staff.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SaveStaffScheduleRequest {

    @NotNull(message = "요일은 필수값입니다.")
    private int datOfWeek;

    @NotNull(message = "시작시간은 필수값입니다.")
    private String startTime;

    @NotNull(message = "종료시간 필수값입니다.")
    private String endTime;
}
