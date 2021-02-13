package com.yogurt.domain.user.exception;


import com.yogurt.global.error.exception.ErrorCode;
import com.yogurt.global.error.exception.InvalidValueException;

public class SamePasswordException extends InvalidValueException {

    public SamePasswordException() {
        super(ErrorCode.SAME_PASSWORD);
    }
}
