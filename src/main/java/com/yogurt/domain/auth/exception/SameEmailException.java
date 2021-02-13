package com.yogurt.domain.auth.exception;


import com.yogurt.global.error.exception.ErrorCode;
import com.yogurt.global.error.exception.InvalidValueException;

public class SameEmailException extends InvalidValueException {

    public SameEmailException() {
        super(ErrorCode.SAME_PASSWORD);
    }
}
