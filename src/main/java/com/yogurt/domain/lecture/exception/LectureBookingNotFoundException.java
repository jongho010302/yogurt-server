package com.yogurt.domain.lecture.exception;

import com.yogurt.global.error.exception.EntityNotFoundException;

public class LectureBookingNotFoundException extends EntityNotFoundException {

    public LectureBookingNotFoundException(long target) {
        super(target + " is not found");
    }
}
