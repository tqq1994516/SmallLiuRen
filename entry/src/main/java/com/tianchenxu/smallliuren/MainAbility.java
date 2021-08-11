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

import com.tianchenxu.smallliuren.database.Form;
import com.tianchenxu.smallliuren.database.FormDatabase;
import com.tianchenxu.smallliuren.slice.ClockCardSlice;
import ohos.aafwk.ability.*;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.ComponentProvider;
import ohos.data.DatabaseHelper;
import ohos.data.dataability.DataAbilityPredicates;
import ohos.data.orm.OrmContext;
import ohos.data.rdb.ValuesBucket;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import com.tianchenxu.smallliuren.utils.ComponentProviderUtils;
import com.tianchenxu.smallliuren.utils.DatabaseUtils;
import ohos.utils.net.Uri;

/**
 * Card Main Ability
 */
public class MainAbility extends Ability {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(0, 0, "com.huawei.cookbooks.MainAbility");
    private static final int DEFAULT_DIMENSION_2X2 = 2;
    private static final int DEFAULT_DIMENSION_4X4 = 4;
    private static final String EMPTY_STRING = "";
    private static final int INVALID_FORM_ID = -1;
    private static final String BASE_URI = "dataability://com.tianchenxu.smallliuren.DataAbility";
    private DataAbilityHelper dataAbilityHelper;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        // 启动dataAbili
        dataAbilityHelper = DataAbilityHelper.creator(this);
        // 启动serviceAbility
        Intent intentService = new Intent();
        Operation operation =
                new Intent.OperationBuilder()
                        .withDeviceId("")
                        .withBundleName(getBundleName())
                        .withAbilityName(TimerAbility.class.getName())
                        .build();
        intentService.setOperation(operation);
        startAbility(intentService);
        super.setMainRoute(ClockCardSlice.class.getName());
    }

    @Override
    protected ProviderFormInfo onCreateForm(Intent intent) {
        if (intent == null) {
            return new ProviderFormInfo();
        }
        // 获取卡片id
        long formId = INVALID_FORM_ID;
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
        int dimension = DEFAULT_DIMENSION_4X4;
        if (intent.hasParameter(AbilitySlice.PARAM_FORM_DIMENSION_KEY)) {
            dimension = intent.getIntParam(AbilitySlice.PARAM_FORM_DIMENSION_KEY, DEFAULT_DIMENSION_2X2);
        }
        int layoutId = ResourceTable.Layout_form_grid_pattern_widget_4_4;
        if (dimension == DEFAULT_DIMENSION_2X2) {
            layoutId = ResourceTable.Layout_form_grid_pattern_widget_2_2;
        }
        ProviderFormInfo formInfo = new ProviderFormInfo(layoutId, this);
        // 存储卡片信息
        Form form = new Form(formId, formName, dimension);
        ComponentProvider componentProvider = ComponentProviderUtils.getComponentProvider(form, this, 1);
        try {
            updateForm(formId, componentProvider);
        } catch (FormException e) {
            DataAbilityPredicates dataAbilityPredicates = new DataAbilityPredicates();
            dataAbilityPredicates.equalTo("formId", formId);
            try {
                dataAbilityHelper.delete(Uri.parse(BASE_URI), dataAbilityPredicates);
            } catch (DataAbilityRemoteException dataAbilityRemoteException) {
                HiLog.info(LABEL_LOG, "delete form failed");
            }
            HiLog.error(LABEL_LOG, "onUpdateForm updateForm error");
        }
        try {
            ValuesBucket valuesBucket = new ValuesBucket();
            valuesBucket.putLong("formId", form.getFormId());

            dataAbilityHelper.insert();
            DatabaseUtils.insertForm(form, connect);
        } catch (Exception e) {
            DatabaseUtils.deleteFormData(form.getFormId(), connect);
        }
        return formInfo;
    }

    @Override
    protected void onDeleteForm(long formId) {
        super.onDeleteForm(formId);
        // 删除数据库中的卡片信息
        DatabaseUtils.deleteFormData(formId, connect);
    }
}
