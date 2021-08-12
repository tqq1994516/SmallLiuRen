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

import com.tianchenxu.smallliuren.database.Assert;
import com.tianchenxu.smallliuren.database.Dizhi;
import com.tianchenxu.smallliuren.database.Form;
import com.tianchenxu.smallliuren.database.Tiangan;
import ohos.data.orm.OrmContext;
import ohos.data.orm.OrmPredicates;

import java.util.List;

/**
 * Card Database Operations
 */
public class DatabaseUtils {
    /**
     * delete data
     *
     * @param formId form id
     * @param connect data connection
     */
    public static void deleteFormData(long formId, OrmContext connect) {
        OrmPredicates where = connect.where(Form.class);
        where.equalTo("formId", formId);
        List<Form> query = connect.query(where);
        if (!query.isEmpty()) {
            connect.delete(query.get(0));
            connect.flush();
        }
    }

    /**
     * add card info
     *
     * @param form card object
     * @param connect data connection
     */
    public static void insertForm(Form form, OrmContext connect) {
        connect.insert(form);
        connect.flush();
    }

    /**
     * add tiangan info
     *
     * @param tiangan object
     * @param connect data connection
     */
    public static void insertTiangan(Tiangan tiangan, OrmContext connect) {
        OrmPredicates where = connect.where(Tiangan.class);
        where.equalTo("tianganName", tiangan.getTianganName());
        List<Tiangan> query = connect.query(where);
        if (!query.isEmpty()) {
            Tiangan result = query.get(0);
            updateTiangan(result.getTianganId(), tiangan, connect);
        } else {
            connect.insert(tiangan);
            connect.flush();
        }
    }

    /**
     * update tiangan info
     *
     * @param tianganId tiangan id
     * @param tiangan object
     * @param connect data connection
     */
    public static void updateTiangan(Long tianganId, Tiangan tiangan, OrmContext connect) {
        tiangan.setTianganId(tianganId);
        connect.update(tiangan);
        connect.flush();
    }

    /**
     * query tiangan object
     *
     * @param tianganName
     * @param connect
     * @return
     */
    public static Tiangan queryTianganByName(String tianganName, OrmContext connect) {
        OrmPredicates where = connect.where(Tiangan.class);
        where.equalTo("tianganName", tianganName);
        List<Tiangan> query = connect.query(where);
        if (!query.isEmpty()) {
            return query.get(0);
        }
        return null;
    }

    /**
     * add dizhi info
     *
     * @param dizhi object
     * @param connect data connection
     */
    public static void insertDizhi(Dizhi dizhi, OrmContext connect) {
        OrmPredicates where = connect.where(Dizhi.class);
        where.equalTo("dizhiName", dizhi.getDizhiName());
        List<Dizhi> query = connect.query(where);
        if (!query.isEmpty()) {
            Dizhi result = query.get(0);
            updateDizhi(result.getDizhiId(), dizhi, connect);
        } else {
            connect.insert(dizhi);
            connect.flush();
        }
    }
    /**
     * update tiangan info
     *
     * @param dizhiId dizhi id
     * @param dizhi object
     * @param connect data connection
     */
    public static void updateDizhi(Long dizhiId, Dizhi dizhi, OrmContext connect) {
        dizhi.setDizhiId(dizhiId);
        connect.update(dizhi);
        connect.flush();
    }

    /**
     * query dizhi object
     *
     * @param dizhiName
     * @param connect
     * @return
     */
    public static Dizhi queryDizhiByName(String dizhiName, OrmContext connect) {
        OrmPredicates where = connect.where(Dizhi.class);
        where.equalTo("dizhiName", dizhiName);
        List<Dizhi> query = connect.query(where);
        if (!query.isEmpty()) {
            return query.get(0);
        }
        return null;
    }

    /**
     * add assert info
     *
     * @param anAssert object
     * @param connect data connection
     */
    public static void insertAssert(Assert anAssert, OrmContext connect) {
        OrmPredicates where = connect.where(Assert.class);
        where.equalTo("dayNum", anAssert.getDayNum());
        where.equalTo("timeNum", anAssert.getTimeNum());
        List<Assert> query = connect.query(where);
        if (!query.isEmpty()) {
            Assert result = query.get(0);
            updateAssert(result.getAssertId(), anAssert, connect);
        } else {
            connect.insert(anAssert);
            connect.flush();
        }
    }

    /**
     * update assert info
     *
     * @param assertId dizhi id
     * @param anAssert object
     * @param connect data connection
     */
    public static void updateAssert(Long assertId, Assert anAssert, OrmContext connect) {
        anAssert.setAssertId(assertId);
        connect.update(anAssert);
        connect.flush();
    }

    /**
     * query assert object
     *
     * @param dayNum
     * @param timeNum
     * @param connect
     * @return
     */
    public static Assert queryAssertByNums(int dayNum, int timeNum, OrmContext connect) {
        OrmPredicates where = connect.where(Assert.class);
        where.equalTo("dayNum", dayNum);
        where.equalTo("timeNum", timeNum);
        List<Assert> query = connect.query(where);
        if (!query.isEmpty()) {
            return query.get(0);
        }
        return null;
    }
}
