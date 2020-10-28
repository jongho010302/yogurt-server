package com.yogurt.base.exception;

import com.yogurt.base.exception.enums.ErrorType;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class YogurtFileSizeException extends YogurtException {

    public YogurtFileSizeException(String name) {
        super(ErrorType.FILE_SIZE, name);
    }

    public YogurtFileSizeException(String name, List<Object> errorData) {
        super(ErrorType.FILE_SIZE, name, errorData);
    }

}