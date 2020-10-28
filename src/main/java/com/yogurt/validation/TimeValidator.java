package com.yogurt.validation;

import com.yogurt.validation.annotation.TimeValid;
import com.yogurt.validation.enums.TimeEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class TimeValidator implements ConstraintValidator<TimeValid, String> {

    TimeEnum timeEnum;

    public void initialize(final TimeValid timeValid) {
        timeEnum = timeValid.time();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (timeEnum == TimeEnum.TIME_24) {
            Pattern pattern = Pattern.compile("^(?:([01]?\\d|2[0-3]):)?([0-5]?\\d)");
            return value == null || Validation.regexCheck(pattern, value);
        } else if (timeEnum == TimeEnum.TIME_48) {
            Pattern pattern = Pattern.compile("^(?:([0123]?\\d|4[0-7]):)?([0-5]?\\d)");
            return value == null || Validation.regexCheck(pattern, value);
        }
        return true;
    }
}
