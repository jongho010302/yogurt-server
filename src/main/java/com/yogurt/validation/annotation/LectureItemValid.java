package com.yogurt.validation.annotation;

import com.yogurt.validation.LectureItemValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LectureItemValidator.class)
@Documented
public @interface LectureItemValid {
    String message() default "존재하지 않는 수업입니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
