package com.yogurt.domain.lecture.exception;

import com.yogurt.global.error.exception.BusinessException;
import com.yogurt.global.error.exception.ErrorCode;

public class BookingEntryExceedException extends BusinessException {

    public BookingEntryExceedException() {
        super(ErrorCode.BOOKING_ENTRY);
    }
}
