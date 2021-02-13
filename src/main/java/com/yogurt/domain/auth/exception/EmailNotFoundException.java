package com.yogurt.domain.auth.exception;


import com.yogurt.global.error.exception.EntityNotFoundException;

public class EmailNotFoundException extends EntityNotFoundException {

    public EmailNotFoundException(String target) {
        super(target + "is not found");
    }
}
