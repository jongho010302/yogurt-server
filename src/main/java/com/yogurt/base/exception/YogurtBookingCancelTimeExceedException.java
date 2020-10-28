package com.yogurt.base.exception;

import com.yogurt.base.exception.enums.ErrorType;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class YogurtBookingCancelTimeExceedException extends YogurtException {

    public YogurtBookingCancelTimeExceedException(String message) {
        super(ErrorType.BOOKING_CANCEL_TIME_EXCEED, message);
    }

    public YogurtBookingCancelTimeExceedException(String message, List<Object> errorData) {
        super(ErrorType.BOOKING_CANCEL_TIME_EXCEED, message, errorData);
    }
}
