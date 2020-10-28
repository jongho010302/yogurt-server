package com.yogurt.member.dto;

import com.yogurt.member.domain.Member;
import com.yogurt.ticket.domain.MemberTicket;
import com.yogurt.ticket.domain.Ticket;
import com.yogurt.validation.annotation.DateValid;
import com.yogurt.validation.annotation.MemberValid;
import com.yogurt.validation.annotation.TicketValid;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SaveMemberTicketRequest {

    @MemberValid
    @NotNull(message = "사용자는 필수 값입니다.")
    private Long memberId;

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

    public MemberTicket toEntity(Member member, Ticket ticket) {
        return MemberTicket.builder()
                .ticket(ticket)
                .member(member)
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
