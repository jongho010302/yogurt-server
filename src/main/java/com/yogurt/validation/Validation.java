package com.yogurt.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean regexCheck(Pattern pattern, String value) {
        try {
            if (value == null) {
                return false;
            }

            Matcher matcher = pattern.matcher(value);
            if (!matcher.matches()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
