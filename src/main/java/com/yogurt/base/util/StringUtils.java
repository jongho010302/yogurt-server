package com.yogurt.base.util;

import java.util.UUID;

public class StringUtils {
    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str));
    }

    public static String getUUID(int length) {
        return UUID.randomUUID().toString().substring(0, length);
    }
}
