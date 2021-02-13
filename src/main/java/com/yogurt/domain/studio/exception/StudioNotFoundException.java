package com.yogurt.domain.studio.exception;

import com.yogurt.global.error.exception.EntityNotFoundException;

public class StudioNotFoundException extends EntityNotFoundException {

    public StudioNotFoundException(long target) {
        super(target + " is not found");
    }
}
