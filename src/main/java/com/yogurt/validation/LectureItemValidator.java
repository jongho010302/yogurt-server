package com.yogurt.validation;

import com.yogurt.domain.lecture.service.common.CommonLectureService;
import com.yogurt.validation.annotation.LectureItemValid;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class LectureItemValidator implements ConstraintValidator<LectureItemValid, Long> {

    private final CommonLectureService service;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return id == null || service.existsLectureItemById(id);
    }
}
