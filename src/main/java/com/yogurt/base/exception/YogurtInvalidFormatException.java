package com.yogurt.base.exception;

import com.yogurt.base.exception.enums.ErrorType;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class YogurtInvalidFormatException extends YogurtException {

    public YogurtInvalidFormatException(String name) {
        super(ErrorType.INVALID_FORMAT, name);
    }

    public YogurtInvalidFormatException(String name, List<Object> errorData) {
        super(ErrorType.INVALID_FORMAT, name, errorData);
    }
}
