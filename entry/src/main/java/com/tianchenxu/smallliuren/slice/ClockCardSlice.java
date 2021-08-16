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

import com.tianchenxu.smallliuren.database.FormDatabase;
import com.tianchenxu.smallliuren.utils.BaseData;
import com.tianchenxu.smallliuren.utils.ComponentUtils;
import com.tianchenxu.smallliuren.utils.DateUtils;
import com.tianchenxu.smallliuren.utils.LogUtils;
import com.tianchenxu.smallliuren.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.Text;
import ohos.data.DatabaseHelper;
import ohos.data.orm.OrmContext;
import ohos.eventhandler.EventHandler;
import ohos.eventhandler.EventRunner;
import ohos.eventhandler.InnerEvent;
import ohos.hiviewdfx.HiLogLabel;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Clock Card Slice
 */
public class ClockCardSlice extends AbilitySlice {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(0, 0, ClockCardSlice.class.getName());
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
    }



    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
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
