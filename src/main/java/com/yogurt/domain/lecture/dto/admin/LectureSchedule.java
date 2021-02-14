package com.yogurt.domain.lecture.dto.admin;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LectureSchedule {

    @NotNull(message = "요일은 필수값입니다.")
    private int dayOfWeek;

    @NotNull(message = "수업여부는 필수값입니다.")
    private Boolean hasClass;

    @NotNull(message = "시작시간은 필수값입니다.")
    private String startTime;

    @NotNull(message = "종료시간 필수값입니다.")
    private String endTime;
}
