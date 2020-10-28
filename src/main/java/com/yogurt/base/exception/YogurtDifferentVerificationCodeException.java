package com.yogurt.base.exception;

import com.yogurt.base.exception.enums.ErrorType;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class YogurtDifferentVerificationCodeException extends YogurtException {

    public YogurtDifferentVerificationCodeException(String name) {
        super(ErrorType.DIFFERENT_VERIFY_CODE, name);
    }

    public YogurtDifferentVerificationCodeException(String name, List<Object> errorData) {
        super(ErrorType.DIFFERENT_VERIFY_CODE, name, errorData);
    }

}