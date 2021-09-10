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
import com.tianchenxu.smallliuren.database.*;

import ohos.app.Context;
import ohos.data.orm.OrmContext;
import ohos.hiviewdfx.HiLogLabel;
import ohos.utils.zson.ZSONObject;

import java.util.Calendar;
import java.util.Objects;

/**
 * Component ZSONObjectUtils
 */
public class ZSONObjectUtils {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "Demo");

    public static ZSONObject getZSONObject(OrmContext connect, int flag) {
        ZSONObject zsonObject = new ZSONObject();
        setZSONObject(zsonObject, flag, connect);
        return zsonObject;
    }


    /**
     * Set the value of zsonObject
     *
     * @param zsonObject ZSONObject
     * @param flag       flag
     * @param connect    connect
     */
    public static void setZSONObject(ZSONObject zsonObject, int flag, OrmContext connect) {
        Calendar now = Calendar.getInstance();
        Lunar lunar = DateUtils.getLunar(now);
        zsonObject.put("date", DateUtils.getCurrentDate(now, "yyyy-MM-dd"));
        zsonObject.put("time", DateUtils.getCurrentDate(now, "HH:mm:ss"));
        zsonObject.put("week", DateUtils.getCurrentDate(now, "EEEE"));
        zsonObject.put("lunar_year", lunar.getYearInChinese());
        zsonObject.put("lunar_month", lunar.getMonthInChinese() + "月");
        zsonObject.put("lunar_day", lunar.getDayInChinese());
        zsonObject.put("solarTerms", lunar.getJieQi());
        zsonObject.put("ganzhi_year", lunar.getYearInGanZhiByLiChun() + "年");
        zsonObject.put("ganzhi_month", lunar.getMonthInGanZhiExact() + "月");
        zsonObject.put("ganzhi_day", lunar.getDayInGanZhiExact() + "日");
        zsonObject.put("ganzhi_time", lunar.getTimeInGanZhi() + "时");
        if (flag == 1) {
            setImageValue(zsonObject, connect, lunar);
        }
    }

    /**
     * Set the value of zsonObject
     *
     * @param zsonObject ZSONObject
     * @param connect    connect
     * @param lunar      lunar
     */
    private static void setImageValue(ZSONObject zsonObject, OrmContext connect, Lunar lunar) {
        int lunarMonthNum = lunar.getMonth();
        int lunarDayNum = lunar.getDay();
        int lunarTimeNum = Objects.requireNonNull(DatabaseUtils.queryDizhiByName(lunar.getTimeZhi(), connect)).getDizhiNum();
        int monthStepNum = lunarMonthNum % 6;
        int dayStepNum = (lunarDayNum + monthStepNum - 1) % 6;
        int timeStepNum = (lunarTimeNum + dayStepNum - 1) % 6;
        int month_yinyang = Objects.requireNonNull(DatabaseUtils.queryTianganByName(lunar.getMonthGanExact(), connect)).getTianganYinyang();
        int day_yinyang = Objects.requireNonNull(DatabaseUtils.queryTianganByName(lunar.getDayGanExact(), connect)).getTianganYinyang();
        int time_yinyang = Objects.requireNonNull(DatabaseUtils.queryTianganByName(lunar.getTimeGan(), connect)).getTianganYinyang();

        setDetailText(zsonObject, timeStepNum, dayStepNum, connect);
        ImageUtils.setImage(zsonObject, monthStepNum, "aided", 1);
        ImageUtils.setImage(zsonObject, dayStepNum, "assistant", 1);
        ImageUtils.setImage(zsonObject, timeStepNum, "main", 1);
        ImageUtils.setImage(zsonObject, month_yinyang, "aided_flag", 2);
        ImageUtils.setImage(zsonObject, day_yinyang, "assistant_flag", 2);
        ImageUtils.setImage(zsonObject, time_yinyang, "main_flag", 2);
    }

    /**
     * set detail text
     *
     * @param zsonObject ZSONObject
     * @param timeNum    time num
     * @param dayNum     day num
     * @param connect    connect
     */
    private static void setDetailText(ZSONObject zsonObject, int timeNum, int dayNum, OrmContext connect) {
        Attribute attribute = DatabaseUtils.queryAttributeByNum(timeNum, connect);
        Deity deity = DatabaseUtils.queryDeityById(Objects.requireNonNull(attribute).getDeity(), connect);
        FiveElements fiveElements = DatabaseUtils.queryFiveElementsById(attribute.getFiveElements(), connect);
        Organ organ = DatabaseUtils.queryOrganById(attribute.getOrgan(), connect);
        Orientation magnateOrientation = DatabaseUtils.queryOrientationById(attribute.getMagnateOrientation(), connect);
        Orientation offendOrientation = DatabaseUtils.queryOrientationById(attribute.getOffendOrientation(), connect);
        Assert anAssert = DatabaseUtils.queryAssertByNums(dayNum, timeNum, connect);
        zsonObject.put("deity", Objects.requireNonNull(deity).getDeityName());
        zsonObject.put("fiveElements", Objects.requireNonNull(fiveElements).getFiveElementsName());
        zsonObject.put("organ", Objects.requireNonNull(organ).getOrganName());
        zsonObject.put("magnateOrientation", Objects.requireNonNull(magnateOrientation).getOrientationName());
        zsonObject.put("luckyNum", attribute.getLuckyNum());
        zsonObject.put("dominantAffair", attribute.getLuckyNum());
        zsonObject.put("luckyNum", attribute.getLuckyNum());
        zsonObject.put("offendOrientation", Objects.requireNonNull(offendOrientation).getOrientationName());
        zsonObject.put("ghostsAndGods", attribute.getGhostsAndGods());
        zsonObject.put("assert_text", Objects.requireNonNull(anAssert).getAssertText());
    }
}
