package com.yogurt.generic.lesson.domain;

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
public class ClassType {

    public static final String PATTERN = "(GROUP|PRIVATE)";
    public static final String PATTERN_MESSAGE = "수업타입의 형식을 맞춰 주세요.";

    @JsonIgnore
    @Transient
    private final Pattern pattern = Pattern.compile(PATTERN);

    @Column(length = 10, nullable = false)
    private String classType;

    public ClassType(String classType) {
        validate(classType);
        this.classType = classType;
    }

    private void validate(String classType) {
        Matcher matcher = pattern.matcher(classType);

        if (!matcher.matches()) {
            throw new YogurtInvalidFormatException(PATTERN_MESSAGE);
        }
    }

    public static ClassType of(String classType) {
        return new ClassType(classType);
    }
}
