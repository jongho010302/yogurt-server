package com.yogurt.base.exception;

import com.yogurt.base.exception.enums.ErrorType;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class YogurtAlreadyDataUseException extends YogurtException {

    public YogurtAlreadyDataUseException(String name) {
        super(ErrorType.ALREADY_DATA_USE, name);
    }

    public YogurtAlreadyDataUseException(String name, List<Object> errorData) {
        super(ErrorType.ALREADY_DATA_USE, name, errorData);
    }
}
