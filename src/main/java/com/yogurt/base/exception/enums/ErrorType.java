package com.yogurt.base.exception.enums;

import com.yogurt.base.exception.interfaces.InterfaceErrorType;

public enum ErrorType implements InterfaceErrorType {
    ALREADY_BOOKED("already.booked"),
    ALREADY_DATA_EXISTS("already.data.exists"),
    ALREADY_DATA_USE("already.data.use"),
    DATE_PARSING("data.parsing"),
    DATA_NOT_EXISTS("data.not.exists"),
    DIFFERENT_VERIFY_CODE("different.verify.code"),
    OAUTH("oauth"),
    ENTITY_NOT_FOUND("entity.not.found"),
    BOOKING_ENTRY_EXCEED("booking.entry.exceed"),
    BOOKING_TIME_EXCEED("booking.time.exceed"),
    BOOKING_CANCEL_TIME_EXCEED("booking.cancel.time.exceed"),
    FILE_SIZE("file.size"),
    FILE_UPLOAD("file.upload"),
    INVALID_FORMAT("invalid.class.type.format"),
    TICKET_EXPIRED("ticket.expired"),
    INVALID_SAME_EMAIL("invalid.same.email"),
    INVALID_SAME_PASSWORD("invalid.same.password"),
    INVALID_PARAM("invalid.param"),
    INTERNAL_SERVER("internal.server"),
    LACK_OF_COUPON_COUNT("lack.of.coupon.count"),
    NO_AUTH("no.auth"),
    NOT_FOUND("not.found"),
    NOT_VERIFIED_EMAIL("not.verified.email"),
    METHOD_ARGUMENT_NOT_VALID("method.argument.not.valid"),
    STUDIO("studio"),
    VERIFY_TIMEOUT("verify.timeout"),
    WRONG_PASSWORD("wrong.password"),

    ;

    private String errorMessage;

    ErrorType(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
