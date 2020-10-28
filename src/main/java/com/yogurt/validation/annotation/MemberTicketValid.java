package com.yogurt.validation.annotation;

import com.yogurt.validation.MemberTicketValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MemberTicketValidator.class)
@Documented
public @interface MemberTicketValid {
    String message() default "존재하지 않는 회원 수강권입니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
