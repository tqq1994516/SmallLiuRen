package com.tianchenxu.smallliuren.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date time util
 */
public class DateUtils {
    /**
     * current time
     *
     * @param format format
     * @return corresponding format string
     */
    public static String getCurrentDate(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date);
    }
}