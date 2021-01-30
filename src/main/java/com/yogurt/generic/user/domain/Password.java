package com.yogurt.generic.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
@Getter
@Embeddable
public class Password {

    public static final String PATTERN = "^(?=.*[a-zA-Z])((?=.*\\d)|(?=.*\\W)).{6,20}$";
    public static final String PATTERN_MESSAGE = "비밀번호의 형식을 맞춰 주세요.";
}