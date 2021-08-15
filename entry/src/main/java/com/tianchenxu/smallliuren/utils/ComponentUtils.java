package com.tianchenxu.smallliuren.utils;

import com.nlf.calendar.Lunar;
import com.tianchenxu.smallliuren.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.ComponentProvider;
import ohos.agp.components.Text;
import ohos.app.Context;
import ohos.data.orm.OrmContext;

import java.util.Calendar;

public class ComponentUtils {
    private static Text dateText;
    private static Text timeText;
    private static Text weekText;
    private static Text lunarYearText;
    private static Text lunarMonthText;
    private static Text lunarDayText;
    private static Text solarTermsText;
    private static Text Text;

    /**
     * Set the value of component
     *
     * @param flag
     * @param context
     * @param connect
     */
    private static void setComponentValue(AbilitySlice slice, int flag, Context context, OrmContext connect) {
        Calendar now = Calendar.getInstance();
        Lunar lunar = DateUtils.getLunar(now);
        String date = DateUtils.getCurrentDate(now, "yyyy-MM-dd");
        setTextValue(date, hourText);
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
        if (flag == 1) {
            setImageComponentProviderValue(componentProvider, context, connect, lunar);
        }
    }

    private void setTextValue(String str, Text text) {
        text.setText(str);
    }
}
