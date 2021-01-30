package com.yogurt.base.exception;

import com.yogurt.base.exception.enums.ErrorType;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class YogurtOAuthException extends YogurtException {

    public YogurtOAuthException(String name) {
        super(ErrorType.OAUTH, name);
    }

    public YogurtOAuthException(String name, List<Object> errorData) {
        super(ErrorType.OAUTH, name, errorData);
    }

}