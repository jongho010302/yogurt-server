package com.yogurt.domain.ticket.exception;

import com.yogurt.global.error.exception.BusinessException;
import com.yogurt.global.error.exception.ErrorCode;

public class TicketExpireException extends BusinessException {

    public TicketExpireException() {
        super(ErrorCode.TICKET_EXPIRE);
    }
}
