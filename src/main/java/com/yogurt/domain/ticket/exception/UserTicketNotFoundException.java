package com.yogurt.domain.ticket.exception;

import com.yogurt.global.error.exception.EntityNotFoundException;

public class UserTicketNotFoundException extends EntityNotFoundException {

    public UserTicketNotFoundException(long target) {
        super(target + " is not found");
    }
}
