package com.yogurt.base.exception;

import com.yogurt.base.exception.enums.ErrorType;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class YogurtDateParsingException extends YogurtException {

    public YogurtDateParsingException(String message) {
        super(ErrorType.DATE_PARSING, message);
    }

    public YogurtDateParsingException(String message, List<Object> errorData) {
        super(ErrorType.DATE_PARSING, message, errorData);
    }
}
