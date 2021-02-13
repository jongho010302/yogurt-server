package com.yogurt.validation.annotation;

import com.yogurt.domain.base.model.VerificationType;
import com.yogurt.validation.VerificationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VerificationValidator.class)
@Documented
public @interface VerificationValid {
    String message() default VerificationType.PATTERN_MESSAGE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
