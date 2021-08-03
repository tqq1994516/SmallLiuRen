package com.tianchenxu.smallliuren.utils;

import com.nlf.calendar.Lunar;
import com.tianchenxu.smallliuren.ResourceTable;
import com.tianchenxu.smallliuren.widget.controller.FormController;
import ohos.agp.components.ComponentProvider;
import ohos.agp.utils.Color;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.media.image.PixelMap;

import java.util.Calendar;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Component ProviderUtils
 */
public class ComponentProviderUtils {
    private static final int DIM_VERSION = 2;

    /**
     * Obtains the ComponentProvider object
     *
     * @param controller form info
     * @param context    context
     * @return component provider
     */
    public static ComponentProvider getComponentProvider(FormController controller, Context context) {
        int layoutId = ResourceTable.Layout_form_grid_pattern_widget_4_4;
        if (controller.getDimension() == DIM_VERSION) {
            layoutId = ResourceTable.Layout_form_grid_pattern_widget_2_2;
        }
        ComponentProvider componentProvider = new ComponentProvider(layoutId, context);
        setComponentProviderValue(componentProvider);
        return componentProvider;
    }


    /**
     * Set the value of componentProvider
     *
     * @param componentProvider component provider
     */
    private static void setComponentProviderValue(ComponentProvider componentProvider) {
        componentProvider.setText(ResourceTable.Id_date, DateUtils.getCurrentSolar().toYmd());
        componentProvider.setText(ResourceTable.Id_time, DateUtils.getCurrentSolar().getHour() + ":" + DateUtils.getCurrentSolar().getMinute() + ":" + DateUtils.getCurrentSolar().getSecond());
        componentProvider.setText(ResourceTable.Id_week, "星期" + DateUtils.getCurrentSolar().getWeekInChinese());
        componentProvider.setText(ResourceTable.Id_lunar_year, DateUtils.getCurrentLunar().getYearInChinese() + "年");
        componentProvider.setText(ResourceTable.Id_lunar_mouth, DateUtils.getCurrentLunar().getMonthInChinese() + "月");
        componentProvider.setText(ResourceTable.Id_lunar_day, DateUtils.getCurrentLunar().getDayInChinese() + "日");
        componentProvider.setText(ResourceTable.Id_solarTerms, DateUtils.getCurrentLunar().getJieQi());
        componentProvider.setText(ResourceTable.Id_ganzhi_year, DateUtils.getCurrentLunar().getYearInGanZhiByLiChun() + "年");
        componentProvider.setText(ResourceTable.Id_ganzhi_mouth, DateUtils.getCurrentLunar().getMonthInGanZhiExact() + "月");
        componentProvider.setText(ResourceTable.Id_ganzhi_day, DateUtils.getCurrentLunar().getDayInGanZhiExact() + "日");
        componentProvider.setText(ResourceTable.Id_ganzhi_time, DateUtils.getCurrentLunar().getTimeInGanZhi() + "时");
        getSmallLiuRen(DateUtils.getCurrentLunar(), componentProvider);
    }

    private static final Dictionary<String, Integer> lunarTimeNums() {
        Dictionary<String, Integer> lunarTime = new Hashtable<>();
        lunarTime.put("子", 1);
        lunarTime.put("丑", 2);
        lunarTime.put("寅", 3);
        lunarTime.put("卯", 4);
        lunarTime.put("辰", 5);
        lunarTime.put("巳", 6);
        lunarTime.put("午", 7);
        lunarTime.put("未", 8);
        lunarTime.put("申", 9);
        lunarTime.put("酉", 10);
        lunarTime.put("戌", 11);
        lunarTime.put("亥", 12);
        return lunarTime;
    }

    private static void getSmallLiuRen(Lunar lunar, ComponentProvider componentProvider) {
        Dictionary<String, Integer> lunTimeNums = lunarTimeNums();
        int lunarMonthNum = (int) lunar.getMonth();
        int lunarDayNum = (int) lunar.getDay();
        int lunarTimeNum = lunTimeNums.get(lunar.getTimeZhi());
        int monthStepNum = lunarMonthNum % 6;
        switch (monthStepNum) {
            case 0:
                componentProvider.setImageContent(ResourceTable.Id_aided, ResourceTable.Media_kongwang);
                break;
            case 1:
                componentProvider.setImageContent(ResourceTable.Id_aided, ResourceTable.Media_daan);
                break;
            case 2:
                componentProvider.setImageContent(ResourceTable.Id_aided, ResourceTable.Media_liulian);
                break;
            case 3:
                componentProvider.setImageContent(ResourceTable.Id_aided, ResourceTable.Media_suxi);
                break;
            case 4:
                componentProvider.setImageContent(ResourceTable.Id_aided, ResourceTable.Media_chikou);
                break;
            case 5:
                componentProvider.setImageContent(ResourceTable.Id_aided, ResourceTable.Media_xiaoji);
                break;
        }

        int dayStepNum = (lunarDayNum + monthStepNum) % 6;
        switch (dayStepNum) {
            case 0:
                componentProvider.setImageContent(ResourceTable.Id_assistant, ResourceTable.Media_kongwang);
                break;
            case 1:
                componentProvider.setImageContent(ResourceTable.Id_assistant, ResourceTable.Media_daan);
                break;
            case 2:
                componentProvider.setImageContent(ResourceTable.Id_assistant, ResourceTable.Media_liulian);
                break;
            case 3:
                componentProvider.setImageContent(ResourceTable.Id_assistant, ResourceTable.Media_suxi);
                break;
            case 4:
                componentProvider.setImageContent(ResourceTable.Id_assistant, ResourceTable.Media_chikou);
                break;
            case 5:
                componentProvider.setImageContent(ResourceTable.Id_assistant, ResourceTable.Media_xiaoji);
                break;
        }

        int timeStepNum = (lunarTimeNum + dayStepNum) % 6;
        switch (dayStepNum) {
            case 0:
                componentProvider.setImageContent(ResourceTable.Id_main, ResourceTable.Media_kongwang);
                break;
            case 1:
                componentProvider.setImageContent(ResourceTable.Id_main, ResourceTable.Media_daan);
                break;
            case 2:
                componentProvider.setImageContent(ResourceTable.Id_main, ResourceTable.Media_liulian);
                break;
            case 3:
                componentProvider.setImageContent(ResourceTable.Id_main, ResourceTable.Media_suxi);
                break;
            case 4:
                componentProvider.setImageContent(ResourceTable.Id_main, ResourceTable.Media_chikou);
                break;
            case 5:
                componentProvider.setImageContent(ResourceTable.Id_main, ResourceTable.Media_xiaoji);
                break;
        }
    }
}