package com.yogurt.validation;

import com.yogurt.ticket.service.UserTicketService;
import com.yogurt.validation.annotation.UserTicketValid;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UserTicketValidator implements ConstraintValidator<UserTicketValid, Long> {

    private final UserTicketService service;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return id == null || service.existsById(id);
    }
}
