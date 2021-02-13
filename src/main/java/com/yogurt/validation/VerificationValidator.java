package com.yogurt.validation;

import com.yogurt.domain.base.model.VerificationType;
import com.yogurt.validation.annotation.VerificationValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class VerificationValidator implements ConstraintValidator<VerificationValid, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile(VerificationType.PATTERN);
        return value == null || Validation.regexCheck(pattern, value);
    }
}
