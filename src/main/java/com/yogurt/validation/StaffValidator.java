package com.yogurt.validation;

import com.yogurt.api.staff.service.StaffService;
import com.yogurt.validation.annotation.StaffValid;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class StaffValidator implements ConstraintValidator<StaffValid, Long> {

    private final StaffService service;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return id == null || service.existsById(id);
    }
}
