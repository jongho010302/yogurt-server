package com.yogurt.domain.user.exception;

import com.yogurt.global.error.exception.EntityNotFoundException;

public class UserEmailNotFoundException extends EntityNotFoundException {

    public UserEmailNotFoundException(String target) {
        super(target + " is not found");
    }
}
