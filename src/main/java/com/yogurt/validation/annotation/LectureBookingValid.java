package com.yogurt.validation.annotation;

import com.yogurt.validation.LectureBookingValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LectureBookingValidator.class)
@Documented
public @interface LectureBookingValid {
    String message() default "존재하지 않는 수업 예약 데이터 입니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
