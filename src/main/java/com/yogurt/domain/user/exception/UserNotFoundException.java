package com.yogurt.domain.user.exception;

import com.yogurt.global.error.exception.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(long target) {
        super(target + " is not found");
    }
}
