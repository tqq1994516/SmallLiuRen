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

import java.sql.Time;
import java.util.List;

/**
 * Card Database Operations
 */
public class DatabaseUtils {
    /**
     * delete data
     *
     * @param formId  form id
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
     * @param form    card object
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
     * @param tiangan   object
     * @param connect   data connection
     */
    public static void updateTiangan(Integer tianganId, Tiangan tiangan, OrmContext connect) {
        tiangan.setTianganId(tianganId);
        connect.update(tiangan);
        connect.flush();
    }

    /**
     * query tiangan object
     *
     * @param tianganName tiangan name
     * @param connect     OrmContext
     * @return Tiangan object
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
     * query tiangan object
     *
     * @param tianganId tiangan Id
     * @param connect   OrmContext
     * @return Tiangan object
     */
    public static Tiangan queryTianganById(Integer tianganId, OrmContext connect) {
        OrmPredicates where = connect.where(Tiangan.class);
        where.equalTo("tianganId", tianganId);
        List<Tiangan> query = connect.query(where);
        if (!query.isEmpty()) {
            return query.get(0);
        }
        return null;
    }

    /**
     * add dizhi info
     *
     * @param dizhi   object
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
     * @param dizhi   object
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
     * @param dizhiName dizhi name
     * @param connect   OrmContext
     * @return Dizhi object
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
     * @param connect  data connection
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
     * @param connect  data connection
     */
    public static void updateAssert(Integer assertId, Assert anAssert, OrmContext connect) {
        anAssert.setAssertId(assertId);
        connect.update(anAssert);
        connect.flush();
    }

    /**
     * query assert object
     *
     * @param dayNum  day number
     * @param timeNum time number
     * @param connect OrmContext
     * @return Assert object
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
     * @param connect   data connection
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
     * @param attribute   object
     * @param connect     data connection
     */
    public static void updateAttribute(Integer attributeId, Attribute attribute, OrmContext connect) {
        attribute.setAttributeId(attributeId);
        connect.update(attribute);
        connect.flush();
    }

    /**
     * query attribute object
     *
     * @param attributeId attribute id
     * @param connect     OrmContext
     * @return Attribute object
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
     * @param jinguNum jingu number
     * @param connect  OrmContext
     * @return Attribute object
     */
    public static Attribute queryAttributeByNum(int jinguNum, OrmContext connect) {
        Jingu jingu = DatabaseUtils.queryJinguByNum(jinguNum, connect);
        OrmPredicates where = connect.where(Attribute.class);
        where.equalTo("jingu", jingu.getJinguId());
        List<Attribute> query = connect.query(where);
        if (!query.isEmpty()) {
            return query.get(0);
        }
        return null;
    }

    /**
     * add deity info
     *
     * @param deity   object
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
     * @param deity   object
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
     * @param deityName deity name
     * @param connect   OrmContext
     * @return Deity object
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
     * @param deityId deity id
     * @param connect OrmContext
     * @return Deity object
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
     * @param connect      data connection
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
     * @param fiveElements   object
     * @param connect        data connection
     */
    public static void updateFiveElements(Integer fiveElementsId, FiveElements fiveElements, OrmContext connect) {
        fiveElements.setFiveElementsId(fiveElementsId);
        connect.update(fiveElements);
        connect.flush();
    }

    /**
     * query fiveElements object
     *
     * @param fiveElementsName fiveElements name
     * @param connect          OrmContext
     * @return FiveElements object
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
     * @param fiveElementsId fiveElements id
     * @param connect        OrmContext
     * @return FiveElements object
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
     * @param organ   object
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
     * @param organ   object
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
     * @param organName organ name
     * @param connect   OrmContext
     * @return Organ object
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
     * @param organId organ id
     * @param connect OrmContext
     * @return Organ object
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
     * @param connect     data connection
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
     * @param orientation   object
     * @param connect       data connection
     */
    public static void updateOrientation(Integer orientationId, Orientation orientation, OrmContext connect) {
        orientation.setOrientationId(orientationId);
        connect.update(orientation);
        connect.flush();
    }

    /**
     * query orientation object
     *
     * @param orientationName orientation name
     * @param connect         OrmContext
     * @return Orientation object
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
     * @param orientationId orientation id
     * @param connect       OrmContext
     * @return Orientation object
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
     * @param jingu   object
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
     * @param jingu   object
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
     * @param jinguName jingu name
     * @param connect   OrmContext
     * @return Jingu object
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
     * @param jinguNum jingu number
     * @param connect  OrmContext
     * @return Jingu object
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

    /**
     * query jingu object
     *
     * @param jinguId jingu Id
     * @param connect  OrmContext
     * @return Jingu object
     */
    public static Jingu queryJinguById(Integer jinguId, OrmContext connect) {
        OrmPredicates where = connect.where(Jingu.class);
        where.equalTo("jinguId", jinguId);
        List<Jingu> query = connect.query(where);
        if (!query.isEmpty()) {
            return query.get(0);
        }
        return null;
    }

    /**
     * add oldLunarHour info
     *
     * @param oldLunarHour object
     * @param connect      data connection
     */
    public static void insertOldLunarHour(OldLunarHour oldLunarHour, OrmContext connect) {
        List<OldLunarHour> oldLunarHours = DatabaseUtils.queryOldLunarHour(connect);
        if (!oldLunarHours.isEmpty()) {
            OldLunarHour result = oldLunarHours.get(0);
            updateOldLunarHour(result.getOldLunarHourId(), oldLunarHour, connect);
        } else {
            connect.insert(oldLunarHour);
            connect.flush();
        }
    }

    /**
     * update oldLunarHour info
     *
     * @param OldLunarHourId oldLunarHour id
     * @param oldLunarHour   object
     * @param connect        data connection
     */
    public static void updateOldLunarHour(Integer OldLunarHourId, OldLunarHour oldLunarHour, OrmContext connect) {
        oldLunarHour.setOldLunarHourId(OldLunarHourId);
        connect.update(oldLunarHour);
        connect.flush();
    }

    /**
     * query oldLunarHour object
     *
     * @param connect OrmContext
     * @return OldLunarHour object
     */
    public static List<OldLunarHour> queryOldLunarHour(OrmContext connect) {
        OrmPredicates where = connect.where(OldLunarHour.class);
        List<OldLunarHour> query = connect.query(where);
        return query;
    }

    /**
     * query oldLunarHour object
     *
     * @param oldLunarHourId oldLunarHour id
     * @param connect        OrmContext
     * @return OldLunarHour object
     */
    public static OldLunarHour queryOldLunarHourById(Integer oldLunarHourId, OrmContext connect) {
        OrmPredicates where = connect.where(OldLunarHour.class);
        where.equalTo("oldLunarHourId", oldLunarHourId);
        List<OldLunarHour> query = connect.query(where);
        if (!query.isEmpty()) {
            return query.get(0);
        }
        return null;
    }

    /**
     * add relation info
     *
     * @param relation object
     * @param connect  data connection
     */
    public static void insertRelation(Relation relation, OrmContext connect) {
        OrmPredicates where = connect.where(Relation.class);
        where.equalTo("relationName", relation.getRelationName());
        List<Relation> query = connect.query(where);
        if (!query.isEmpty()) {
            Relation result = query.get(0);
            updateRelation(result.getRelationId(), relation, connect);
        } else {
            connect.insert(relation);
            connect.flush();
        }
    }

    /**
     * update relation info
     *
     * @param relationId relation id
     * @param relation   object
     * @param connect    data connection
     */
    public static void updateRelation(Integer relationId, Relation relation, OrmContext connect) {
        relation.setRelationId(relationId);
        connect.update(relation);
        connect.flush();
    }

    /**
     * query relation object
     *
     * @param relationName relation name
     * @param connect      OrmContext
     * @return Relation object
     */
    public static Relation queryRelationByName(String relationName, OrmContext connect) {
        OrmPredicates where = connect.where(Relation.class);
        where.equalTo("relationName", relationName);
        List<Relation> query = connect.query(where);
        if (!query.isEmpty()) {
            return query.get(0);
        }
        return null;
    }

    /**
     * add affair info
     *
     * @param affair  object
     * @param connect data connection
     */
    public static void insertAffair(Affair affair, OrmContext connect) {
        OrmPredicates where = connect.where(Affair.class);
        where.equalTo("affairName", affair.getAffairName());
        List<Affair> query = connect.query(where);
        if (!query.isEmpty()) {
            Affair result = query.get(0);
            updateAffair(result.getAffairId(), affair, connect);
        } else {
            connect.insert(affair);
            connect.flush();
        }
    }

    /**
     * update affair info
     *
     * @param affairId affair id
     * @param affair   object
     * @param connect  data connection
     */
    public static void updateAffair(Integer affairId, Affair affair, OrmContext connect) {
        affair.setAffairId(affairId);
        connect.update(affair);
        connect.flush();
    }

    /**
     * query affair object
     *
     * @param affairName affair name
     * @param connect    OrmContext
     * @return Affair object
     */
    public static Affair queryAffairByName(String affairName, OrmContext connect) {
        OrmPredicates where = connect.where(Affair.class);
        where.equalTo("affairName", affairName);
        List<Affair> query = connect.query(where);
        if (!query.isEmpty()) {
            return query.get(0);
        }
        return null;
    }

    /**
     * query affair object
     *
     * @param affairId affair Id
     * @param connect  OrmContext
     * @return Affair object
     */
    public static Affair queryAffairById(Integer affairId, OrmContext connect) {
        OrmPredicates where = connect.where(Affair.class);
        where.equalTo("affairId", affairId);
        List<Affair> query = connect.query(where);
        if (!query.isEmpty()) {
            return query.get(0);
        }
        return null;
    }

    /**
     * add realms info
     *
     * @param realms  object
     * @param connect data connection
     */
    public static void insertRealms(Realms realms, OrmContext connect) {
        OrmPredicates where = connect.where(Realms.class);
        where.equalTo("realmsName", realms.getRealmsName());
        List<Realms> query = connect.query(where);
        if (!query.isEmpty()) {
            Realms result = query.get(0);
            updateRealms(result.getRealmsId(), realms, connect);
        } else {
            connect.insert(realms);
            connect.flush();
        }
    }

    /**
     * update realms info
     *
     * @param realmsId realms id
     * @param realms   object
     * @param connect  data connection
     */
    public static void updateRealms(Integer realmsId, Realms realms, OrmContext connect) {
        realms.setRealmsId(realmsId);
        connect.update(realms);
        connect.flush();
    }

    /**
     * query realms object
     *
     * @param realmsName realms name
     * @param connect    OrmContext
     * @return Realms object
     */
    public static Realms queryRealmsByName(String realmsName, OrmContext connect) {
        OrmPredicates where = connect.where(Realms.class);
        where.equalTo("realmsName", realmsName);
        List<Realms> query = connect.query(where);
        if (!query.isEmpty()) {
            return query.get(0);
        }
        return null;
    }

    /**
     * query realms object
     *
     * @param realmsId realms Id
     * @param connect  OrmContext
     * @return Realms object
     */
    public static Realms queryRealmsById(Integer realmsId, OrmContext connect) {
        OrmPredicates where = connect.where(Realms.class);
        where.equalTo("realmsId", realmsId);
        List<Realms> query = connect.query(where);
        if (!query.isEmpty()) {
            return query.get(0);
        }
        return null;
    }

    /**
     * add star info
     *
     * @param star    object
     * @param connect data connection
     */
    public static void insertStar(Star star, OrmContext connect) {
        OrmPredicates where = connect.where(Star.class);
        where.equalTo("starName", star.getStarName());
        List<Star> query = connect.query(where);
        if (!query.isEmpty()) {
            Star result = query.get(0);
            updateStar(result.getStarId(), star, connect);
        } else {
            connect.insert(star);
            connect.flush();
        }
    }

    /**
     * update star info
     *
     * @param starId  star id
     * @param star    object
     * @param connect data connection
     */
    public static void updateStar(Integer starId, Star star, OrmContext connect) {
        star.setStarId(starId);
        connect.update(star);
        connect.flush();
    }

    /**
     * query star object
     *
     * @param starName star name
     * @param connect  OrmContext
     * @return Realms object
     */
    public static Star queryStarByName(String starName, OrmContext connect) {
        OrmPredicates where = connect.where(Star.class);
        where.equalTo("starName", starName);
        List<Star> query = connect.query(where);
        if (!query.isEmpty()) {
            return query.get(0);
        }
        return null;
    }

    /**
     * query star object
     *
     * @param starId  star Id
     * @param connect OrmContext
     * @return Realms object
     */
    public static Star queryStarById(Integer starId, OrmContext connect) {
        OrmPredicates where = connect.where(Star.class);
        where.equalTo("starId", starId);
        List<Star> query = connect.query(where);
        if (!query.isEmpty()) {
            return query.get(0);
        }
        return null;
    }

    /**
     * add bodyUse info
     *
     * @param bodyUse object
     * @param connect data connection
     */
    public static void insertBodyUse(BodyUse bodyUse, OrmContext connect) {
        OrmPredicates where = connect.where(BodyUse.class);
        where.equalTo("relationship", bodyUse.getRelationship());
        List<BodyUse> query = connect.query(where);
        if (!query.isEmpty()) {
            BodyUse result = query.get(0);
            updateBodyUse(result.getBodyUseId(), bodyUse, connect);
        } else {
            connect.insert(bodyUse);
            connect.flush();
        }
    }

    /**
     * update bodyUse info
     *
     * @param bodyUseId bodyUse id
     * @param bodyUse   object
     * @param connect   data connection
     */
    public static void updateBodyUse(Integer bodyUseId, BodyUse bodyUse, OrmContext connect) {
        bodyUse.setBodyUseId(bodyUseId);
        connect.update(bodyUse);
        connect.flush();
    }

    /**
     * query bodyUse object
     *
     * @param relationship body use relationship
     * @param connect      OrmContext
     * @return BodyUse object
     */
    public static BodyUse queryBodyUseByText(String relationship, OrmContext connect) {
        OrmPredicates where = connect.where(BodyUse.class);
        where.equalTo("relationship", relationship);
        List<BodyUse> query = connect.query(where);
        if (!query.isEmpty()) {
            return query.get(0);
        }
        return null;
    }
}
