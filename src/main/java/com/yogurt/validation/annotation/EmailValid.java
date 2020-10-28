package com.yogurt.validation.annotation;

import com.yogurt.validation.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface EmailValid {
    String message() default "이메일의 형식을 맞춰 주세요.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
