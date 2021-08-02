package com.tianchenxu.smallliuren;

import com.tianchenxu.smallliuren.database.Form;
import com.tianchenxu.smallliuren.database.FormDatabase;
import com.tianchenxu.smallliuren.utils.ComponentProviderUtils;
import com.tianchenxu.smallliuren.utils.DatabaseUtils;
import com.tianchenxu.smallliuren.utils.DateUtils;

import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.FormException;
import ohos.aafwk.content.Intent;
import ohos.agp.components.ComponentProvider;
import ohos.data.DatabaseHelper;
import ohos.data.orm.OrmContext;
import ohos.data.orm.OrmPredicates;
import ohos.event.notification.NotificationRequest;
import ohos.rpc.IRemoteObject;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TimerAbility extends Ability {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "Demo");
    private static final long SEND_PERIOD = 1000L;
    private static final int NOTICE_ID = 1005;
    // 初始化数据库工具
    private DatabaseHelper helper = new DatabaseHelper(this);
    private OrmContext connect;

    @Override
    public void onStart(Intent intent) {
        HiLog.error(LABEL_LOG, "TimerAbility::onStart");
        connect = helper.getOrmContext("FormDatabase", "FormDatabase.db", FormDatabase.class);
        // 启动定时器
        startTimer();
        super.onStart(intent);
    }

    //卡片更新定时器，每秒更新一次
    private void startTimer() {
        Timer timer = new Timer();
        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        updateForms();
                        notice();
                    }
                }, 0, SEND_PERIOD
        );
    }

    private void notice() {
        // 创建通知
        NotificationRequest request = new NotificationRequest(NOTICE_ID);
        request.setAlertOneTime(true);
        NotificationRequest.NotificationNormalContent content = new NotificationRequest.NotificationNormalContent();
        content.setText(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
        NotificationRequest.NotificationContent notificationContent = new NotificationRequest.NotificationContent(content);
        request.setContent(notificationContent);
        // 绑定通知
        keepBackgroundRunning(NOTICE_ID, request);
    }

    private void updateForms() {
        // 从数据库获取卡片信息
        OrmPredicates ormPredicates = new OrmPredicates(Form.class);
        // 获取卡片列表
        List<Form> formList = connect.query(ormPredicates);
        // 更新时分秒
        if (formList.size() <= 0) {
            return;
        }
        for (Form form : formList) {
            // 遍历卡片列表更新卡片
            ComponentProvider componentProvider = ComponentProviderUtils.getComponentProvider(form, this);
            try {
                Long formFormId = form.getFormId();
                updateForm(formFormId, componentProvider);
            } catch (FormException e) {
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

    @Override
    public void onCommand(Intent intent, boolean restart, int startId) {
    }

    @Override
    public IRemoteObject onConnect(Intent intent) {
        return null;
    }

    @Override
    public void onDisconnect(Intent intent) {
    }
}