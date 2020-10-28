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
public class Gender {

    public static final String PATTERN = "(M|F)";
    public static final String PATTERN_MESSAGE = "성별의 형식을 맞춰 주세요.";

    @JsonIgnore
    @Transient
    private final Pattern pattern = Pattern.compile(PATTERN);

    @Column(length = 1, nullable = false)
    private String gender;

    public Gender(String gender) {
        validate(gender);
        this.gender = gender;
    }

    private void validate(String gender) {
        Matcher matcher = pattern.matcher(gender);

        if (!matcher.matches()) {
            throw new YogurtInvalidFormatException(PATTERN_MESSAGE);
        }
    }

    public static Gender of(String gender) {
        return new Gender(gender);
    }
}
