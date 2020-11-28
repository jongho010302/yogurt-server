package com.yogurt.api.lecture.dto;

import com.yogurt.validation.annotation.LectureBookingValid;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LectureCancelRequest {

    @LectureBookingValid
    @NotNull(message = "수업 예약은 필수값입니다.")
    private Long lectureBookingId;
}
