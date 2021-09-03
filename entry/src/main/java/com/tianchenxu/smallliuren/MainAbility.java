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

import com.tianchenxu.smallliuren.database.*;
import com.tianchenxu.smallliuren.database.Form;
import com.tianchenxu.smallliuren.slice.HostSlice;
import com.tianchenxu.smallliuren.utils.ComponentProviderUtils;
import com.tianchenxu.smallliuren.utils.DatabaseUtils;

import com.tianchenxu.smallliuren.utils.DateUtils;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.FormException;
import ohos.aafwk.ability.ProviderFormInfo;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.ComponentProvider;
import ohos.data.DatabaseHelper;
import ohos.data.orm.OrmContext;
import ohos.data.orm.OrmPredicates;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.Collection;
import java.util.List;

/**
 * Card Main Ability
 */
public class MainAbility extends Ability {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(0, 0, "com.huawei.cookbooks.MainAbility");
    private static final int DEFAULT_DIMENSION_4X4 = 4;
    private static final String EMPTY_STRING = "";
    private static final int INVALID_FORM_ID = 0;
    private static final String DATABASE_NAME = "FormDatabase.db";
    private static final String DATABASE_NAME_ALIAS = "FormDatabase";
    private final DatabaseHelper databaseHelper = new DatabaseHelper(this);
    private OrmContext connect;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        // 启动serviceAbility
        Intent intentService = new Intent();
        connect = databaseHelper.getOrmContext(DATABASE_NAME_ALIAS, DATABASE_NAME, FormDatabase.class);
        Operation operation =
                new Intent.OperationBuilder()
                        .withDeviceId("")
                        .withBundleName(getBundleName())
                        .withAbilityName(TimerAbility.class.getName())
                        .build();
        intentService.setOperation(operation);
        startAbility(intentService);
        super.setMainRoute(HostSlice.class.getName());
    }

    @Override
    protected ProviderFormInfo onCreateForm(Intent intent) {
        if (intent == null) {
            return new ProviderFormInfo();
        }
        // 获取卡片id
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
        int dimension = DEFAULT_DIMENSION_4X4;
        if (intent.hasParameter(AbilitySlice.PARAM_FORM_DIMENSION_KEY)) {
            dimension = intent.getIntParam(AbilitySlice.PARAM_FORM_DIMENSION_KEY, DEFAULT_DIMENSION_4X4);
        }
        int layoutId = ResourceTable.Layout_form_grid_pattern_widget_4_4;
        ProviderFormInfo formInfo = new ProviderFormInfo(layoutId, this);
        // 存储卡片信息
        Form form = new Form(formId, formName, dimension);
        if (connect == null) {
            connect = databaseHelper.getOrmContext(DATABASE_NAME_ALIAS, DATABASE_NAME, FormDatabase.class);
        }
        ComponentProvider componentProvider = ComponentProviderUtils.getComponentProvider(connect, layoutId, this);
        componentProvider.applyAction(componentProvider.getAllComponents());
        formInfo.mergeActions(componentProvider);
        try {
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
