package com.yogurt.base.exception.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
public class ErrorInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String errorCode;

    private String errorMessage;

    private String message;

    private Object errorData = new ArrayList<>();

    public ErrorInfo(String errorCode, String errorMessage, String message) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.message = message;
    }

    public ErrorInfo(String errorCode, String errorMessage, String message, Object errorData) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.message = message;
        this.errorData = errorData;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
