package com.yogurt.base.exception;

import com.yogurt.base.exception.enums.ErrorType;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class YogurtBookingTimeExceedException extends YogurtException {

    public YogurtBookingTimeExceedException(String message) {
        super(ErrorType.BOOKING_TIME_EXCEED, message);
    }

    public YogurtBookingTimeExceedException(String message, List<Object> errorData) {
        super(ErrorType.BOOKING_TIME_EXCEED, message, errorData);
    }
}
