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
import com.tianchenxu.smallliuren.slice.ClockCardSlice;
import ohos.aafwk.ability.*;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.ComponentProvider;
import ohos.data.DatabaseHelper;
import ohos.data.orm.OrmContext;
import ohos.data.orm.OrmObject;
import ohos.data.rdb.ValuesBucket;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import com.tianchenxu.smallliuren.utils.ComponentProviderUtils;
import com.tianchenxu.smallliuren.utils.DatabaseUtils;

/**
 * Card Main Ability
 */
public class MainAbility extends Ability {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(0, 0, "com.huawei.cookbooks.MainAbility");
    private static final int DEFAULT_DIMENSION_2X2 = 2;
    private static final int DEFAULT_DIMENSION_4X4 = 4;
    private static final String EMPTY_STRING = "";
    private static final int INVALID_FORM_ID = -1;
    private static final String DATABASE_NAME = "FormDatabase.db";
    private static final String DATABASE_NAME_ALIAS = "FormDatabase";
    private DatabaseHelper databaseHelper = new DatabaseHelper(this);
    private OrmContext connect;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        // 启动dataAbili
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
        initTianganDizhi();
        startAbility(intentService);
        super.setMainRoute(ClockCardSlice.class.getName());
    }

    private void initTianganDizhi() {
        if (connect == null) {
            connect = databaseHelper.getOrmContext(DATABASE_NAME_ALIAS, DATABASE_NAME, FormDatabase.class);
        }
        addTiangan("甲", 1, 1, connect);
        addTiangan("乙", 2, 2, connect);
        addTiangan("丙", 3, 1, connect);
        addTiangan("丁", 4, 2, connect);
        addTiangan("戊", 5, 1, connect);
        addTiangan("己", 6, 2, connect);
        addTiangan("庚", 7, 1, connect);
        addTiangan("辛", 8, 2, connect);
        addTiangan("壬", 9, 1, connect);
        addTiangan("癸", 10, 2, connect);
        addDizhi("子", 1, 1, connect);
        addDizhi("丑", 2, 2, connect);
        addDizhi("寅", 3, 1, connect);
        addDizhi("卯", 4, 2, connect);
        addDizhi("辰", 5, 1, connect);
        addDizhi("巳", 6, 2, connect);
        addDizhi("午", 7, 1, connect);
        addDizhi("未", 8, 2, connect);
        addDizhi("申", 9, 1, connect);
        addDizhi("酉", 10, 2, connect);
        addDizhi("戌", 11, 1, connect);
        addDizhi("亥", 12, 2, connect);
        addAssert(1, 1, "大安事事昌，求财在坤方，失物去不远，宅舍保安康；行人身未动，病者主无妨，将军回田野，仔细兴推祥。", connect);
        addAssert(1, 2, "大安加留连，办事不周全，失物西北去，婚姻晚几天。", connect);
        addAssert(1, 3, "大安加速喜，事事自己起，失物当日见，婚姻自己提。", connect);
        addAssert(1, 4, "大安加赤口，办事不顺手，失物不用找，婚姻两分手。", connect);
        addAssert(1, 5, "大安加小吉，事事从己及，失物不出门，婚姻成就地。", connect);
        addAssert(1, 6, "大安加空亡，病人要上床，失物无踪影，事事不顺情。", connect);
        addAssert(2, 1, "留连加大安，办事两分张，婚姻有喜事，先苦后来甜。", connect);
        addAssert(2, 2, "留连事难成，求谋月未明，凡事只宜缓，去者未回程；失物南方见，急讨方称心，更须防口舌，人口且太平。", connect);
        addAssert(2, 3, "留连加速喜，事事由自己，婚姻有成意，失物三天里。", connect);
        addAssert(2, 4, "留连加赤口，病者死人口，失物准丢失，婚姻两分手。", connect);
        addAssert(2, 5, "留连加小吉，事事不用提，失物东南去，病者出人齐。", connect);
        addAssert(2, 6, "留连加空亡，病人准死亡，失物不见面，婚姻两分张。", connect);
        addAssert(3, 1, "速喜加大安，事事都平安，姻姻成全了，占病都相安。", connect);
        addAssert(3, 2, "速喜加留连，婚姻不可言，失物无信息，病人有仙缘。", connect);
        addAssert(3, 3, "速喜喜来临，求财向南行，失物申未午，逢人路上寻；官事有福德，病者无祸侵，田宅六畜吉，行人有喜音。", connect);
        addAssert(3, 4, "速喜加赤口，自己往外走，失物往正北，婚姻得勤走。", connect);
        addAssert(3, 5, "速喜加小吉，婚姻有人提，病人当天好，时物在家里。", connect);
        addAssert(3, 6, "速喜加空亡，婚姻有分张，病者积极治，失物不久见。", connect);
        addAssert(4, 1, "赤口加大安，办事险和难，失物东北找，婚姻指定难。", connect);
        addAssert(4, 2, "赤口加留连，办事有困难，行人在外走，失物不回还。", connect);
        addAssert(4, 3, "赤口加速喜，婚姻在自己，失物有着落，办事官事起。", connect);
        addAssert(4, 4, "赤口主口舌，官非切要防，失物急去寻，行人有惊慌；六畜多惊怪，病者出西方，更须防诅咒，恐怕染瘟疫。", connect);
        addAssert(4, 5, "赤口加小吉，办事自己提，婚姻不能成，失物无信息。", connect);
        addAssert(4, 6, "赤口加空亡，无病也上床，失物不用找，婚姻不能成。", connect);
        addAssert(5, 1, "小吉加大安，事事两周全，婚姻当日定，失物自己损。", connect);
        addAssert(5, 2, "小吉加留连，事事有反还，婚姻有人破，失物上西南。", connect);
        addAssert(5, 3, "小吉加速喜，事事从头起，婚姻能成就，失物在院里。", connect);
        addAssert(5, 4, "小吉加赤口，办事往外走，婚姻有难处，失物丢了手。", connect);
        addAssert(5, 5, "小吉最吉昌，路上好商量，阴人来报喜，失物在坤方；人立便至，交易甚是强，凡是皆和合，病者辱上苍。", connect);
        addAssert(5, 6, "小吉加空亡，病人不妥当，失物正东找，婚姻再想想。", connect);
        addAssert(6, 1, "空亡加大安，事事不周全，婚姻从和好，失物反复间。", connect);
        addAssert(6, 2, "空亡加留连，办事处处难，婚姻重新定，失物永不还。", connect);
        addAssert(6, 3, "空亡加速喜，事事怨自己，婚姻有一定，失物在家里。", connect);
        addAssert(6, 4, "空亡加赤口，办事官非有，婚姻难定准，失物往远走。", connect);
        addAssert(6, 5, "空亡加小吉，事事有猜疑，婚姻有喜事，失物回家里。", connect);
        addAssert(6, 6, "空亡事不祥，阴人多乖张，求财无利益，行人有灾秧；失物寻不见，官事有刑伤，病人逢暗鬼，禳解保安康。\n", connect);
    }

    private void addTiangan(String tianganName, int tianganNum, int tianganYinyang, OrmContext connect) {
        Tiangan tiangan = new Tiangan();
        tiangan.setTianganName(tianganName);
        tiangan.setTianganNum(tianganNum);
        tiangan.setTianganYinyang(tianganYinyang);
        DatabaseUtils.insertTiangan(tiangan, connect);
    }

    private void addDizhi(String dizhiName, int dizhiNum, int dizhiYinyang, OrmContext connect) {
        Dizhi dizhi = new Dizhi();
        dizhi.setDizhiName(dizhiName);
        dizhi.setDizhiNum(dizhiNum);
        dizhi.setDizhiYinyang(dizhiYinyang);
        DatabaseUtils.insertDizhi(dizhi, connect);
    }

    private void addAssert(int dayNum, int timeNum, String assertText, OrmContext connect) {
        Assert anAssert = new Assert();
        anAssert.setDayNum(dayNum);
        anAssert.setTimeNum(timeNum);
        anAssert.setAssertText(assertText);
        DatabaseUtils.insertAssert(anAssert, connect);
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
        if (connect == null) {
            connect = databaseHelper.getOrmContext(DATABASE_NAME_ALIAS, DATABASE_NAME, FormDatabase.class);
        }
        DatabaseUtils.insertForm(form, connect);
        ComponentProvider componentProvider = ComponentProviderUtils.getComponentProvider(form, this, 1, connect);
        try {
            updateForm(formId, componentProvider);
        } catch (FormException e) {
            DatabaseUtils.deleteFormData(formId, connect);
            HiLog.error(LABEL_LOG, "onUpdateForm updateForm error");
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
