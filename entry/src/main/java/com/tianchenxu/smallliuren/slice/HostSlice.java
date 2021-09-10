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

package com.tianchenxu.smallliuren.slice;

import com.nlf.calendar.Solar;
import com.lxj.xpopup.XPopup.Builder;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.interfaces.SimpleCallback;
import com.nlf.calendar.Lunar;
import com.tianchenxu.smallliuren.CustomPopup.DateSelector;
import com.tianchenxu.smallliuren.database.FormDatabase;
import com.tianchenxu.smallliuren.utils.BaseData;
import com.tianchenxu.smallliuren.utils.ComponentUtils;
import com.tianchenxu.smallliuren.utils.DateUtils;
import com.tianchenxu.smallliuren.utils.LogUtils;
import com.tianchenxu.smallliuren.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;
import ohos.data.DatabaseHelper;
import ohos.data.orm.OrmContext;
import ohos.eventhandler.EventHandler;
import ohos.eventhandler.EventRunner;
import ohos.eventhandler.InnerEvent;
import ohos.hiviewdfx.HiLogLabel;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Clock Card Slice
 */
public class HostSlice extends AbilitySlice implements Component.ClickedListener {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(0, 0, HostSlice.class.getName());
    private static final long SEND_PERIOD = 1000L;
    private DatabaseHelper helper = new DatabaseHelper(this);
    private static final String DATABASE_NAME = "FormDatabase.db";
    private static final String DATABASE_NAME_ALIAS = "FormDatabase";
    private OrmContext connect;
    private EventRunner runner;
    private MyEventHandle myEventHandle;
    private AbilitySlice slice = this;
    private Timer timer;
    private Runnable runnable = new Runnable() {
        private void initHandler() {
            runner = EventRunner.getMainEventRunner();
            if (runner == null) {
                return;
            }
            myEventHandle = new MyEventHandle(runner);
        }

        @Override
        public void run() {
            // 初始化认证对象
            initHandler();
        }
    };

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        if (connect == null) {
            connect = helper.getOrmContext(DATABASE_NAME_ALIAS, DATABASE_NAME, FormDatabase.class);
        }
        BaseData.initBaseData(connect, helper);
        initComponent(1);
        startTimer();
    }

    private void startTimer() {
        timer = new Timer();
        if (connect == null) {
            connect = helper.getOrmContext(DATABASE_NAME_ALIAS, DATABASE_NAME, FormDatabase.class);
        }
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        runnable.run();
                        int flag = DateUtils.getFlag(connect);
                        myEventHandle.sendEvent(flag);
                    }
                },
                0,
                SEND_PERIOD);
    }

    /**
     * Init Component
     */
    private void initComponent(int flag) {
        ComponentUtils.setComponentValue(slice, flag, connect);
        findComponentById(ResourceTable.Id_calendarIcon).setClickedListener(this);
        findComponentById(ResourceTable.Id_solarRadio).setClickedListener(this);
        findComponentById(ResourceTable.Id_lunarRadio).setClickedListener(this);
        findComponentById(ResourceTable.Id_refresh).setClickedListener(this);
    }


    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    @Override
    public void onClick(Component component) {
        switch (component.getId()) {
            case ResourceTable.Id_solarRadio:
            case ResourceTable.Id_lunarRadio:
                RadioContainer container = (RadioContainer) findComponentById(ResourceTable.Id_calendarRadio);
                TextField selectDate = (TextField) findComponentById(ResourceTable.Id_selectDate);
                container.setMarkChangedListener((radioContainer, i) -> {
                    Image calendarIcon = (Image) findComponentById(ResourceTable.Id_calendarIcon);
                    if (i == 1) {
                        calendarIcon.setHeight(0);
                        calendarIcon.setWidth(0);
                        String solar = selectDate.getText();
                        Lunar lunar = DateUtils.getLunar(solar);
                        selectDate.setText(String.format("%4d-%02d-%02d", lunar.getYear(), lunar.getMonth(), lunar.getDay()));
                    } else if (i == 0) {
                        calendarIcon.setHeight(90);
                        calendarIcon.setWidth(90);
                        String lunar = selectDate.getText();
                        Solar solar = DateUtils.getSolar(lunar);
                        selectDate.setText(String.format("%4d-%02d-%02d", solar.getYear(), solar.getMonth(), solar.getDay()));
                    }
                });
                break;
            case ResourceTable.Id_calendarIcon:
                DateSelector dateSelector = new DateSelector(slice);
                new Builder(slice)
                        .setPopupCallback(new SimpleCallback() {
                            final TextField selectDate = (TextField) findComponentById(ResourceTable.Id_selectDate);
                            final String oldDate = selectDate.getText();

                            @Override
                            public void onShow(BasePopupView basePopupView) {
                                super.onShow(basePopupView);
                            }

                            @Override
                            public void onDismiss(BasePopupView basePopupView) {
                                super.onDismiss(basePopupView);
                                String dateString;
                                if (oldDate != dateSelector.getDate()) {
                                    dateString = dateSelector.getDate();
                                    selectDate.setText(dateString);
                                } else {
                                    dateString = oldDate;
                                    selectDate.setText(dateString);
                                }
                                timer.cancel();
                                ComponentUtils.setComponentValue(slice, 1, connect, dateString);
                            }
                        })
                        .isComponentMode(true, component)
                        .asCustom(dateSelector)
                        .show();
                break;
            case ResourceTable.Id_refresh:
                timer.cancel();
                initComponent(1);
                startTimer();
                break;
        }
    }

    private class MyEventHandle extends EventHandler {
        MyEventHandle(EventRunner runner) throws IllegalArgumentException {
            super(runner);
        }

        @Override
        protected void processEvent(InnerEvent event) {
            super.processEvent(event);
            int eventId = event.eventId;
            initComponent(eventId);
        }
    }

    @Override
    protected void onStop() {
        LogUtils.info("onStop", " start to destroy slice");
        timer.cancel();
    }
}
