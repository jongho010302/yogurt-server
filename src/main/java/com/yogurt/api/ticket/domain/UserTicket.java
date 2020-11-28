package com.yogurt.api.ticket.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.yogurt.base.exception.YogurtInvalidParamException;
import com.yogurt.base.exception.YogurtLackOfCouponCountException;
import com.yogurt.generic.base.BaseEntity;
import com.yogurt.api.user.domain.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserTicket extends BaseEntity {

    @ManyToOne
    private Ticket ticket;

    @JsonBackReference
    @ManyToOne
    private User user;

    // 총 이용 횟수
    @Column(nullable = false)
    private int maxCoupon;

    // 취소 가능 횟수
    @Column(nullable = false)
    private int maxCancel;

    // 남은 이용 횟수
    @Column(nullable = false)
    private int remainingCoupon;

    // 남은 취소 가능 횟수
    @Column(nullable = false)
    private int remainingCancel;

    // 이용 시작일
    @Column(nullable = false)
    private String startDate;

    // 이용 종료일
    @Column(nullable = false)
    private String endDate;

    @Column(nullable = false)
    private Boolean isDeactivated;

    public void validateUserOwner(long userId) {
        if (!this.isUserOwner(userId)) {
            throw new YogurtInvalidParamException("자신의 수강권이 아닙니다.");
        }
    }

    private boolean isUserOwner(long userId) {
        return this.getUser().getId().equals(userId);
    }

    public void validateRemainingCoupon() {
        if (!this.hasRemainingCoupon()) {
            throw new YogurtLackOfCouponCountException("예약 가능 횟수를 넘어섰습니다. 다른 수강권을 이용해주세요.");
        }
    }

    private boolean hasRemainingCoupon() {
        return this.getRemainingCoupon() > 0;
    }

    public void validateRemainingCancel() {
        if (!this.hasRemainingCancel()) {
            throw new YogurtLackOfCouponCountException("예약 취소 가능 횟수를 넘어섰습니다. 다른 수강권을 이용해주세요.");
        }
    }

    private boolean hasRemainingCancel() {
        return this.getRemainingCancel() > 0;
    }

    public void booked() {
        subtractCouponCount();
    }

    private void subtractCouponCount() {
        this.setRemainingCoupon(this.getRemainingCoupon() - 1);
    }

    public void canceled() {
        subtractCancelCount();
        addCouponCount();
    }

    private void subtractCancelCount() {
        this.setRemainingCancel(this.getRemainingCancel() - 1);
    }

    private void addCouponCount() {
        this.setRemainingCoupon(this.getRemainingCoupon() - 1);
    }
}
