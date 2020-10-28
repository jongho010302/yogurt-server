package com.yogurt.validation;

import com.yogurt.member.service.MemberService;
import com.yogurt.validation.annotation.MemberValid;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class MemberValidator implements ConstraintValidator<MemberValid, Long> {

    private final MemberService service;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return id == null || service.existsById(id);
    }
}
