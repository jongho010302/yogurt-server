package com.yogurt.base.exception;

import com.yogurt.base.exception.enums.ErrorType;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class YogurtInvalidSameEmailException extends YogurtException {

    public YogurtInvalidSameEmailException(String name) {
        super(ErrorType.INVALID_SAME_EMAIL, name);
    }

    public YogurtInvalidSameEmailException(String name, List<Object> errorData) {
        super(ErrorType.INVALID_SAME_EMAIL, name, errorData);
    }
}
