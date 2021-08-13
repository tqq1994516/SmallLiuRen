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

import com.tianchenxu.smallliuren.database.*;
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
    public static void updateAssert(Integer assertId, Assert anAssert, OrmContext connect) {
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

    /**
     * add attribute info
     *
     * @param attribute object
     * @param connect data connection
     */
    public static void insertAttribute(Attribute attribute, OrmContext connect) {
        OrmPredicates where = connect.where(Attribute.class);
        where.equalTo("jingu", attribute.getJingu());
        List<Attribute> query = connect.query(where);
        if (!query.isEmpty()) {
            Attribute result = query.get(0);
            updateAttribute(result.getAttributeId(), attribute, connect);
        } else {
            connect.insert(attribute);
            connect.flush();
        }
    }

    /**
     * update attribute info
     *
     * @param attributeId attribute id
     * @param attribute object
     * @param connect data connection
     */
    public static void updateAttribute(Integer attributeId, Attribute attribute, OrmContext connect) {
        attribute.setAttributeId(attributeId);
        connect.update(attribute);
        connect.flush();
    }

    /**
     * query attribute object
     *
     * @param attributeId
     * @param connect
     * @return
     */
    public static Attribute queryAttributeById(Integer attributeId, OrmContext connect) {
        OrmPredicates where = connect.where(Attribute.class);
        where.equalTo("attributeId", attributeId);
        List<Attribute> query = connect.query(where);
        if (!query.isEmpty()) {
            return query.get(0);
        }
        return null;
    }

    /**
     * query attribute object
     *
     * @param jingu
     * @param connect
     * @return
     */
    public static Attribute queryAttributeByInt(int jingu, OrmContext connect) {
        OrmPredicates where = connect.where(Attribute.class);
        where.equalTo("jingu", jingu);
        List<Attribute> query = connect.query(where);
        if (!query.isEmpty()) {
            return query.get(0);
        }
        return null;
    }

    /**
     * add deity info
     *
     * @param deity object
     * @param connect data connection
     */
    public static void insertDeity(Deity deity, OrmContext connect) {
        OrmPredicates where = connect.where(Deity.class);
        where.equalTo("deityName", deity.getDeityName());
        List<Deity> query = connect.query(where);
        if (!query.isEmpty()) {
            Deity result = query.get(0);
            updateDeity(result.getDeityId(), deity, connect);
        } else {
            connect.insert(deity);
            connect.flush();
        }
    }

    /**
     * update deity info
     *
     * @param deityId deity id
     * @param deity object
     * @param connect data connection
     */
    public static void updateDeity(Integer deityId, Deity deity, OrmContext connect) {
        deity.setDeityId(deityId);
        connect.update(deity);
        connect.flush();
    }

    /**
     * query deity object
     *
     * @param deityName
     * @param connect
     * @return
     */
    public static Deity queryDeityByName(String deityName, OrmContext connect) {
        OrmPredicates where = connect.where(Deity.class);
        where.equalTo("deityName", deityName);
        List<Deity> query = connect.query(where);
        if (!query.isEmpty()) {
            return query.get(0);
        }
        return null;
    }

    /**
     * query deity object
     *
     * @param deityId
     * @param connect
     * @return
     */
    public static Deity queryDeityById(Integer deityId, OrmContext connect) {
        OrmPredicates where = connect.where(Deity.class);
        where.equalTo("deityId", deityId);
        List<Deity> query = connect.query(where);
        if (!query.isEmpty()) {
            return query.get(0);
        }
        return null;
    }

    /**
     * add fiveElements info
     *
     * @param fiveElements object
     * @param connect data connection
     */
    public static void insertFiveElements(FiveElements fiveElements, OrmContext connect) {
        OrmPredicates where = connect.where(FiveElements.class);
        where.equalTo("fiveElementsName", fiveElements.getFiveElementsName());
        List<FiveElements> query = connect.query(where);
        if (!query.isEmpty()) {
            FiveElements result = query.get(0);
            updateFiveElements(result.getFiveElementsId(), fiveElements, connect);
        } else {
            connect.insert(fiveElements);
            connect.flush();
        }
    }

    /**
     * update fiveElements info
     *
     * @param fiveElementsId deity id
     * @param fiveElements object
     * @param connect data connection
     */
    public static void updateFiveElements(Integer fiveElementsId, FiveElements fiveElements, OrmContext connect) {
        fiveElements.setFiveElementsId(fiveElementsId);
        connect.update(fiveElements);
        connect.flush();
    }

    /**
     * query fiveElements object
     *
     * @param fiveElementsName
     * @param connect
     * @return
     */
    public static FiveElements queryFiveElementsByName(String fiveElementsName, OrmContext connect) {
        OrmPredicates where = connect.where(FiveElements.class);
        where.equalTo("fiveElementsName", fiveElementsName);
        List<FiveElements> query = connect.query(where);
        if (!query.isEmpty()) {
            return query.get(0);
        }
        return null;
    }

    /**
     * query fiveElements object
     *
     * @param fiveElementsId
     * @param connect
     * @return
     */
    public static FiveElements queryFiveElementsById(Integer fiveElementsId, OrmContext connect) {
        OrmPredicates where = connect.where(FiveElements.class);
        where.equalTo("fiveElementsId", fiveElementsId);
        List<FiveElements> query = connect.query(where);
        if (!query.isEmpty()) {
            return query.get(0);
        }
        return null;
    }

    /**
     * add organ info
     *
     * @param organ object
     * @param connect data connection
     */
    public static void insertOrgan(Organ organ, OrmContext connect) {
        OrmPredicates where = connect.where(Organ.class);
        where.equalTo("organName", organ.getOrganName());
        List<Organ> query = connect.query(where);
        if (!query.isEmpty()) {
            Organ result = query.get(0);
            updateOrgan(result.getOrganId(), organ, connect);
        } else {
            connect.insert(organ);
            connect.flush();
        }
    }

    /**
     * update organ info
     *
     * @param organId organ id
     * @param organ object
     * @param connect data connection
     */
    public static void updateOrgan(Integer organId, Organ organ, OrmContext connect) {
        organ.setOrganId(organId);
        connect.update(organ);
        connect.flush();
    }

    /**
     * query organ object
     *
     * @param organName
     * @param connect
     * @return
     */
    public static Organ queryOrganByName(String organName, OrmContext connect) {
        OrmPredicates where = connect.where(Organ.class);
        where.equalTo("organName", organName);
        List<Organ> query = connect.query(where);
        if (!query.isEmpty()) {
            return query.get(0);
        }
        return null;
    }

    /**
     * query organ object
     *
     * @param organId
     * @param connect
     * @return
     */
    public static Organ queryOrganById(Integer organId, OrmContext connect) {
        OrmPredicates where = connect.where(Organ.class);
        where.equalTo("organId", organId);
        List<Organ> query = connect.query(where);
        if (!query.isEmpty()) {
            return query.get(0);
        }
        return null;
    }

    /**
     * add orientation info
     *
     * @param orientation object
     * @param connect data connection
     */
    public static void insertOrientation(Orientation orientation, OrmContext connect) {
        OrmPredicates where = connect.where(Orientation.class);
        where.equalTo("orientationName", orientation.getOrientationName());
        List<Orientation> query = connect.query(where);
        if (!query.isEmpty()) {
            Orientation result = query.get(0);
            updateOrientation(result.getOrientationId(), orientation, connect);
        } else {
            connect.insert(orientation);
            connect.flush();
        }
    }

    /**
     * update orientation info
     *
     * @param orientationId organ id
     * @param orientation object
     * @param connect data connection
     */
    public static void updateOrientation(Integer orientationId, Orientation orientation, OrmContext connect) {
        orientation.setOrientationId(orientationId);
        connect.update(orientation);
        connect.flush();
    }

    /**
     * query organ object
     *
     * @param orientationName
     * @param connect
     * @return
     */
    public static Orientation queryOrientationByName(String orientationName, OrmContext connect) {
        OrmPredicates where = connect.where(Orientation.class);
        where.equalTo("orientationName", orientationName);
        List<Orientation> query = connect.query(where);
        if (!query.isEmpty()) {
            return query.get(0);
        }
        return null;
    }

    /**
     * query orientation object
     *
     * @param orientationId
     * @param connect
     * @return
     */
    public static Orientation queryOrientationById(Integer orientationId, OrmContext connect) {
        OrmPredicates where = connect.where(Orientation.class);
        where.equalTo("orientationId", orientationId);
        List<Orientation> query = connect.query(where);
        if (!query.isEmpty()) {
            return query.get(0);
        }
        return null;
    }

    /**
     * add jingu info
     *
     * @param jingu object
     * @param connect data connection
     */
    public static void insertJingu(Jingu jingu, OrmContext connect) {
        OrmPredicates where = connect.where(Jingu.class);
        where.equalTo("jinguName", jingu.getJinguName());
        List<Jingu> query = connect.query(where);
        if (!query.isEmpty()) {
            Jingu result = query.get(0);
            updateJingu(result.getJinguId(), jingu, connect);
        } else {
            connect.insert(jingu);
            connect.flush();
        }
    }

    /**
     * update jingu info
     *
     * @param jinguId jingu id
     * @param jingu object
     * @param connect data connection
     */
    public static void updateJingu(Integer jinguId, Jingu jingu, OrmContext connect) {
        jingu.setJinguId(jinguId);
        connect.update(jingu);
        connect.flush();
    }

    /**
     * query jingu object
     *
     * @param jinguName
     * @param connect
     * @return
     */
    public static Jingu queryJinguByName(String jinguName, OrmContext connect) {
        OrmPredicates where = connect.where(Jingu.class);
        where.equalTo("jinguName", jinguName);
        List<Jingu> query = connect.query(where);
        if (!query.isEmpty()) {
            return query.get(0);
        }
        return null;
    }

    /**
     * query jingu object
     *
     * @param jinguNum
     * @param connect
     * @return
     */
    public static Jingu queryJinguByNum(int jinguNum, OrmContext connect) {
        OrmPredicates where = connect.where(Jingu.class);
        where.equalTo("jinguNum", jinguNum);
        List<Jingu> query = connect.query(where);
        if (!query.isEmpty()) {
            return query.get(0);
        }
        return null;
    }
}
