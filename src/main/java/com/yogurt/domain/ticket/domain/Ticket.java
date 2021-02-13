package com.yogurt.domain.ticket.domain;

import com.yogurt.domain.base.entity.BaseEntity;
import com.yogurt.domain.base.model.ClassType;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Ticket extends BaseEntity {

    @Column(nullable = false)
    private Long studioId;

    // 강의 타입 (GROUP: 그룹, PRIVATE: 프라이빗[개인, 듀엣, 트리플])
    private ClassType classType;

    // 수강권 이름
    @Column(nullable = false)
    private String title;

    // 설명
    @Column(columnDefinition = "TEXT")
    private String description;

    // 가격
    @Column(nullable = false)
    private int price;

    // 이용 가능한 날짜 ex) 120: 120일
    @Column(nullable = false)
    private int availableDays;

    // 수강 인원
    @Column(nullable = false)
    private int maxTrainee;

    // 총 이용 횟수
    @Column(nullable = false)
    private int maxCoupon;

    // 취소 가능 횟수
    @Column(nullable = false)
    private int maxCancel;

    // 주간 이용 횟수
    @Column(nullable = false)
    private int bookingLimitPerWeek;

    // 월간 이용 횟수
    @Column(nullable = false)
    private int bookingLimitPerMonth;

    // 당일 예약 변경 횟수
    @Column(nullable = false)
    private int dailyBookingChangeLimit;

    // 예약 가능 시간 (시작)
    @Column(nullable = false)
    private int bookingStartTime;

    // 예약 가능 시간 (종료)
    @Column(nullable = false)
    private int bookingEndTime;

    // 판매 여부
    @Column(nullable = false)
    private Boolean isSelling;

    public String getClassType() {
        return classType.getClassType();
    }
}
