package com.yogurt.base.exception;

import com.yogurt.base.exception.enums.ErrorType;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class YogurtBookingEntryExceedException extends YogurtException {

    public YogurtBookingEntryExceedException(String message) {
        super(ErrorType.BOOKING_ENTRY_EXCEED, message);
    }

    public YogurtBookingEntryExceedException(String message, List<Object> errorData) {
        super(ErrorType.BOOKING_ENTRY_EXCEED, message, errorData);
    }
}
