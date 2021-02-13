package com.yogurt.domain.ticket.dto.admin;

import com.yogurt.domain.base.model.ClassType;
import com.yogurt.domain.studio.domain.Studio;
import com.yogurt.domain.ticket.domain.Ticket;
import com.yogurt.validation.annotation.ClassTypeValid;
import com.yogurt.validation.annotation.StudioValid;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SaveTicketRequest {

    @StudioValid
    @NotNull(message = "센터는 필수 값입니다.")
    private Long studioId;

    @ClassTypeValid
    @NotEmpty(message = "수업 타입은 필수 값입니다.")
    private String classType;

    @NotEmpty(message = "수강권은 필수 값입니다.")
    private String title;

    @NotEmpty(message = "설명은 필수 값입니다.")
    private String description;

    @NotNull(message = "사용기한은 필수 값입니다.")
    private Integer availableDays;

    @NotNull(message = "가격은 필수 값입니다.")
    private Integer price;

    @NotNull(message = "수강 인원은 필수 값입니다.")
    @Range(min = 1, message = "수강 인원은 1보다 작을 수 없습니다.")
    private Integer maxTrainee;

    @NotNull(message = "사용 가능 횟수는 필수 값입니다.")
    @Range(min = 1, message = "사용 가능 횟수는 1보다 작을 수 없습니다.")
    private Integer maxCoupon;

    @NotNull(message = "취소 가능 횟수는 필수 값입니다.")
    @Range(min = 1, message = "취소 가능 횟수는 1보다 작을 수 없습니다.")
    private Integer maxCancel;

    @NotNull(message = "주간 이용 횟수는 필수 값입니다.")
    @Range(min = 0, message = "주간 이용 횟수는 0보다 작을 수 없습니다.")
    private Integer bookingLimitPerWeek;

    @NotNull(message = "월간 이용 횟수는 필수 값입니다.")
    @Range(min = 0, message = "월간 이용 횟수는 0보다 작을 수 없습니다.")
    private Integer bookingLimitPerMonth;

    @NotNull(message = "당일 예약 변경 횟수는 필수 값입니다.")
    @Range(min = 0, message = "당일 예약 변경 횟수는 0보다 작을 수 없습니다.")
    private Integer dailyBookingChangeLimit;

    @NotNull(message = "예약 가능 시간 (시작)은 필수 값입니다.")
    @Range(min = 0, message = "예약 가능 시간 (시작)은 0보다 작을 수 없습니다.")
    private Integer bookingStartTime;

    @NotNull(message = "예약 가능 시간 (종료)은 필수 값입니다.")
    @Range(min = 0, message = "예약 가능 시간 (종료)은 0보다 작을 수 없습니다.")
    private Integer bookingEndTime;

    @NotNull(message = "판매 여부는 필수 값입니다.")
    private Boolean isSelling;

    public Ticket toEntity() {
        Studio studio = new Studio();
        studio.setId(studioId);

        return Ticket.builder()
                .studioId(studioId)
                .classType(ClassType.of(classType))
                .title(title)
                .description(description)
                .availableDays(availableDays)
                .maxTrainee(maxTrainee)
                .price(price)
                .maxCoupon(maxCoupon)
                .maxCancel(maxCancel)
                .bookingLimitPerWeek(bookingLimitPerWeek)
                .bookingLimitPerMonth(bookingLimitPerMonth)
                .dailyBookingChangeLimit(dailyBookingChangeLimit)
                .bookingStartTime(bookingStartTime)
                .bookingEndTime(bookingEndTime)
                .isSelling(isSelling)
                .build();
    }
}
