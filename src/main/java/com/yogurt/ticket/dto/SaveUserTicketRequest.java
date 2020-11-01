package com.yogurt.ticket.dto;

import com.yogurt.ticket.domain.Ticket;
import com.yogurt.ticket.domain.UserTicket;
import com.yogurt.user.domain.User;
import com.yogurt.validation.annotation.DateValid;
import com.yogurt.validation.annotation.TicketValid;
import com.yogurt.validation.annotation.UserValid;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SaveUserTicketRequest {

    @UserValid
    @NotNull(message = "사용자는 필수 값입니다.")
    private Long userId;

    @TicketValid
    @NotNull(message = "수강권은 필수 값입니다.")
    private Long ticketId;

    @NotNull(message = "남은 횟수 필수 값입니다.")
    private Integer remainingCoupon;

    @NotNull(message = "예약 취소 가능 횟수는 필수 값입니다.")
    private Integer remainingCancel;

    @DateValid(message = "이용 시작일의 형식을 맞춰주세요.")
    @NotEmpty(message = "이용 시작일은 필수 값입니다.")
    private String startDate;

    @DateValid(message = "이용 종료일의 형식을 맞춰주세요.")
    @NotEmpty(message = "이용 종료일은 필수 값입니다.")
    private String endDate;

    public UserTicket toEntity(User user, Ticket ticket) {
        return UserTicket.builder()
                .ticket(ticket)
                .user(user)
                .maxCoupon(remainingCoupon)
                .maxCancel(remainingCancel)
                .remainingCoupon(remainingCoupon)
                .remainingCancel(remainingCancel)
                .startDate(startDate)
                .endDate(endDate)
                .isDeactivated(false)
                .build();
    }
}
