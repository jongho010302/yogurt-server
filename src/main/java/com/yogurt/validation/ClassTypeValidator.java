package com.yogurt.validation;

import com.yogurt.generic.lesson.domain.ClassType;
import com.yogurt.validation.annotation.ClassTypeValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ClassTypeValidator implements ConstraintValidator<ClassTypeValid, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile(ClassType.PATTERN);
        return value == null || Validation.regexCheck(pattern, value);
    }
}
