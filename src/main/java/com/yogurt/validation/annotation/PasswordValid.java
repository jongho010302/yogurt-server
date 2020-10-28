package com.yogurt.validation.annotation;

import com.yogurt.validation.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface PasswordValid {
    String message() default "비밀번호의 형식을 맞춰 주세요.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
