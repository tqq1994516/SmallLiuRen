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
import com.tianchenxu.smallliuren.database.*;
import ohos.agp.components.ComponentProvider;
import ohos.app.Context;
import ohos.data.DatabaseHelper;
import ohos.data.orm.OrmContext;
import ohos.global.resource.NotExistException;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.media.image.ImageSource;
import ohos.media.image.PixelMap;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.Objects;

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
    public static ComponentProvider getComponentProvider(Form form, Context context, int flag, OrmContext connect) {
        int layoutId = ResourceTable.Layout_form_grid_pattern_widget_4_4;
        if (form.getDimension() == DIM_VERSION) {
            layoutId = ResourceTable.Layout_form_grid_pattern_widget_2_2;
        }
        ComponentProvider componentProvider = new ComponentProvider(layoutId, context);
        setComponentProviderValue(componentProvider, flag, context, connect);
        return componentProvider;
    }

    /**
     * Set the value of componentProvider
     *
     * @param componentProvider component provider
     */
    private static void setComponentProviderValue(ComponentProvider componentProvider, int flag, Context context, OrmContext connect) {
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
            setImageComponentProviderValue(componentProvider, context, connect, now, lunar);
        }
    }

    /**
     * Set the value of componentProvider
     *
     * @param componentProvider component provider
     */
    private static void setImageComponentProviderValue(ComponentProvider componentProvider, Context context, OrmContext connect, Calendar now, Lunar lunar) {
        int lunarMonthNum = lunar.getMonth();
        int lunarDayNum = lunar.getDay();
        int lunarTimeNum = Objects.requireNonNull(DatabaseUtils.queryDizhiByName(lunar.getTimeZhi(), connect)).getDizhiNum();
        int monthStepNum = lunarMonthNum % 6;
        int dayStepNum = (lunarDayNum + monthStepNum - 1) % 6;
        int timeStepNum = (lunarTimeNum + dayStepNum - 1) % 6;
        int month_yinyang = Objects.requireNonNull(DatabaseUtils.queryTianganByName(lunar.getMonthGanExact(), connect)).getTianganYinyang();
        int day_yinyang = Objects.requireNonNull(DatabaseUtils.queryTianganByName(lunar.getDayGanExact(), connect)).getTianganYinyang();
        int time_yinyang = Objects.requireNonNull(DatabaseUtils.queryTianganByName(lunar.getTimeGan(), connect)).getTianganYinyang();
        setImage(componentProvider, monthStepNum, ResourceTable.Id_aided, context, 1);
        setImage(componentProvider, dayStepNum, ResourceTable.Id_assistant, context, 1);
        setImage(componentProvider, timeStepNum, ResourceTable.Id_main, context, 1);
        setImage(componentProvider, month_yinyang, ResourceTable.Id_aided_flag, context, 2);
        setImage(componentProvider, day_yinyang, ResourceTable.Id_assistant_flag, context, 2);
        setImage(componentProvider, time_yinyang, ResourceTable.Id_main_flag, context, 2);
        setDetailText(componentProvider, timeStepNum, dayStepNum, connect);
    }

    private static void setDetailText(ComponentProvider componentProvider, int timeNum, int dayNum, OrmContext connect) {
        Attribute attribute = DatabaseUtils.queryAttributeByInt(timeNum, connect);
        Deity deity = DatabaseUtils.queryDeityById(attribute.getDeity(), connect);
        FiveElements fiveElements = DatabaseUtils.queryFiveElementsById(attribute.getFiveElements(), connect);
        Organ organ = DatabaseUtils.queryOrganById(attribute.getOrgan(), connect);
        Orientation magnateOrientation = DatabaseUtils.queryOrientationById(attribute.getMagnateOrientation(), connect);
        Orientation offendOrientation = DatabaseUtils.queryOrientationById(attribute.getOffendOrientation(), connect);
        Assert anAssert = DatabaseUtils.queryAssertByNums(dayNum, timeNum, connect);
        componentProvider.setText(ResourceTable.Id_deity, deity.getDeityName());
        componentProvider.setText(ResourceTable.Id_fiveElements, fiveElements.getFiveElementsName());
        componentProvider.setText(ResourceTable.Id_organ, organ.getOrganName());
        componentProvider.setText(ResourceTable.Id_magnateOrientation, magnateOrientation.getOrientationName());
        componentProvider.setText(ResourceTable.Id_luckyNum, attribute.getLuckyNum());
        componentProvider.setText(ResourceTable.Id_successNum, attribute.getSuccessNum());
        componentProvider.setText(ResourceTable.Id_ominousNum, attribute.getOminousNum());
        componentProvider.setText(ResourceTable.Id_offendOrientation, offendOrientation.getOrientationName());
        componentProvider.setText(ResourceTable.Id_ghostsAndGods, attribute.getGhostsAndGods());
        componentProvider.setText(ResourceTable.Id_assertText, anAssert.getAssertText());
    }


    private static void setImage(ComponentProvider componentProvider, int stepNum, int componentId, Context context, int type) {
        PixelMap pixelMap = null;
        if (type==1) {
            switch (stepNum) {
                case 0:
                    pixelMap = getPixelMapFromResource(ResourceTable.Media_kongwang, context);
                    componentProvider.setImagePixelMap(componentId, pixelMap);
                    break;
                case 1:
                    pixelMap = getPixelMapFromResource(ResourceTable.Media_daan, context);
                    componentProvider.setImagePixelMap(componentId, pixelMap);
                    break;
                case 2:
                    pixelMap = getPixelMapFromResource(ResourceTable.Media_liulian, context);
                    componentProvider.setImagePixelMap(componentId, pixelMap);
                    break;
                case 3:
                    pixelMap = getPixelMapFromResource(ResourceTable.Media_suxi, context);
                    componentProvider.setImagePixelMap(componentId, pixelMap);
                    break;
                case 4:
                    pixelMap = getPixelMapFromResource(ResourceTable.Media_chikou, context);
                    componentProvider.setImagePixelMap(componentId, pixelMap);
                    break;
                case 5:
                    pixelMap = getPixelMapFromResource(ResourceTable.Media_xiaoji, context);
                    componentProvider.setImagePixelMap(componentId, pixelMap);
                    break;
            }
        } else if (type == 2) {
            switch (stepNum) {
                case 1:
                    pixelMap = getPixelMapFromResource(ResourceTable.Media_yang, context);
                    componentProvider.setImagePixelMap(componentId, pixelMap);
                    break;
                case 2:
                    pixelMap = getPixelMapFromResource(ResourceTable.Media_yin, context);
                    componentProvider.setImagePixelMap(componentId, pixelMap);
                    break;
            }
        }
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
