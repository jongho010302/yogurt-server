package com.yogurt.lecture.dto;

import com.yogurt.validation.annotation.LectureItemValid;
import com.yogurt.validation.annotation.MemberTicketValid;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LectureBookingRequest {

    @MemberTicketValid
    @NotNull(message = "회원 수강권은 필수값입니다.")
    private Long memberTicketId;

    @LectureItemValid
    @NotNull(message = "수업은 필수값입니다.")
    private Long lectureItemId;
}
