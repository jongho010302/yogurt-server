package com.yogurt.validation.annotation;

import com.yogurt.validation.StudioValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StudioValidator.class)
@Documented
public @interface StudioValid {
    String message() default "존재하지 않는 센터입니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
