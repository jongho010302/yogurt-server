package com.yogurt.domain.auth.exception;


import com.yogurt.global.error.exception.BusinessException;
import com.yogurt.global.error.exception.EntityNotFoundException;
import com.yogurt.global.error.exception.ErrorCode;

public class EmailNotFoundException extends EntityNotFoundException {

    public EmailNotFoundException(String target) {
        super(target + "is not found");
    }
}
