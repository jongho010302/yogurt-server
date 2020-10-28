package com.yogurt.validation.annotation;

import com.yogurt.validation.TelNoValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TelNoValidator.class)
@Documented
public @interface TelNoValid {
    String message() default "전화번호의 형식을 맞춰 주세요.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
