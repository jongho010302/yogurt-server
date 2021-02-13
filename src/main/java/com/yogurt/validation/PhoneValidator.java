package com.yogurt.validation;

import com.yogurt.domain.base.model.Phone;
import com.yogurt.validation.annotation.PhoneValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<PhoneValid, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile(Phone.PATTERN);
        return value == null || Validation.regexCheck(pattern, value);
    }
}
