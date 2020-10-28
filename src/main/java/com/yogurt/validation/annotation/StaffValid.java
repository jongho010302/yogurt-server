package com.yogurt.validation.annotation;

import com.yogurt.validation.StaffValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StaffValidator.class)
@Documented
public @interface StaffValid {
    String message() default "존재하지 않는 직원입니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
