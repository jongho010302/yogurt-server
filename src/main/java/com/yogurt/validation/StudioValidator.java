package com.yogurt.validation;

import com.yogurt.domain.studio.service.admin.AdminStudioService;
import com.yogurt.validation.annotation.StudioValid;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class StudioValidator implements ConstraintValidator<StudioValid, Long> {

    private final AdminStudioService service;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return id == null || service.existsById(id);
    }
}
