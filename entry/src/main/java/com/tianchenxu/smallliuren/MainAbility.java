package com.tianchenxu.smallliuren;

import com.tianchenxu.smallliuren.slice.MainAbilitySlice;
import com.tianchenxu.smallliuren.utils.ComponentProviderUtils;
import com.tianchenxu.smallliuren.widget.controller.*;

import com.tianchenxu.smallliuren.widget.widget.WidgetImpl;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.ProviderFormInfo;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.ComponentProvider;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class MainAbility extends Ability {
    public static final int DIMENSION_4X4 = 4;
    private static final int DEFAULT_DIMENSION_2X2 = 2;
    private int dimension = DIMENSION_4X4;
    private static final int INVALID_FORM_ID = -1;
    private static final String EMPTY_STRING = "";
    private ProviderFormInfo formInfo;
    private WidgetImpl widget;
    private ComponentProvider componentProvider;
    private FormControllerManager formControllerManager;
    private static final HiLogLabel TAG = new HiLogLabel(HiLog.DEBUG, 0x0, MainAbility.class.getName());
    private String topWidgetSlice;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        // 启动TimerAbility
        Intent timer = new Intent();
        Operation operation = new Intent.OperationBuilder()
                .withDeviceId("")
                .withBundleName(getBundleName())
                .withAbilityName(TimerAbility.class.getName())
                .build();
        timer.setOperation(operation);
        startAbility(timer);
        super.setMainRoute(MainAbilitySlice.class.getName());
        if (intentFromWidget(intent)) {
            topWidgetSlice = getRoutePageSlice(intent);
            if (topWidgetSlice != null) {
                setMainRoute(topWidgetSlice);
            }
        }
        stopAbility(intent);
    }

    @Override
    protected ProviderFormInfo onCreateForm(Intent intent) {
        HiLog.info(TAG, "onCreateForm");
        if (intent == null) {
            return new ProviderFormInfo();
        }

        // 获取卡片Id
        long formId;
        if (intent.hasParameter(AbilitySlice.PARAM_FORM_IDENTITY_KEY)) {
            formId = intent.getLongParam(AbilitySlice.PARAM_FORM_IDENTITY_KEY, INVALID_FORM_ID);
        } else {
            return new ProviderFormInfo();
        }
        // 获取卡片名称
        String formName = EMPTY_STRING;
        if (intent.hasParameter(AbilitySlice.PARAM_FORM_NAME_KEY)) {
            formName = intent.getStringParam(AbilitySlice.PARAM_FORM_NAME_KEY);
        }
        // 获取卡片规格
        if (intent.hasParameter(AbilitySlice.PARAM_FORM_DIMENSION_KEY)) {
            dimension = intent.getIntParam(AbilitySlice.PARAM_FORM_DIMENSION_KEY, DIMENSION_4X4);
        }
        int layoutId = ResourceTable.Layout_form_grid_pattern_widget_4_4;
        if (dimension == DEFAULT_DIMENSION_2X2) {
            layoutId = ResourceTable.Layout_form_grid_pattern_widget_2_2;
        }
        HiLog.info(TAG, "onCreateForm: formId=" + formId + ",formName=" + formName);
        formControllerManager = FormControllerManager.getInstance(this);
        widget = new WidgetImpl(this, formName, dimension);
        formInfo = widget.bindFormData();
        componentProvider = ComponentProviderUtils.updateComponentProvider(widget, this);
        formInfo.mergeActions(componentProvider);
        if (widget == null) {
            HiLog.error(TAG, "Get null controller. formId: " + formId + ", formName: " + formName);
            return null;
        }
        return formInfo;
    }

    @Override
    protected void onUpdateForm(long formId) {
        HiLog.info(TAG, "onUpdateForm");
        super.onUpdateForm(formId);
        widget.updateFormData(formId);
    }

    @Override
    protected void onDeleteForm(long formId) {
        HiLog.info(TAG, "onDeleteForm: formId=" + formId);
        super.onDeleteForm(formId);
        formControllerManager.deleteFormController(formId);
    }

    @Override
    protected void onTriggerFormEvent(long formId, String message) {
        HiLog.info(TAG, "onTriggerFormEvent: " + message);
        super.onTriggerFormEvent(formId, message);
        widget.onTriggerFormEvent(formId, message);
    }

    @Override
    public void onNewIntent(Intent intent) {
        if (intentFromWidget(intent)) { // Only response to it when starting from a service widget.
            String newWidgetSlice = getRoutePageSlice(intent);
            if (topWidgetSlice == null || !topWidgetSlice.equals(newWidgetSlice)) {
                topWidgetSlice = newWidgetSlice;
                restart();
            }
        }
    }

    private boolean intentFromWidget(Intent intent) {
        long formId = intent.getLongParam(AbilitySlice.PARAM_FORM_IDENTITY_KEY, INVALID_FORM_ID);
        return formId != INVALID_FORM_ID;
    }

    private String getRoutePageSlice(Intent intent) {
        long formId = intent.getLongParam(AbilitySlice.PARAM_FORM_IDENTITY_KEY, INVALID_FORM_ID);
        if (formId == INVALID_FORM_ID) {
            return null;
        }
        if (widget == null) {
            return null;
        }
        Class<? extends AbilitySlice> clazz = widget.getRoutePageSlice(intent);
        if (clazz == null) {
            return null;
        }
        return clazz.getName();
    }
}
