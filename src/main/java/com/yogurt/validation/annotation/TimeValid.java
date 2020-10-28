package com.yogurt.validation.annotation;

import com.yogurt.validation.TimeValidator;
import com.yogurt.validation.enums.TimeEnum;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimeValidator.class)
@Documented
public @interface TimeValid {
    String message() default "시간의 형식을 맞춰 주세요.";

    TimeEnum time() default TimeEnum.TIME_24;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
