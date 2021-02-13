package com.yogurt.domain.auth.exception;


import com.yogurt.global.error.exception.ErrorCode;
import com.yogurt.global.error.exception.InvalidValueException;

public class OAuthException extends InvalidValueException {

    public OAuthException() {
        super(ErrorCode.OAUTH);
    }
}
