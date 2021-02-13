package com.yogurt.domain.auth.exception;


import com.yogurt.global.error.exception.ErrorCode;
import com.yogurt.global.error.exception.InvalidValueException;

public class CodeNotSentException extends InvalidValueException {

    public CodeNotSentException() {
        super(ErrorCode.CODE_NOT_SENT);
    }
}
