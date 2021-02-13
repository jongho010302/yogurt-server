package com.yogurt.validation;

import com.yogurt.domain.base.model.Date;
import com.yogurt.validation.annotation.DateValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class DateValidator implements ConstraintValidator<DateValid, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile(Date.PATTERN);
        return value == null || Validation.regexCheck(pattern, value);
    }
}
