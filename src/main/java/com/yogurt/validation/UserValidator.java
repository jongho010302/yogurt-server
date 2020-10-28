package com.yogurt.validation;

import com.yogurt.user.service.UserService;
import com.yogurt.validation.annotation.UserValid;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UserValidator implements ConstraintValidator<UserValid, Long> {

    private final UserService service;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return id == null || service.existsById(id);
    }
}
