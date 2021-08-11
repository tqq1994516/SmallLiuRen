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
import com.tianchenxu.smallliuren.ResourceTable;
import com.tianchenxu.smallliuren.database.Form;
import ohos.agp.components.ComponentProvider;
import ohos.agp.components.Image;
import ohos.agp.utils.Color;
import ohos.app.Context;
import ohos.global.resource.NotExistException;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.media.image.ImageSource;
import ohos.media.image.PixelMap;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Component ProviderUtils
 */
public class ComponentProviderUtils {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "Demo");
    private static final int DIM_VERSION = 2;

    /**
     * Obtains the ComponentProvider object
     *
     * @param form form info
     * @param context context
     * @return component provider
     */
    public static ComponentProvider getComponentProvider(Form form, Context context, int flag) {
        int layoutId = ResourceTable.Layout_form_grid_pattern_widget_4_4;
        if (form.getDimension() == DIM_VERSION) {
            layoutId = ResourceTable.Layout_form_grid_pattern_widget_2_2;
        }
        ComponentProvider componentProvider = new ComponentProvider(layoutId, context);
        setComponentProviderValue(componentProvider, flag, context);
        return componentProvider;
    }

    /**
     * Set the value of componentProvider
     *
     * @param componentProvider component provider
     */
    private static void setComponentProviderValue(ComponentProvider componentProvider, int flag, Context context) {
        Calendar now = Calendar.getInstance();
        Lunar lunar = DateUtils.getLunar(now);
        String lunarHour = lunar.getTimeInGanZhi();
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
        componentProvider.setText(ResourceTable.Id_ganzhi_time, lunarHour + "时");
        if (flag == 1) {
            setImageComponentProviderValue(componentProvider, context);
        }
    }

    /**
     * Set the value of componentProvider
     *
     * @param componentProvider component provider
     */
    private static void setImageComponentProviderValue(ComponentProvider componentProvider, Context context) {
        Calendar now = Calendar.getInstance();
        Lunar lunar = DateUtils.getLunar(now);
        Dictionary<String, Integer> lunTimeNums = lunarTimeNums();
        int lunarMonthNum = lunar.getMonth();
        int lunarDayNum = lunar.getDay();
        int lunarTimeNum = lunTimeNums.get(lunar.getTimeZhi());
        int monthStepNum = lunarMonthNum % 6;
        int dayStepNum = (lunarDayNum + monthStepNum - 1) % 6;
        int timeStepNum = (lunarTimeNum + dayStepNum - 1) % 6;
        String monthtiangan = lunar.getMonthGanExact();
        String daytiangan = lunar.getDayGanExact();
        String timetiangan = lunar.getTimeGan();
        Dictionary<String, Integer> tianganyinyang_property = tianganyinyangProperty();
        int month_yinyang = tianganyinyang_property.get(monthtiangan);
        int day_yinyang = tianganyinyang_property.get(daytiangan);
        int time_yinyang = tianganyinyang_property.get(timetiangan);
        setImage(componentProvider, monthStepNum, ResourceTable.Id_aided, context, 1);
        setImage(componentProvider, dayStepNum, ResourceTable.Id_assistant, context, 1);
        setImage(componentProvider, timeStepNum, ResourceTable.Id_main, context, 1);
        setImage(componentProvider, month_yinyang, ResourceTable.Id_aided_flag, context, 2);
        setImage(componentProvider, day_yinyang, ResourceTable.Id_assistant_flag, context, 2);
        setImage(componentProvider, time_yinyang, ResourceTable.Id_main_flag, context, 2);
    }

    private static void setImage(ComponentProvider componentProvider, int stepNum, int componentId, Context context, int type) {
        if (type==1) {
            switch (stepNum) {
                case 0:
                    PixelMap pixelMapFromResource0 = getPixelMapFromResource(ResourceTable.Media_kongwang, context);
                    componentProvider.setImagePixelMap(componentId, pixelMapFromResource0);
                    // 释放PixelMap对象
                    pixelMapFromResource0.release();
                    break;
                case 1:
                    PixelMap pixelMapFromResource1 = getPixelMapFromResource(ResourceTable.Media_daan, context);
                    componentProvider.setImagePixelMap(componentId, pixelMapFromResource1);
                    pixelMapFromResource1.release();
                    break;
                case 2:
                    PixelMap pixelMapFromResource2 = getPixelMapFromResource(ResourceTable.Media_liulian, context);
                    componentProvider.setImagePixelMap(componentId, pixelMapFromResource2);
                    pixelMapFromResource2.release();
                    break;
                case 3:
                    PixelMap pixelMapFromResource3 = getPixelMapFromResource(ResourceTable.Media_suxi, context);
                    componentProvider.setImagePixelMap(componentId, pixelMapFromResource3);
                    pixelMapFromResource3.release();
                    break;
                case 4:
                    PixelMap pixelMapFromResource4 = getPixelMapFromResource(ResourceTable.Media_chikou, context);
                    componentProvider.setImagePixelMap(componentId, pixelMapFromResource4);
                    pixelMapFromResource4.release();
                    break;
                case 5:
                    PixelMap pixelMapFromResource5 = getPixelMapFromResource(ResourceTable.Media_xiaoji, context);
                    componentProvider.setImagePixelMap(componentId, pixelMapFromResource5);
                    pixelMapFromResource5.release();
                    break;
            }
        } else if (type == 2) {
            switch (stepNum) {
                case 0:
                    PixelMap pixelMapFromResource6 = getPixelMapFromResource(ResourceTable.Media_yang, context);
                    componentProvider.setImagePixelMap(componentId, pixelMapFromResource6);
                    pixelMapFromResource6.release();
                    break;
                case 1:
                    PixelMap pixelMapFromResource7 = getPixelMapFromResource(ResourceTable.Media_yin, context);
                    componentProvider.setImagePixelMap(componentId, pixelMapFromResource7);
                    pixelMapFromResource7.release();
                    break;
            }
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

    private static Dictionary<String, Integer> tianganyinyangProperty() {
        Dictionary<String, Integer> tianganyinyang_property = new Hashtable<>();
        tianganyinyang_property.put("甲", 0);
        tianganyinyang_property.put("丙", 0);
        tianganyinyang_property.put("戊", 0);
        tianganyinyang_property.put("庚", 0);
        tianganyinyang_property.put("壬", 0);
        tianganyinyang_property.put("乙", 1);
        tianganyinyang_property.put("丁", 1);
        tianganyinyang_property.put("己", 1);
        tianganyinyang_property.put("辛", 1);
        tianganyinyang_property.put("癸", 1);
        return tianganyinyang_property;
    }

    private static PixelMap getPixelMapFromResource(int resourceId, Context context) {
        InputStream inputStream = null;
        try {
            inputStream = context.getResourceManager().getResource(resourceId);
            ImageSource.SourceOptions sourceOptions = new ImageSource.SourceOptions();
            sourceOptions.formatHint = "image/png";
            ImageSource imageSource = ImageSource.create(inputStream, sourceOptions);
            ImageSource.DecodingOptions decodingOptions = new ImageSource.DecodingOptions();
            PixelMap pixelmap = imageSource.createPixelmap(decodingOptions);
            // 释放ImageSource对象
            imageSource.release();
            return pixelmap;
        } catch (IOException e) {
            HiLog.info(LABEL_LOG, "IOException");
        } catch (NotExistException e) {
            HiLog.info(LABEL_LOG, "NotExistException");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    HiLog.info(LABEL_LOG, "inputStream IOException");
                }
            }
        }
        return null;
    }
}
