package com.tianchenxu.smallliuren.utils;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;


/**
 * 使用lunar库获取当前时间所有信息
 * Date time util
 */
public class DateUtils {
    /**
     * current time
     * @return corresponding format string
     */
    public static Solar getCurrentSolar() {
        return new Solar();
    }

    public static Lunar getCurrentLunar() {
        return new Lunar();
    }
}
