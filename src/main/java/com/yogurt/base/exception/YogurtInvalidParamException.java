package com.yogurt.base.exception;

import com.yogurt.base.exception.enums.ErrorType;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class YogurtInvalidParamException extends YogurtException {

    public YogurtInvalidParamException(String name) {
        super(ErrorType.INVALID_PARAM, name);
    }

    public YogurtInvalidParamException(String name, List<Object> errorData) {
        super(ErrorType.INVALID_PARAM, name, errorData);
    }
}
