package com.yogurt.validation;

import com.yogurt.generic.user.domain.Gender;
import com.yogurt.validation.annotation.GenderValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class GenderValidator implements ConstraintValidator<GenderValid, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile(Gender.PATTERN);
        return value == null || Validation.regexCheck(pattern, value);
    }
}
