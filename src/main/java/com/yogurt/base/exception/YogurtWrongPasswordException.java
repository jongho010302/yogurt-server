package com.yogurt.base.exception;

import com.yogurt.base.exception.enums.ErrorType;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class YogurtWrongPasswordException extends YogurtException {

    public YogurtWrongPasswordException(String name) {
        super(ErrorType.WRONG_PASSWORD, name);
    }

    public YogurtWrongPasswordException(String name, List<Object> errorData) {
        super(ErrorType.WRONG_PASSWORD, name, errorData);
    }
}
