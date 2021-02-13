package com.yogurt.validation;

import com.yogurt.domain.ticket.service.admin.AdminUserTicketService;
import com.yogurt.validation.annotation.UserTicketValid;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UserTicketValidator implements ConstraintValidator<UserTicketValid, Long> {

    private final AdminUserTicketService service;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return id == null || service.existsById(id);
    }
}
