package org.bailiun.multipleversionscoexist.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


public class T {
    public static String NowDay() {
        LocalDate today = LocalDate.now();//获取当前日期
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return today.format(formatter);
    }

    public static String NowMonth() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        return today.format(formatter);
    }

    public static String NowTime(String format) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return now.format(formatter);
    }

    public static String AfterToday(Integer day) {
        Calendar calendar2 = Calendar.getInstance();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        calendar2.add(Calendar.DATE, day);
        return sdf2.format(calendar2.getTime());
    }

    public static String DayAfterToday(String nowTime, Integer day) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(nowTime, formatter);
        LocalDateTime newDateTime = dateTime.plusDays(day);
        return newDateTime.format(formatter);
    }


}
