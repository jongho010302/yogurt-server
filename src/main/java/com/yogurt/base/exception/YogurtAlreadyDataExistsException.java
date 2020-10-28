package com.yogurt.base.exception;

import com.yogurt.base.exception.enums.ErrorType;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class YogurtAlreadyDataExistsException extends YogurtException {

    public YogurtAlreadyDataExistsException(String message) {
        super(ErrorType.ALREADY_DATA_EXISTS, message);
    }

    public YogurtAlreadyDataExistsException(String message, List<Object> errorData) {
        super(ErrorType.ALREADY_DATA_EXISTS, message, errorData);
    }
}
