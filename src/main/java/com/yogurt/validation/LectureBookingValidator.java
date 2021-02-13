package com.yogurt.validation;

import com.yogurt.domain.lecture.service.admin.AdminLectureService;
import com.yogurt.domain.lecture.service.common.CommonLectureService;
import com.yogurt.validation.annotation.LectureBookingValid;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class LectureBookingValidator implements ConstraintValidator<LectureBookingValid, Long> {

    private final CommonLectureService service;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return id == null || service.existsLectureBookingById(id);
    }
}
