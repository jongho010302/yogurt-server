package com.yogurt.domain.lecture.exception;

import com.yogurt.global.error.exception.EntityNotFoundException;

public class LectureItemNotFoundException extends EntityNotFoundException {

    public LectureItemNotFoundException(long target) {
        super(target + " is not found");
    }
}
