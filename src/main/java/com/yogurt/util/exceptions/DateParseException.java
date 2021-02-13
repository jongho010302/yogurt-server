package com.yogurt.util.exceptions;


import com.yogurt.global.error.exception.ErrorCode;
import com.yogurt.global.error.exception.InvalidValueException;

public class DateParseException extends InvalidValueException {

    public DateParseException() {
        super(ErrorCode.DATE_PARSE);
    }
}
