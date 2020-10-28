package com.yogurt.base.exception;

import com.yogurt.base.exception.enums.ErrorType;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class YogurtNotFoundException extends YogurtException {

    public YogurtNotFoundException(String name) {
        super(ErrorType.NOT_FOUND, name);
    }

    public YogurtNotFoundException(String name, List<Object> errorData) {
        super(ErrorType.NOT_FOUND, name, errorData);
    }
}
