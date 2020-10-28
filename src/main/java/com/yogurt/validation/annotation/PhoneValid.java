package com.yogurt.validation.annotation;

import com.yogurt.validation.PhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneValidator.class)
@Documented
public @interface PhoneValid {
    String message() default "핸드폰 번호의 형식을 맞춰 주세요.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
