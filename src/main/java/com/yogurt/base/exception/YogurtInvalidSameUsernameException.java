package com.yogurt.base.exception;

import com.yogurt.base.exception.enums.ErrorType;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class YogurtInvalidSameUsernameException extends YogurtException {

    public YogurtInvalidSameUsernameException(String name) {
        super(ErrorType.INVALID_SAME_USERNAME, name);
    }

    public YogurtInvalidSameUsernameException(String name, List<Object> errorData) {
        super(ErrorType.INVALID_SAME_USERNAME, name, errorData);
    }

}