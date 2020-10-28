package com.yogurt.generic.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yogurt.base.exception.YogurtInvalidFormatException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Embeddable
@NoArgsConstructor
public class Date {
    public static final String PATTERN = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";
    public static final String PATTERN_MESSAGE = "날짜의 형식을 맞춰 주세요.";

    @JsonIgnore
    @Transient
    private final Pattern pattern = Pattern.compile(PATTERN);

    public Date(String date) {
        validate(date);
        this.date = date;
    }

    @Column(nullable = false, length = 10)
    private String date;

    private void validate(String date) {
        Matcher matcher = pattern.matcher(date);

        if (!matcher.matches()) {
            throw new YogurtInvalidFormatException(PATTERN_MESSAGE);
        }
    }

    public static Date of(String date) {
        return new Date(date);
    }
}