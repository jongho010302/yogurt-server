package com.yogurt.validation;

import com.yogurt.ticket.service.MemberTicketService;
import com.yogurt.validation.annotation.MemberTicketValid;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class MemberTicketValidator implements ConstraintValidator<MemberTicketValid, Long> {

    private final MemberTicketService service;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return id == null || service.existsById(id);
    }
}
