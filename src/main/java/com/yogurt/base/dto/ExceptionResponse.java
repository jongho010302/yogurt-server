package com.yogurt.base.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {
    private boolean success;
    private String errorCode;
    private String errorMessage;
    private Object errorData;
    private String message;

    public ExceptionResponse(String errorCode, String errorMessage, Object errorData, String message) {
        this.success = false;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorData = errorData;
        this.message = message;
    }
}
