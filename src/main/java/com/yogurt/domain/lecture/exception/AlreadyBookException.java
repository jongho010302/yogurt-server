package com.yogurt.domain.lecture.exception;

import com.yogurt.global.error.exception.BusinessException;
import com.yogurt.global.error.exception.ErrorCode;

public class AlreadyBookException extends BusinessException {

    public AlreadyBookException() {
        super(ErrorCode.ALREADY_BOOK);
    }
}
