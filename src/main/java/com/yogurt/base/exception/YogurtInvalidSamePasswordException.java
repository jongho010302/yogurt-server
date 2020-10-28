package com.yogurt.base.exception;

import com.yogurt.base.exception.enums.ErrorType;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class YogurtInvalidSamePasswordException extends YogurtException {

    public YogurtInvalidSamePasswordException(String name) {
        super(ErrorType.INVALID_SAME_PASSWORD, name);
    }

    public YogurtInvalidSamePasswordException(String name, List<Object> errorData) {
        super(ErrorType.INVALID_SAME_PASSWORD, name, errorData);
    }
}
