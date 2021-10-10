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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    private static Text dominantAffairText;
    private static Text recessiveAffairText;
    private static Text representativeText;
    private static Text offendOrientationText;
    private static Text ghostsAndGodsText;
    private static Text assertText;
    private static TextField selectDate;
    private static Text deityDetail;
    private static Text jinguPresentation;
    private static Text jinguPresentationDetail;
    private static Text mainRealmsText;
    private static Text viceRealmsText;
    private static Text mainStarText;
    private static Text viceStarText;
    private static Text mainStarDetailText;
    private static Text viceStarDetailText;
    private static Text bodyFiveElementsText;
    private static Text useFiveElementsText;
    private static Text relationshipText;
    private static Text body_useDetailText;

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
            setImageComponentValue(slice, connect, lunar, date+" "+time.split(":")[0]+":00:00");
        }
    }

    /**
     * Set the value of component
     *
     * @param slice
     * @param connect
     */
    public static void setComponentValue(AbilitySlice slice, OrmContext connect, String dateString) {
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

        Lunar lunar = DateUtils.getLunar(dateString);
        String time = "";
        String lunarYear = lunar.getYearInChinese();
        String lunarMonth = lunar.getMonthInChinese() + "月";
        String lunarDay = lunar.getDayInChinese();
        String solarTerms = lunar.getJieQi();
        String ganzhiYear = lunar.getYearInGanZhiByLiChun() + "年";
        String ganzhiMonth = lunar.getMonthInGanZhiExact() + "月";
        String ganzhiDay = lunar.getDayInGanZhiExact() + "日";
        String ganzhiTime = lunar.getTimeInGanZhi() + "时";
        setTextValue(dateString, dateText);
        setTextValue(time, timeText);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = simpleDateFormat.parse(dateString);
            Calendar instance = Calendar.getInstance();
            instance.setTime(parse);
            String week = DateUtils.getCurrentDate(instance, "EEEE");
            setTextValue(week, weekText);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        setTextValue(lunarYear, lunarYearText);
        setTextValue(lunarMonth, lunarMonthText);
        setTextValue(lunarDay, lunarDayText);
        setTextValue(solarTerms, solarTermsText);
        setTextValue(ganzhiYear, ganzhiYearText);
        setTextValue(ganzhiMonth, ganzhiMonthText);
        setTextValue(ganzhiDay, ganzhiDayText);
        setTextValue(ganzhiTime, ganzhiTimeText);
        setImageComponentValue(slice, connect, lunar, dateString);
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
        String time_tiangan = lunar.getTimeGan();
        int lunarMonthNum = lunar.getMonth();
        int lunarDayNum = lunar.getDay();
        int lunarTimeNum = Objects.requireNonNull(DatabaseUtils.queryDizhiByName(lunar.getTimeZhi(), connect)).getDizhiNum();
        int monthStepNum = lunarMonthNum % 6;
        int dayStepNum = (lunarDayNum + monthStepNum - 1) % 6;
        int timeStepNum = (lunarTimeNum + dayStepNum - 1) % 6;
        int month_yinyang = Objects.requireNonNull(DatabaseUtils.queryTianganByName(lunar.getMonthGanExact(), connect)).getTianganYinyang();
        int day_yinyang = Objects.requireNonNull(DatabaseUtils.queryTianganByName(lunar.getDayGanExact(), connect)).getTianganYinyang();
        int time_yinyang = Objects.requireNonNull(DatabaseUtils.queryTianganByName(time_tiangan, connect)).getTianganYinyang();
        setTextValue(date, selectDate);
        ImageUtils.setImage(aidedImage, monthStepNum, slice, 1);
        ImageUtils.setImage(assistantImage, dayStepNum, slice, 1);
        ImageUtils.setImage(mainImage, timeStepNum, slice, 1);
        ImageUtils.setImage(aidedFlagImage, month_yinyang, slice, 2);
        ImageUtils.setImage(assistantFlagImage, day_yinyang, slice, 2);
        ImageUtils.setImage(mainFlagImage, time_yinyang, slice, 2);
        setDetailText(slice, timeStepNum, dayStepNum, time_yinyang, time_tiangan, connect);
    }

    private static void setDetailText(AbilitySlice slice, int timeNum, int dayNum, int time_yinyang, String time_tiangan, OrmContext connect) {
        Attribute attribute = DatabaseUtils.queryAttributeByNum(timeNum, connect);
        Jingu jingu = DatabaseUtils.queryJinguById(attribute.getJingu(), connect);
        Deity deity = DatabaseUtils.queryDeityById(attribute.getDeity(), connect);
        FiveElements fiveElements = DatabaseUtils.queryFiveElementsById(attribute.getFiveElements(), connect);
        Organ organ = DatabaseUtils.queryOrganById(attribute.getOrgan(), connect);
        Orientation magnateOrientation = DatabaseUtils.queryOrientationById(attribute.getMagnateOrientation(), connect);
        Orientation offendOrientation = DatabaseUtils.queryOrientationById(attribute.getOffendOrientation(), connect);
        Assert anAssert = DatabaseUtils.queryAssertByNums(dayNum, timeNum, connect);
        Affair dominantAffair = DatabaseUtils.queryAffairById(jingu.getDominantAffair(), connect);
        Affair recessiveAffair = DatabaseUtils.queryAffairById(jingu.getRecessiveAffair(), connect);
        Tiangan representative = DatabaseUtils.queryTianganById(jingu.getMainTiangan(), connect);
        Realms mainRealms = DatabaseUtils.queryRealmsById(jingu.getMainRealms(), connect);
        Realms viceRealms = DatabaseUtils.queryRealmsById(jingu.getViceRealms(), connect);
        Star mainStar = DatabaseUtils.queryStarById(jingu.getMainStar(), connect);
        Star viceStar = DatabaseUtils.queryStarById(jingu.getViceStar(), connect);
        String jinguTianganYinyang = oddOrEven(representative.getTianganYinyang());
        String timeTianganYinyang = oddOrEven(time_yinyang);
        Tiangan timeTiangan = DatabaseUtils.queryTianganByName(time_tiangan, connect);
        FiveElements time_fiveElements = DatabaseUtils.queryFiveElementsById(timeTiangan.getFiveElements(), connect);

        deityText = (Text) slice.findComponentById(ResourceTable.Id_deity);
        fiveElementsText = (Text) slice.findComponentById(ResourceTable.Id_fiveElements);
        organText = (Text) slice.findComponentById(ResourceTable.Id_organ);
        magnateOrientationText = (Text) slice.findComponentById(ResourceTable.Id_magnateOrientation);
        luckyNumText = (Text) slice.findComponentById(ResourceTable.Id_luckyNum);
        dominantAffairText = (Text) slice.findComponentById(ResourceTable.Id_dominantAffair);
        recessiveAffairText = (Text) slice.findComponentById(ResourceTable.Id_recessiveAffair);
        representativeText = (Text) slice.findComponentById(ResourceTable.Id_representative);
        offendOrientationText = (Text) slice.findComponentById(ResourceTable.Id_offendOrientation);
        ghostsAndGodsText = (Text) slice.findComponentById(ResourceTable.Id_ghostsAndGods);
        assertText = (Text) slice.findComponentById(ResourceTable.Id_assertText);
        deityDetail = (Text) slice.findComponentById(ResourceTable.Id_deityDetail);
        jinguPresentation = (Text) slice.findComponentById(ResourceTable.Id_jinguPresentation);
        jinguPresentationDetail = (Text) slice.findComponentById(ResourceTable.Id_jinguDetail);
        mainRealmsText = (Text) slice.findComponentById(ResourceTable.Id_mainRealms);
        viceRealmsText = (Text) slice.findComponentById(ResourceTable.Id_viceRealms);
        mainStarText = (Text) slice.findComponentById(ResourceTable.Id_mainStar);
        viceStarText = (Text) slice.findComponentById(ResourceTable.Id_viceStar);
        mainStarDetailText = (Text) slice.findComponentById(ResourceTable.Id_mainStarDetail);
        viceStarDetailText = (Text) slice.findComponentById(ResourceTable.Id_viceStarDetail);
        bodyFiveElementsText = (Text) slice.findComponentById(ResourceTable.Id_bodyFiveElements);
        useFiveElementsText = (Text) slice.findComponentById(ResourceTable.Id_useFiveElements);
        relationshipText = (Text) slice.findComponentById(ResourceTable.Id_relationship);
        body_useDetailText = (Text) slice.findComponentById(ResourceTable.Id_body_useDetail);


        setTextValue(deity.getDeityName(), deityText);
        setTextValue(fiveElements.getFiveElementsName(), fiveElementsText);
        setTextValue(organ.getOrganName(), organText);
        setTextValue(magnateOrientation.getOrientationName(), magnateOrientationText);
        setTextValue(attribute.getLuckyNum(), luckyNumText);
        setTextValue(offendOrientation.getOrientationName(), offendOrientationText);
        setTextValue(attribute.getGhostsAndGods(), ghostsAndGodsText);
        setTextValue(anAssert.getAssertText(), assertText);
        setTextValue(deity.getPresentation(), deityDetail);
        setTextValue(jingu.getPresentation(), jinguPresentation);
        setTextValue(jingu.getPresentationDetail(), jinguPresentationDetail);
        setTextValue(dominantAffair.getAffairName(), dominantAffairText);
        setTextValue(recessiveAffair.getAffairName(), recessiveAffairText);
        setTextValue(representative.getRepresentative(), representativeText);
        setTextValue(mainRealms.getRealmsName(), mainRealmsText);
        setTextValue(viceRealms.getRealmsName(), viceRealmsText);
        setTextValue(mainStar.getStarName(), mainStarText);
        setTextValue(viceStar.getStarName(), viceStarText);
        setTextValue(mainStar.getDetail(), mainStarDetailText);
        setTextValue(viceStar.getDetail(), viceStarDetailText);
        setTextValue(jinguTianganYinyang + fiveElements.getFiveElementsName(), bodyFiveElementsText);
        setTextValue(timeTianganYinyang + time_fiveElements.getFiveElementsName(), useFiveElementsText);
        FiveElements bodyfiveElements = DatabaseUtils.queryFiveElementsByName(fiveElements.getFiveElementsName(), connect);
        FiveElements usefiveElements = DatabaseUtils.queryFiveElementsByName(time_fiveElements.getFiveElementsName(), connect);
        String relationship = "";
        if (bodyfiveElements.getGrowFiveElement().equals(usefiveElements.getFiveElementsName())) {
            relationship = "体生用";
        } else if (bodyfiveElements.getRestrainFiveElement().equals(usefiveElements.getFiveElementsName())) {
            relationship = "体克用";
        } else if (usefiveElements.getGrowFiveElement().equals(bodyfiveElements.getFiveElementsName())) {
            relationship = "用生体";
        } else if (usefiveElements.getRestrainFiveElement().equals(bodyfiveElements.getFiveElementsName())) {
            relationship = "用克体";
        } else if (bodyfiveElements.getFiveElementsName().equals(usefiveElements.getFiveElementsName()) && jinguTianganYinyang.equals(timeTianganYinyang)) {
            relationship = "比劫";
        } else if (bodyfiveElements.getFiveElementsName().equals(usefiveElements.getFiveElementsName()) && !jinguTianganYinyang.equals(timeTianganYinyang)) {
            relationship = "比肩";
        } else {
            relationship = "未知";
        }
        setTextValue(relationship, relationshipText);
        BodyUse bodyUse = DatabaseUtils.queryBodyUseByText(relationship, connect);
        setTextValue(Objects.requireNonNull(bodyUse).getBody_useDetail(), body_useDetailText);
    }

    private static String oddOrEven(int num) {
        if ((num & 1) == 1) {
            return "阳";
        } else {
            return "阴";
        }
    }
}
