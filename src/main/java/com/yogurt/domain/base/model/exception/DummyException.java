package com.yogurt.domain.base.model.exception;


import com.yogurt.global.error.exception.ErrorCode;
import com.yogurt.global.error.exception.InvalidValueException;

public class DummyException extends InvalidValueException {

    public DummyException(String ang) {
        super(ErrorCode.CODE_TIMEOUT);
    }
}
