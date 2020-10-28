package com.yogurt.validation;

import com.yogurt.generic.user.domain.UserRole;
import com.yogurt.validation.annotation.UserRoleValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class UserRoleValidator implements ConstraintValidator<UserRoleValid, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile(UserRole.PATTERN);
        return value == null || Validation.regexCheck(pattern, value);
    }
}
