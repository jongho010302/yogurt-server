package com.yogurt.base.exception;

import com.yogurt.base.exception.enums.ErrorType;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class YogurtNotVerifiedEmailException extends YogurtException {

    public YogurtNotVerifiedEmailException(String name) {
        super(ErrorType.NOT_VERIFIED_EMAIL, name);
    }

    public YogurtNotVerifiedEmailException(String name, List<Object> errorData) {
        super(ErrorType.NOT_VERIFIED_EMAIL, name, errorData);
    }
}
