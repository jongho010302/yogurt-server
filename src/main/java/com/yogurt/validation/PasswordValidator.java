package com.yogurt.validation;

import com.yogurt.generic.user.domain.Password;
import com.yogurt.validation.annotation.PasswordValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<PasswordValid, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile(Password.PATTERN);
        return value == null || Validation.regexCheck(pattern, value);
    }
}
