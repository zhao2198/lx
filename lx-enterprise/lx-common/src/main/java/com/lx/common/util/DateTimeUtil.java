package com.lx.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Create Date: 2018年5月2日 上午10:32:59
 *
 * @version: V3.0.1
 * @author: feng yi
 */

public class DateTimeUtil {

    public static String DEFAULTFORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String getCurDateTime() {
        return getCurDateTime(DEFAULTFORMAT);
    }

    /**
     * 当前时间
     *
     * @param pattern
     * @return
     */
    public static String getCurDateTime(String pattern) {
        return formatCalendar(Calendar.getInstance(), pattern);
    }

    public static String formatCalendar(Calendar calendar) {
        return formatCalendar(calendar, DEFAULTFORMAT);
    }

    public static String formatCalendar(Calendar calendar, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        // sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
        return sdf.format(calendar.getTime());
    }

    public static Date parseDate(String date) throws ParseException {
        if (date == null)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(date);
    }

    public static Date parseDate(String date, String pattern) throws ParseException {
        if (date == null)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(date);
    }

    public static String formatDate(Date date, String pattern) {
        if (date == null)
            return "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return formatCalendar(calendar, pattern);
    }

    public static String formatDate(Date date) {
        return formatDate(date, DEFAULTFORMAT);
    }

    public static Calendar parseString(String dateStr, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = sdf.parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static String convertDateTimeStrFormat(String dateStr, String pattern, String newPattern) throws ParseException {
        return DateTimeUtil.formatCalendar(DateTimeUtil.parseString(dateStr, pattern), newPattern);
    }

    public static Calendar parseString(String dateStr) throws ParseException {
        return parseString(dateStr, DEFAULTFORMAT);
    }

    public static String formatDateByUdp(Date date) {
        return formatDate(date, "yyMMddHHmmss");
    }

    /**
     * 将uinix字符串转换成时间字符串
     *
     * @param langdate
     * @return
     * @Description: TODO
     * @author fy
     * @date 2018-4-12
     * @version V1.0
     */

    public static String UinxToDate(int langdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (langdate < 10000) {
            return sdf.format(new Date());
        }
        String dd = sdf.format(new Date(langdate * 1000L));
        return dd;
    }

}
