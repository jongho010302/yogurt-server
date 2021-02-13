package com.yogurt.domain.ticket.exception;

import com.yogurt.global.error.exception.EntityNotFoundException;

public class TicketNotFoundException extends EntityNotFoundException {

    public TicketNotFoundException(long target) {
        super(target + " is not found");
    }
}
