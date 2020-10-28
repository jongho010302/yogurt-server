package com.yogurt.base.exception;

import com.yogurt.base.exception.enums.ErrorType;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class YogurtStudioException extends YogurtException {

    public YogurtStudioException(String message) {
        super(ErrorType.STUDIO, message);
    }

    public YogurtStudioException(String message, List<Object> errorData) {
        super(ErrorType.STUDIO, message, errorData);
    }
}
