package com.yogurt.util;

import com.yogurt.util.exceptions.DateParseException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    static final String DATE_PATTERN_DEFAULT = "yyyy-MM-dd";

    static final String[] WEEK = {"일", "월", "화", "수", "목", "금", "토"};

    public static Date parse(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN_DEFAULT);
            return sdf.parse(date);
        } catch (Exception e) {
            throw new DateParseException();
        }
    }

    public static Date parse(String date, String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(date);
        } catch (Exception e) {
            throw new DateParseException();
        }
    }

    public static String format(Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN_DEFAULT);
            return sdf.format(date);
        } catch (Exception e) {
            throw new DateParseException();
        }
    }

    public static String format(Date date, String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(date);
        } catch (Exception e) {
            throw new DateParseException();
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

    public static String getDayOfWeek(String strDate) {
        Date date = DateUtils.parse(strDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int dayOfWeekNum = calendar.get(Calendar.DAY_OF_WEEK);
        String dayOfWeek = WEEK[dayOfWeekNum];
        return dayOfWeek;
    }

    public static String getLectureAt(String startAt, String endAt) {
        String dayOfWeek = getDayOfWeek(startAt);
        String yyyymmmdd = startAt.split(" ")[0];
        String startTime = endAt.split(" ")[1];
        String endTime = endAt.split(" ")[1];

        String lectureAt = String.format("%s (%s) %s~%s", yyyymmmdd, dayOfWeek, startTime, endTime);
        return lectureAt;
    }
}
