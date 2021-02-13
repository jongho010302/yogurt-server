package com.yogurt.domain.auth.exception;


import com.yogurt.global.error.exception.ErrorCode;
import com.yogurt.global.error.exception.InvalidValueException;

public class CodeTimeoutException extends InvalidValueException {

    public CodeTimeoutException() {
        super(ErrorCode.CODE_TIMEOUT);
    }
}
