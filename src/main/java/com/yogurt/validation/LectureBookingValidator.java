package com.yogurt.validation;

import com.yogurt.api.lecture.service.LectureService;
import com.yogurt.validation.annotation.LectureBookingValid;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class LectureBookingValidator implements ConstraintValidator<LectureBookingValid, Long> {

    private final LectureService service;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return id == null || service.existsLectureBookingById(id);
    }
}
