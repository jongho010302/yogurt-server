package com.yogurt.validation;

import com.yogurt.domain.ticket.service.admin.AdminTicketService;
import com.yogurt.validation.annotation.TicketValid;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class TicketValidator implements ConstraintValidator<TicketValid, Long> {

    private final AdminTicketService service;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return id == null || service.existsById(id);
    }
}
