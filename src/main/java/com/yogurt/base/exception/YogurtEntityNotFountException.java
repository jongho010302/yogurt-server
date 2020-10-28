package com.yogurt.base.exception;

import com.yogurt.base.exception.enums.ErrorType;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class YogurtEntityNotFountException extends YogurtException {

    public YogurtEntityNotFountException(String name) {
        super(ErrorType.ENTITY_NOT_FOUND, name);
    }

    public YogurtEntityNotFountException(String name, List<Object> errorData) {
        super(ErrorType.ENTITY_NOT_FOUND, name, errorData);
    }

}