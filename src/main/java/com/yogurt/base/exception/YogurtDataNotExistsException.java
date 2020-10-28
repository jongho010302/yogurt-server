package com.yogurt.base.exception;

import com.yogurt.base.exception.enums.ErrorType;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class YogurtDataNotExistsException extends YogurtException {

    public YogurtDataNotExistsException(String message) {
        super(ErrorType.DATA_NOT_EXISTS, message);
    }

    public YogurtDataNotExistsException(String message, List<Object> errorData) {
        super(ErrorType.DATA_NOT_EXISTS, message, errorData);
    }
}
