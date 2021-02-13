package com.yogurt.domain.ticket.exception;

import com.yogurt.global.error.exception.BusinessException;
import com.yogurt.global.error.exception.ErrorCode;

public class BookingCancelCouponLackException extends BusinessException {

    public BookingCancelCouponLackException() {
        super(ErrorCode.BOOKING_CANCEL_COUPON_LACK);
    }
}
