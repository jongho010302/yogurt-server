package com.yogurt.validation;

import com.yogurt.domain.base.model.Email;
import com.yogurt.validation.annotation.EmailValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<EmailValid, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile(Email.PATTERN);
        return value == null || Validation.regexCheck(pattern, value);
    }
}
