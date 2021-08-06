package com.tianchenxu.smallliuren;

import com.tianchenxu.smallliuren.utils.ComponentProviderUtils;
import com.tianchenxu.smallliuren.utils.DateUtils;

import com.tianchenxu.smallliuren.widget.controller.FormController;
import com.tianchenxu.smallliuren.widget.controller.FormControllerManager;
import com.tianchenxu.smallliuren.widget.widget.WidgetImpl;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.FormException;
import ohos.aafwk.content.Intent;
import ohos.agp.components.ComponentProvider;
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
    private FormControllerManager formControllerManager;

    @Override
    public void onStart(Intent intent) {
        HiLog.error(LABEL_LOG, "TimerAbility::onStart");
        formControllerManager = FormControllerManager.getInstance(this);
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
        content.setText(DateUtils.getCurrentSolar().toYmdHms());
        NotificationRequest.NotificationContent notificationContent = new NotificationRequest.NotificationContent(content);
        request.setContent(notificationContent);
        // 绑定通知
        keepBackgroundRunning(NOTICE_ID, request);
    }

    private void updateForms() {
        // 获取卡片列表
        List<Long> formIdList = formControllerManager.getAllFormIdFromSharePreference();
        // 更新时分秒
        if (formIdList.size() <= 0) {
            return;
        }
        for (Long formId : formIdList) {
            WidgetImpl controller = (WidgetImpl) formControllerManager.getController(formId);
            ComponentProvider componentProvider = ComponentProviderUtils.updateComponentProvider(controller, this);
            try {
                // 遍历卡片列表更新卡片
                updateForm(formId, componentProvider);
            } catch (Exception e) {
                formControllerManager.deleteFormController(formId);
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