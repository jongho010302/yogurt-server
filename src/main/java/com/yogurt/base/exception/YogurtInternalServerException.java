package com.yogurt.base.exception;

import com.yogurt.base.exception.enums.ErrorType;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class YogurtInternalServerException extends YogurtException {

    public YogurtInternalServerException(String name) {
        super(ErrorType.INTERNAL_SERVER, name);
    }

    public YogurtInternalServerException(String name, List<Object> errorData) {
        super(ErrorType.INTERNAL_SERVER, name, errorData);
    }
}
