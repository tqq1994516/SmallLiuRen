package com.tianchenxu.smallliuren.utils;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import com.tianchenxu.smallliuren.ResourceTable;
import com.tianchenxu.smallliuren.widget.controller.FormController;
import ohos.agp.components.ComponentProvider;
import ohos.app.Context;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Component ProviderUtils
 */
public class ComponentProviderUtils {
    private static final int DIM_VERSION = 2;
    private static final Solar solar = DateUtils.getCurrentSolar();
    private static final Lunar lunar = DateUtils.getCurrentLunar();

    /**
     * Obtains the ComponentProvider object
     *
     * @param controller form info
     * @param context    context
     * @return component provider
     */
    public static ComponentProvider updateComponentProvider(FormController controller, Context context) {
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
        String hour = String.format("%2d", solar.getHour()).replace(" ", "0");
        String minute = String.format("%2d", solar.getMinute()).replace(" ", "0");
        String second = String.format("%2d", solar.getSecond()).replace(" ", "0");
        componentProvider.setText(ResourceTable.Id_date, solar.toYmd());
        componentProvider.setText(ResourceTable.Id_time, hour + ":" + minute + ":" + second);
        componentProvider.setText(ResourceTable.Id_week, "星期" + solar.getWeekInChinese());
        componentProvider.setText(ResourceTable.Id_lunar_year, lunar.getYearInChinese() + "年");
        componentProvider.setText(ResourceTable.Id_lunar_mouth, lunar.getMonthInChinese() + "月");
        componentProvider.setText(ResourceTable.Id_lunar_day, lunar.getDayInChinese() + "日");
        componentProvider.setText(ResourceTable.Id_solarTerms, lunar.getJieQi());
        componentProvider.setText(ResourceTable.Id_ganzhi_year, lunar.getYearInGanZhiByLiChun() + "年");
        componentProvider.setText(ResourceTable.Id_ganzhi_mouth, lunar.getMonthInGanZhiExact() + "月");
        componentProvider.setText(ResourceTable.Id_ganzhi_day, lunar.getDayInGanZhiExact() + "日");
        componentProvider.setText(ResourceTable.Id_ganzhi_time, lunar.getTimeInGanZhi() + "时");
//        if (flag) {
//            setImageComponentProviderValue(componentProvider);
//            flag
//        }
    }

    /**
     * Set the value of componentProvider
     *
     * @param componentProvider component provider
     */
    private static void setImageComponentProviderValue(ComponentProvider componentProvider) {
        Dictionary<String, Integer> lunTimeNums = lunarTimeNums();
        int lunarMonthNum = lunar.getMonth();
        int lunarDayNum = lunar.getDay();
        int lunarTimeNum = lunTimeNums.get(lunar.getTimeZhi());
        int monthStepNum = lunarMonthNum % 6;
        int dayStepNum = (lunarDayNum + monthStepNum) % 6;
        int timeStepNum = (lunarTimeNum + dayStepNum) % 6;
        setImage(componentProvider, monthStepNum, ResourceTable.Id_aided);
        setImage(componentProvider, dayStepNum, ResourceTable.Id_assistant);
        setImage(componentProvider, timeStepNum, ResourceTable.Id_main);
    }

    private static void setImage(ComponentProvider componentProvider, int stepNum, int componentId) {
        switch (stepNum) {
            case 0:
                componentProvider.setImageContent(componentId, ResourceTable.Media_kongwang);
                break;
            case 1:
                componentProvider.setImageContent(stepNum, ResourceTable.Media_daan);
                break;
            case 2:
                componentProvider.setImageContent(stepNum, ResourceTable.Media_liulian);
                break;
            case 3:
                componentProvider.setImageContent(stepNum, ResourceTable.Media_suxi);
                break;
            case 4:
                componentProvider.setImageContent(stepNum, ResourceTable.Media_chikou);
                break;
            case 5:
                componentProvider.setImageContent(stepNum, ResourceTable.Media_xiaoji);
                break;
        }
    }

    private static Dictionary<String, Integer> lunarTimeNums() {
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
}