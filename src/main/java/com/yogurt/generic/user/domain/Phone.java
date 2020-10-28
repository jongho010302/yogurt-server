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

@NoArgsConstructor
@Getter
@Embeddable
public class Phone {

    public static final String PATTERN = "^\\d{3}-\\d{3,4}-\\d{4}$";
    public static final String PATTERN_MESSAGE = "핸드폰 번호의 형식을 맞춰 주세요.";

    @JsonIgnore
    @Transient
    private final Pattern pattern = Pattern.compile(PATTERN);

    @Column(length = 13, nullable = false)
    private String phone;

    public Phone(String phone) {
        validate(phone);
        this.phone = phone;
    }

    private void validate(String phone) {
        Matcher matcher = pattern.matcher(phone);

        if (!matcher.matches()) {
            throw new YogurtInvalidFormatException(PATTERN_MESSAGE);
        }
    }

    public static Phone of(String phone) {
        return new Phone(phone);
    }
}