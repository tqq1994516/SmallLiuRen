/*
 * Copyright (c) 2021 Huawei Device Co., Ltd.
 * Licensed under the Apache License,Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tianchenxu.smallliuren.utils;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import com.tianchenxu.smallliuren.database.OldLunarHour;
import ohos.data.orm.OrmContext;
import ohos.utils.system.SystemCapability;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    public static String getCurrentDate(Calendar calendar, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(calendar.getTime());
    }

    public static Lunar getLunar(Calendar calendar) {
        Solar solar = Solar.fromDate(calendar.getTime());
        return solar.getLunar();
    }


    public static Lunar getLunar(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setLenient(false);
        Date parse=null;
        try {
            parse = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Solar solar = Solar.fromDate(parse);
        return solar.getLunar();
    }

    public static Solar getSolar(Calendar calendar) {
        Lunar lunar = Lunar.fromDate(calendar.getTime());
        return lunar.getSolar();
    }


    public static Solar getSolar(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setLenient(false);
        Date parse=null;
        try {
            parse = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Lunar lunar = Lunar.fromDate(parse);
        return lunar.getSolar();
    }

    public static int getFlag(OrmContext connect) {
        Lunar nowLunar = new Lunar();
        Calendar instance = Calendar.getInstance();
        int flag = 0;
        OldLunarHour oldLunarHour = DatabaseUtils.queryOldLunarHour(connect).get(0);
        String nowLunarHour = nowLunar.getTimeInGanZhi();
        if (!nowLunarHour.equals(oldLunarHour.getOldLunarHourText())) {
            flag = 1;
            oldLunarHour.setOldLunarHourText(nowLunarHour);
            oldLunarHour.setLastUpdateTime(instance);
            DatabaseUtils.updateOldLunarHour(oldLunarHour.getOldLunarHourId(), oldLunarHour, connect);
        }
        return flag;
    }

}
