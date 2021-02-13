package com.yogurt.domain.base.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yogurt.domain.base.model.exception.DummyException;
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
public class TelNo {

    public static final String PATTERN = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
    public static final String PATTERN_MESSAGE = "전화번호의 형식을 맞춰 주세요.";

    @JsonIgnore
    @Transient
    private final Pattern pattern = Pattern.compile(PATTERN);

    @Column(length = 14, nullable = false)
    private String telNo;

    public TelNo(String telNo) {
        validate(telNo);
        this.telNo = telNo;
    }

    private void validate(String telNo) {
        Matcher matcher = pattern.matcher(telNo);

        if (!matcher.matches()) {
            throw new DummyException(PATTERN_MESSAGE);
        }
    }

    public static TelNo of(String telNo) {
        return new TelNo(telNo);
    }
}