package com.yogurt.generic.user.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
@Getter
@Embeddable
public class Password {

    public static final String PATTERN = "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
    public static final String PATTERN_MESSAGE = "비밀번호의 형식을 맞춰 주세요.";
}