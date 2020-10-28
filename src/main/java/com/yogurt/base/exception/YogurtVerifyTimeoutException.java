package com.yogurt.base.exception;

import com.yogurt.base.exception.enums.ErrorType;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class YogurtVerifyTimeoutException extends YogurtException {

    public YogurtVerifyTimeoutException(String name) {
        super(ErrorType.VERIFY_TIMEOUT, name);
    }

    public YogurtVerifyTimeoutException(String name, List<Object> errorData) {
        super(ErrorType.VERIFY_TIMEOUT, name, errorData);
    }
}
