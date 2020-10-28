package com.yogurt.base.exception;

import com.yogurt.base.exception.enums.ErrorType;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class YogurtFileUploadException extends YogurtException {

    public YogurtFileUploadException(String name) {
        super(ErrorType.FILE_UPLOAD, name);
    }

    public YogurtFileUploadException(String name, List<Object> errorData) {
        super(ErrorType.FILE_UPLOAD, name, errorData);
    }

}