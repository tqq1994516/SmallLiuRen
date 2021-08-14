package com.tianchenxu.smallliuren.utils;

import com.tianchenxu.smallliuren.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.Component;
import ohos.agp.components.Text;
import ohos.data.orm.OrmContext;

import java.util.Calendar;

public class CommponentUtils {
    private Text dateText;
    private Text timeText;
    private Text weekText;
    private Text lunarYearText;
    private Text lunarMonthText;
    private Text lunarDayText;
    private Text solarTermsText;
    private Text ganzhiYearText;
    private Text ganzhiMonthText;
    private Text ganzhiDayText;
    private Text ganzhiTimeText;

    /**
     * Set the value of component
     *
     * @param flag update flag
     * @param slice slice info
     * @param connect OrmContext
     */
    private static void setComponentProviderValue(int flag, AbilitySlice slice, OrmContext connect) {
        Calendar now = Calendar.getInstance();
        Component dateComponent = slice.findComponentById(ResourceTable.Id_date);
        Component weekComponent = slice.findComponentById(ResourceTable.Id_week);
        Component timeComponent = slice.findComponentById(ResourceTable.Id_time);
        Component lunarYearComponent = slice.findComponentById(ResourceTable.Id_lunar_year);
        Component lunarMonthComponent = slice.findComponentById(ResourceTable.Id_lunar_month);
        Component lunarDayComponent = slice.findComponentById(ResourceTable.Id_lunar_day);
        Component solarTermsComponent = slice.findComponentById(ResourceTable.Id_solarTerms);
        Component ganzhiYearComponent = slice.findComponentById(ResourceTable.Id_ganzhi_year);
        Component ganzhiMonthComponent = slice.findComponentById(ResourceTable.Id_ganzhi_month);
        Component ganzhiDayComponent = slice.findComponentById(ResourceTable.Id_ganzhi_day);
        Component ganzhiTimeComponent = slice.findComponentById(ResourceTable.Id_ganzhi_time);

        componentProvider.setText(ResourceTable.Id_date, DateUtils.getCurrentDate(now,"yyyy-MM-dd"));
        componentProvider.setText(ResourceTable.Id_time, DateUtils.getCurrentDate(now,"HH:mm:ss"));
        componentProvider.setText(ResourceTable.Id_week, DateUtils.getCurrentDate(now,"EEEE"));
        componentProvider.setText(ResourceTable.Id_lunar_year, lunar.getYearInChinese());
        componentProvider.setText(ResourceTable.Id_lunar_month, lunar.getMonthInChinese() + "月");
        componentProvider.setText(ResourceTable.Id_lunar_day, lunar.getDayInChinese());
        componentProvider.setText(ResourceTable.Id_solarTerms, lunar.getJieQi());
        componentProvider.setText(ResourceTable.Id_ganzhi_year, lunar.getYearInGanZhiByLiChun() + "年");
        componentProvider.setText(ResourceTable.Id_ganzhi_month, lunar.getMonthInGanZhiExact() + "月");
        componentProvider.setText(ResourceTable.Id_ganzhi_day, lunar.getDayInGanZhiExact() + "日");
        componentProvider.setText(ResourceTable.Id_ganzhi_time, lunar.getTimeInGanZhi() + "时");
        if (dateComponent != null && dateComponent instanceof Text) {
            Text dateTextComponent = (Text) dateComponent;
            dateTextComponent.setText(DateUtils.getCurrentDate(now,"yyyy-MM-dd"));
        }
    }
}
