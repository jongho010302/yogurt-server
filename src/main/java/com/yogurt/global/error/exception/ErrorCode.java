package com.yogurt.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C001", " Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", " Invalid Input Value"),
    ENTITY_NOT_FOUND(400, "C003", " Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "C004", "Server Error"),
    INVALID_TYPE_VALUE(400, "C005", " Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "C006", "Access is Denied"),

    // Auth
    EMAIL_ALREADY_REGISTERED(400, "A001", "Email is already registered"),
    CODE_NOT_SENT(400, "A002", "Verification code isn't sent"),
    CODE_TIMEOUT(400, "A003", "Verification exceed timeout"),
    CODE_DIFFERENT(400, "A004", "Verification code different"),
    WRONG_PASSWORD(400, "A005", "Verification code different"),
    OAUTH(400, "A006", "OAuth error occurred"),

    // Ticket
    TICKET_EXPIRE(400, "TO01", "Ticket was expired"),
    BOOKING_COUPON_LACK(400, "TO002", "There are no booking coupons available"),
    BOOKING_CANCEL_COUPON_LACK(400, "TO003", "There are no booking cancel coupons available"),

    // Lecture
    BOOKING_TIME_EXCEED(400, "LO001", "The booking time has been exceeded"),
    BOOKING_CANCEL_TIME_EXCEED(400, "LO002", "The booking cancel time has been exceeded"),
    BOOKING_ENTRY(400, "LO003", "The booking entry is exceeded"),
    ALREADY_BOOK(400, "LO004", "You already booked this lecture"),

    // User
    SAME_PASSWORD(400, "U001", "It's the same password before"),

    // File
    FILE_SIZE(400, "F001", "File size must be below 5MB"),
    FILE_UPLOAD(400, "F002", "File upload error occurred."),

    // Util
    DATE_PARSE(400, "U001", "Date parsing error occurred.")

    ;
    private final String code;
    private final String message;
    private int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }


}
