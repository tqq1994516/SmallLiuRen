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

package com.tianchenxu.smallliuren;

import com.nlf.calendar.Lunar;
import com.tianchenxu.smallliuren.database.*;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.FormException;
import ohos.aafwk.content.Intent;
import ohos.agp.components.ComponentProvider;
import ohos.data.DatabaseHelper;
import ohos.data.orm.OrmContext;
import ohos.data.orm.OrmPredicates;
import ohos.event.notification.NotificationRequest;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import com.tianchenxu.smallliuren.utils.ComponentProviderUtils;
import com.tianchenxu.smallliuren.utils.DatabaseUtils;
import com.tianchenxu.smallliuren.utils.DateUtils;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Time PA
 */
public class TimerAbility extends Ability {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "Demo");
    private static final long SEND_PERIOD = 1000L;
    private static final int NOTICE_ID = 1005;
    private DatabaseHelper helper = new DatabaseHelper(this);
    private static final String DATABASE_NAME = "FormDatabase.db";
    private static final String DATABASE_NAME_ALIAS = "FormDatabase";
    private OrmContext connect;

    @Override
    public void onStart(Intent intent) {
        HiLog.info(LABEL_LOG, "TimerAbility::onStart");
        connect = helper.getOrmContext(DATABASE_NAME_ALIAS, DATABASE_NAME, FormDatabase.class);
        startTimer();
        super.onStart(intent);
    }


    private void notice() {
        // 创建通知
        NotificationRequest request = new NotificationRequest(NOTICE_ID);
        request.setAlertOneTime(true);
        NotificationRequest.NotificationNormalContent content = new NotificationRequest.NotificationNormalContent();
        Calendar now = Calendar.getInstance();
        content.setText(DateUtils.getCurrentDate(now, "yyyy-MM-dd HH:mm:ss"));
        NotificationRequest.NotificationContent notificationContent = new NotificationRequest.NotificationContent(content);
        request.setContent(notificationContent);
        // 绑定通知
        keepBackgroundRunning(NOTICE_ID, request);
    }

    // 卡片更新定时器，每秒更新一次
    private void startTimer() {
        Timer timer = new Timer();
        if (connect == null) {
            connect = helper.getOrmContext(DATABASE_NAME_ALIAS, DATABASE_NAME, FormDatabase.class);
        }
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        int flag = DateUtils.getFlag(connect);
                        updateForms(flag);
                        notice();
                    }
                },
                0,
                SEND_PERIOD);
    }

    private void updateForms(int flag) {
        // 从数据库中获取卡片信息
        OrmPredicates ormPredicates = new OrmPredicates(Form.class);
        List<Form> formList = connect.query(ormPredicates);
        // 更新时分秒
        if (formList.size() <= 0) {
            return;
        }
        for (Form form : formList) {
            // 遍历卡片列表更新卡片
            ComponentProvider componentProvider = ComponentProviderUtils.getComponentProvider(form, this, flag, connect);
            HiLog.info(LABEL_LOG, flag + "");
            try {
                Long updateFormId = form.getFormId();
                updateForm(updateFormId, componentProvider);
            } catch (FormException e) {
                // 删除不存在的卡片
                DatabaseUtils.deleteFormData(form.getFormId(), connect);
                HiLog.error(LABEL_LOG, "onUpdateForm updateForm error");
            }
        }
    }

    @Override
    public void onBackground() {
        super.onBackground();
        HiLog.info(LABEL_LOG, "TimerAbility::onBackground");
    }

    @Override
    public void onStop() {
        super.onStop();
        HiLog.info(LABEL_LOG, "TimerAbility::onStop");
    }
}
