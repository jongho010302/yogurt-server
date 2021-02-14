package com.yogurt.domain.lecture.dto.member;

import com.yogurt.validation.annotation.LectureItemValid;
import com.yogurt.validation.annotation.UserTicketValid;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LectureBookingRequest {

    @UserTicketValid
    @NotNull(message = "회원 수강권은 필수값입니다.")
    private Long userTicketId;

    @LectureItemValid
    @NotNull(message = "수업은 필수값입니다.")
    private Long lectureItemId;
}
