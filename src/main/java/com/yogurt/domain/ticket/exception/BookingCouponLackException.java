package com.yogurt.domain.ticket.exception;

import com.yogurt.global.error.exception.BusinessException;
import com.yogurt.global.error.exception.ErrorCode;

public class BookingCouponLackException extends BusinessException {

    public BookingCouponLackException() {
        super(ErrorCode.BOOKING_COUPON_LACK);
    }
}
