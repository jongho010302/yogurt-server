package com.yogurt.domain.auth.exception;


import com.yogurt.global.error.exception.ErrorCode;
import com.yogurt.global.error.exception.InvalidValueException;

public class WrongPasswordException extends InvalidValueException {

    public WrongPasswordException() {
        super(ErrorCode.WRONG_PASSWORD);
    }
}
