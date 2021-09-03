package com.tianchenxu.smallliuren.utils;

import com.nlf.calendar.Lunar;
import com.tianchenxu.smallliuren.ResourceTable;
import com.tianchenxu.smallliuren.database.*;
import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.Component;
import ohos.agp.components.Image;
import ohos.agp.components.Text;
import ohos.agp.components.TextField;
import ohos.app.Context;
import ohos.data.orm.OrmContext;

import java.util.Calendar;
import java.util.Objects;

public class ComponentUtils {
    private static Text dateText;
    private static Text timeText;
    private static Text weekText;
    private static Text lunarYearText;
    private static Text lunarMonthText;
    private static Text lunarDayText;
    private static Text solarTermsText;
    private static Text ganzhiYearText;
    private static Text ganzhiMonthText;
    private static Text ganzhiDayText;
    private static Text ganzhiTimeText;
    private static Image aidedImage;
    private static Image assistantImage;
    private static Image mainImage;
    private static Image aidedFlagImage;
    private static Image assistantFlagImage;
    private static Image mainFlagImage;
    private static Text deityText;
    private static Text fiveElementsText;
    private static Text organText;
    private static Text magnateOrientationText;
    private static Text luckyNumText;
    private static Text successNumText;
    private static Text ominousNumText;
    private static Text offendOrientationText;
    private static Text ghostsAndGodsText;
    private static Text assertText;
    private static TextField selectDate;

    /**
     * Set the value of component
     *
     * @param flag
     * @param connect
     */
    public static void setComponentValue(AbilitySlice slice, int flag, OrmContext connect) {
        dateText = (Text) slice.findComponentById(ResourceTable.Id_date);
        timeText = (Text) slice.findComponentById(ResourceTable.Id_time);
        weekText = (Text) slice.findComponentById(ResourceTable.Id_week);
        lunarYearText = (Text) slice.findComponentById(ResourceTable.Id_lunar_year);
        lunarMonthText = (Text) slice.findComponentById(ResourceTable.Id_lunar_month);
        lunarDayText = (Text) slice.findComponentById(ResourceTable.Id_lunar_day);
        solarTermsText = (Text) slice.findComponentById(ResourceTable.Id_solarTerms);
        ganzhiYearText = (Text) slice.findComponentById(ResourceTable.Id_ganzhi_year);
        ganzhiMonthText = (Text) slice.findComponentById(ResourceTable.Id_ganzhi_month);
        ganzhiDayText = (Text) slice.findComponentById(ResourceTable.Id_ganzhi_day);
        ganzhiTimeText = (Text) slice.findComponentById(ResourceTable.Id_ganzhi_time);
        Calendar now = Calendar.getInstance();
        Lunar lunar = DateUtils.getLunar(now);
        String date = DateUtils.getCurrentDate(now, "yyyy-MM-dd");
        String time = DateUtils.getCurrentDate(now, "HH:mm:ss");
        String week = DateUtils.getCurrentDate(now, "EEEE");
        String lunarYear = lunar.getYearInChinese();
        String lunarMonth = lunar.getMonthInChinese() + "月";
        String lunarDay = lunar.getDayInChinese();
        String solarTerms = lunar.getJieQi();
        String ganzhiYear = lunar.getYearInGanZhiByLiChun() + "年";
        String ganzhiMonth = lunar.getMonthInGanZhiExact() + "月";
        String ganzhiDay = lunar.getDayInGanZhiExact() + "日";
        String ganzhiTime = lunar.getTimeInGanZhi() + "时";
        setTextValue(date, dateText);
        setTextValue(time, timeText);
        setTextValue(week, weekText);
        setTextValue(lunarYear, lunarYearText);
        setTextValue(lunarMonth, lunarMonthText);
        setTextValue(lunarDay, lunarDayText);
        setTextValue(solarTerms, solarTermsText);
        setTextValue(ganzhiYear, ganzhiYearText);
        setTextValue(ganzhiMonth, ganzhiMonthText);
        setTextValue(ganzhiDay, ganzhiDayText);
        setTextValue(ganzhiTime, ganzhiTimeText);
        if (flag == 1) {
            setImageComponentValue(slice, connect, lunar, date);
        }
    }

    /**
     * Set the value of component
     *
     * @param flag
     * @param connect
     */
    public static void setComponentValue(AbilitySlice slice, int flag, OrmContext connect, String dateString) {
        dateText = (Text) slice.findComponentById(ResourceTable.Id_date);
        timeText = (Text) slice.findComponentById(ResourceTable.Id_time);
        weekText = (Text) slice.findComponentById(ResourceTable.Id_week);
        lunarYearText = (Text) slice.findComponentById(ResourceTable.Id_lunar_year);
        lunarMonthText = (Text) slice.findComponentById(ResourceTable.Id_lunar_month);
        lunarDayText = (Text) slice.findComponentById(ResourceTable.Id_lunar_day);
        solarTermsText = (Text) slice.findComponentById(ResourceTable.Id_solarTerms);
        ganzhiYearText = (Text) slice.findComponentById(ResourceTable.Id_ganzhi_year);
        ganzhiMonthText = (Text) slice.findComponentById(ResourceTable.Id_ganzhi_month);
        ganzhiDayText = (Text) slice.findComponentById(ResourceTable.Id_ganzhi_day);
        ganzhiTimeText = (Text) slice.findComponentById(ResourceTable.Id_ganzhi_time);
        Calendar now = Calendar.getInstance();
        Lunar lunar = DateUtils.getLunar(now);
        String date = DateUtils.getCurrentDate(now, "yyyy-MM-dd");
        String time = DateUtils.getCurrentDate(now, "HH:mm:ss");
        String week = DateUtils.getCurrentDate(now, "EEEE");
        String lunarYear = lunar.getYearInChinese();
        String lunarMonth = lunar.getMonthInChinese() + "月";
        String lunarDay = lunar.getDayInChinese();
        String solarTerms = lunar.getJieQi();
        String ganzhiYear = lunar.getYearInGanZhiByLiChun() + "年";
        String ganzhiMonth = lunar.getMonthInGanZhiExact() + "月";
        String ganzhiDay = lunar.getDayInGanZhiExact() + "日";
        String ganzhiTime = lunar.getTimeInGanZhi() + "时";
        setTextValue(date, dateText);
        setTextValue(time, timeText);
        setTextValue(week, weekText);
        setTextValue(lunarYear, lunarYearText);
        setTextValue(lunarMonth, lunarMonthText);
        setTextValue(lunarDay, lunarDayText);
        setTextValue(solarTerms, solarTermsText);
        setTextValue(ganzhiYear, ganzhiYearText);
        setTextValue(ganzhiMonth, ganzhiMonthText);
        setTextValue(ganzhiDay, ganzhiDayText);
        setTextValue(ganzhiTime, ganzhiTimeText);
        if (flag == 1) {
            setImageComponentValue(slice, connect, lunar, date);
        }
    }

    private static void setTextValue(String str, Text text) {
        text.setText(str);
    }

    /**
     * Set the value of component
     *
     * @param slice
     * @param connect
     * @param lunar
     */
    private static void setImageComponentValue(AbilitySlice slice, OrmContext connect, Lunar lunar, String date) {
        selectDate = (TextField) slice.findComponentById(ResourceTable.Id_selectDate);
        aidedImage = (Image) slice.findComponentById(ResourceTable.Id_aided);
        assistantImage = (Image) slice.findComponentById(ResourceTable.Id_assistant);
        mainImage = (Image) slice.findComponentById(ResourceTable.Id_main);
        aidedFlagImage = (Image) slice.findComponentById(ResourceTable.Id_aided_flag);
        assistantFlagImage = (Image) slice.findComponentById(ResourceTable.Id_assistant_flag);
        mainFlagImage = (Image) slice.findComponentById(ResourceTable.Id_main_flag);
        int lunarMonthNum = lunar.getMonth();
        int lunarDayNum = lunar.getDay();
        int lunarTimeNum = Objects.requireNonNull(DatabaseUtils.queryDizhiByName(lunar.getTimeZhi(), connect)).getDizhiNum();
        int monthStepNum = lunarMonthNum % 6;
        int dayStepNum = (lunarDayNum + monthStepNum - 1) % 6;
        int timeStepNum = (lunarTimeNum + dayStepNum - 1) % 6;
        int month_yinyang = Objects.requireNonNull(DatabaseUtils.queryTianganByName(lunar.getMonthGanExact(), connect)).getTianganYinyang();
        int day_yinyang = Objects.requireNonNull(DatabaseUtils.queryTianganByName(lunar.getDayGanExact(), connect)).getTianganYinyang();
        int time_yinyang = Objects.requireNonNull(DatabaseUtils.queryTianganByName(lunar.getTimeGan(), connect)).getTianganYinyang();
        setTextValue(date, selectDate);
        ImageUtils.setImage(aidedImage, monthStepNum, slice, 1);
        ImageUtils.setImage(assistantImage, dayStepNum, slice, 1);
        ImageUtils.setImage(mainImage, timeStepNum, slice, 1);
        ImageUtils.setImage(aidedFlagImage, month_yinyang, slice, 2);
        ImageUtils.setImage(assistantFlagImage, day_yinyang, slice, 2);
        ImageUtils.setImage(mainFlagImage, time_yinyang, slice, 2);
        setDetailText(slice, timeStepNum, dayStepNum, connect);
    }

    private static void setDetailText(AbilitySlice slice, int timeNum, int dayNum, OrmContext connect) {
        Attribute attribute = DatabaseUtils.queryAttributeByNum(timeNum, connect);
        Deity deity = DatabaseUtils.queryDeityById(attribute.getDeity(), connect);
        FiveElements fiveElements = DatabaseUtils.queryFiveElementsById(attribute.getFiveElements(), connect);
        Organ organ = DatabaseUtils.queryOrganById(attribute.getOrgan(), connect);
        Orientation magnateOrientation = DatabaseUtils.queryOrientationById(attribute.getMagnateOrientation(), connect);
        Orientation offendOrientation = DatabaseUtils.queryOrientationById(attribute.getOffendOrientation(), connect);
        Assert anAssert = DatabaseUtils.queryAssertByNums(dayNum, timeNum, connect);
        deityText = (Text) slice.findComponentById(ResourceTable.Id_deity);
        fiveElementsText = (Text) slice.findComponentById(ResourceTable.Id_fiveElements);
        organText = (Text) slice.findComponentById(ResourceTable.Id_organ);
        magnateOrientationText = (Text) slice.findComponentById(ResourceTable.Id_magnateOrientation);
        luckyNumText = (Text) slice.findComponentById(ResourceTable.Id_luckyNum);
        successNumText = (Text) slice.findComponentById(ResourceTable.Id_successNum);
        ominousNumText = (Text) slice.findComponentById(ResourceTable.Id_ominousNum);
        offendOrientationText = (Text) slice.findComponentById(ResourceTable.Id_offendOrientation);
        ghostsAndGodsText = (Text) slice.findComponentById(ResourceTable.Id_ghostsAndGods);
        assertText = (Text) slice.findComponentById(ResourceTable.Id_assertText);
        setTextValue(deity.getDeityName(), deityText);
        setTextValue(fiveElements.getFiveElementsName(), fiveElementsText);
        setTextValue(organ.getOrganName(), organText);
        setTextValue(magnateOrientation.getOrientationName(), magnateOrientationText);
        setTextValue(attribute.getLuckyNum(), luckyNumText);
        setTextValue(attribute.getSuccessNum(), successNumText);
        setTextValue(attribute.getOminousNum(), ominousNumText);
        setTextValue(offendOrientation.getOrientationName(), offendOrientationText);
        setTextValue(attribute.getGhostsAndGods(), ghostsAndGodsText);
        setTextValue(anAssert.getAssertText(), assertText);
    }
}
