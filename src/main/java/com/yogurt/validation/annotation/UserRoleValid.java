package com.yogurt.validation.annotation;

import com.yogurt.generic.user.domain.UserRole;
import com.yogurt.validation.UserRoleValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserRoleValidator.class)
@Documented
public @interface UserRoleValid {
    String message() default UserRole.PATTERN_MESSAGE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
