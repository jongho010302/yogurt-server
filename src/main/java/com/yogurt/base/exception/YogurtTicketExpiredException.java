package com.yogurt.base.exception;

import com.yogurt.base.exception.enums.ErrorType;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class YogurtTicketExpiredException extends YogurtException {

    public YogurtTicketExpiredException(String name) {
        super(ErrorType.TICKET_EXPIRED, name);
    }

    public YogurtTicketExpiredException(String name, List<Object> errorData) {
        super(ErrorType.TICKET_EXPIRED, name, errorData);
    }
}
