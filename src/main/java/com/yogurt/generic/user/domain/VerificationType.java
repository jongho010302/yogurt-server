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
public class VerificationType {

    public enum VERIFICATION_TYPE {
        SIGNUP, FIND_PASSWORD, CHANGE_EMAIL
    }

    public static final String PATTERN = "(SIGNUP|FIND_PASSWORD|CHANGE_EMAIL)";
    public static final String PATTERN_MESSAGE = "인증번호 타입의 형식을 맞춰주세요.";

    @JsonIgnore
    @Transient
    private final Pattern pattern = Pattern.compile(PATTERN);

    @Column
    private String type;

    private VerificationType(String type) {
        validate(type);
        this.type = type;
    }

    private void validate(String type) {
        Matcher matcher = pattern.matcher(type);

        if (!matcher.matches()) {
            throw new YogurtInvalidFormatException(PATTERN_MESSAGE);
        }
    }

    public static VerificationType of(String type) {
        return new VerificationType(type);
    }
}
