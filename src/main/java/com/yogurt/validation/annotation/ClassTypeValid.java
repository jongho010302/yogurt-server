package com.yogurt.validation.annotation;

import com.yogurt.validation.ClassTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ClassTypeValidator.class)
@Documented
public @interface ClassTypeValid {
    String message() default "수업 타입의 형식을 맞춰 주세요.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
