package com.yogurt.domain.staff.exception;

import com.yogurt.global.error.exception.EntityNotFoundException;

public class StaffNotFoundException extends EntityNotFoundException {

    public StaffNotFoundException(long target) {
        super(target + " is not found");
    }
}
