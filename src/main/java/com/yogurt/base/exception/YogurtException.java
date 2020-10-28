package com.yogurt.base.exception;

import com.yogurt.base.exception.entity.ErrorInfo;
import com.yogurt.base.exception.interfaces.InterfaceErrorType;

import java.util.List;

public class YogurtException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final ErrorInfo errorInfo;


    public YogurtException(InterfaceErrorType errorType, String message) {
        this.errorInfo = new ErrorInfo(errorType.toString(), errorType.getErrorMessage(), message);
    }

    public YogurtException(InterfaceErrorType errorType, String message, List<Object> errorData) {
        this.errorInfo = new ErrorInfo(errorType.toString(), errorType.getErrorMessage(), message, errorData);
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public String getErrorCode() {
        return this.errorInfo.getErrorCode();
    }

    public Object getErrorData() {
        return this.errorInfo.getErrorData();
    }

    public String getErrorMessage() {
        return this.errorInfo.getErrorMessage();
    }

    public String getMessage() {
        return this.errorInfo.getMessage();
    }
}
