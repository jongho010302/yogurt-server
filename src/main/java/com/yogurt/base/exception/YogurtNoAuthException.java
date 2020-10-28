package com.yogurt.base.exception;

import com.yogurt.base.exception.enums.ErrorType;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class YogurtNoAuthException extends YogurtException {

    public YogurtNoAuthException(String name) {
        super(ErrorType.NO_AUTH, name);
    }

    public YogurtNoAuthException(String name, List<Object> errorData) {
        super(ErrorType.NO_AUTH, name, errorData);
    }
}
