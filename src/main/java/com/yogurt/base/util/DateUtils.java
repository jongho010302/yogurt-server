package com.yogurt.base.util;

import com.yogurt.base.exception.YogurtDateParsingException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    static final String DATE_PATTERN_DEFAULT = "yyyy-MM-dd";

    public static Date parse(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN_DEFAULT);
            return sdf.parse(date);
        } catch (Exception e) {
            throw new YogurtDateParsingException("데이터 변환 중 에러가 발생했습니다.");
        }
    }

    public static Date parse(String date, String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(date);
        } catch (Exception e) {
            throw new YogurtDateParsingException("데이터 변환 중 에러가 발생했습니다.");
        }
    }

    public static String format(Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN_DEFAULT);
            return sdf.format(date);
        } catch (Exception e) {
            throw new YogurtDateParsingException("데이터 변환 중 에러가 발생했습니다.");
        }
    }

    public static String format(Date date, String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(date);
        } catch (Exception e) {
            throw new YogurtDateParsingException("데이터 변환 중 에러가 발생했습니다.");
        }
    }

    public static Calendar getCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.getCurrentDate());
        return calendar;
    }

    public static Calendar getCalendar(String dateStr) {
        Date date = DateUtils.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static Date getCurrentDate() {
        return new Date();
    }
}
