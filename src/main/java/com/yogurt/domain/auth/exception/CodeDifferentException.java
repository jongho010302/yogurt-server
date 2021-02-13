package com.yogurt.domain.auth.exception;


import com.yogurt.global.error.exception.ErrorCode;
import com.yogurt.global.error.exception.InvalidValueException;

public class CodeDifferentException extends InvalidValueException {

    public CodeDifferentException() {
        super(ErrorCode.CODE_DIFFERENT);
    }
}
