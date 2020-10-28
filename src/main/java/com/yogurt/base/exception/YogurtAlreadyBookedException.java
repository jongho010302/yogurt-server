package com.yogurt.base.exception;

import com.yogurt.base.exception.enums.ErrorType;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class YogurtAlreadyBookedException extends YogurtException {

    public YogurtAlreadyBookedException(String message) {
        super(ErrorType.ALREADY_BOOKED, message);
    }

    public YogurtAlreadyBookedException(String message, List<Object> errorData) {
        super(ErrorType.ALREADY_BOOKED, message, errorData);
    }
}
