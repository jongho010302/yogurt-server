package com.yogurt.validation.annotation;

import com.yogurt.validation.UserTicketValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserTicketValidator.class)
@Documented
public @interface UserTicketValid {
    String message() default "존재하지 않는 회원 수강권입니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
