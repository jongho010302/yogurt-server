package com.yogurt.domain.lecture.exception;

import com.yogurt.global.error.exception.BusinessException;
import com.yogurt.global.error.exception.ErrorCode;

public class BookingTimeExceedException extends BusinessException {

    public BookingTimeExceedException() {
        super(ErrorCode.BOOKING_TIME_EXCEED);
    }
}
