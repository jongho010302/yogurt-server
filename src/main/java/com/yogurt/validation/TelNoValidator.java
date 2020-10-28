package com.yogurt.validation;

import com.yogurt.generic.user.domain.TelNo;
import com.yogurt.validation.annotation.TelNoValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class TelNoValidator implements ConstraintValidator<TelNoValid, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile(TelNo.PATTERN);
        return value == null || Validation.regexCheck(pattern, value);
    }
}
