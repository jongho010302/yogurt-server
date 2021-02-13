package com.yogurt.domain.lecture.exception;

import com.yogurt.global.error.exception.BusinessException;
import com.yogurt.global.error.exception.ErrorCode;

public class BookingCancelTimeExceedException extends BusinessException {

    public BookingCancelTimeExceedException() {
        super(ErrorCode.BOOKING_CANCEL_TIME_EXCEED);
    }
}
