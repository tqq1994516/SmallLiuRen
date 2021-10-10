package com.tianchenxu.smallliuren.database;

import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.Entity;
import ohos.data.orm.annotation.PrimaryKey;

@Entity(tableName = "bodyUse")
public class BodyUse extends OrmObject {
    @PrimaryKey(autoGenerate = true)
    private Integer bodyUseId;
    private String relationship;
    private String body_useDetail;

    public BodyUse() {
    }

    public BodyUse(Integer bodyUseId, String relationship, String body_useDetail) {
        this.bodyUseId = bodyUseId;
        this.relationship = relationship;
        this.body_useDetail = body_useDetail;
    }

    public Integer getBodyUseId() {
        return bodyUseId;
    }

    public void setBodyUseId(Integer bodyUseId) {
        this.bodyUseId = bodyUseId;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getBody_useDetail() {
        return body_useDetail;
    }

    public void setBody_useDetail(String body_useDetail) {
        this.body_useDetail = body_useDetail;
    }
}
