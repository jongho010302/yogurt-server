package com.yogurt.validation;

import com.yogurt.domain.staff.service.admin.AdminStaffService;
import com.yogurt.validation.annotation.StaffValid;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class StaffValidator implements ConstraintValidator<StaffValid, Long> {

    private final AdminStaffService service;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return id == null || service.existsById(id);
    }
}
