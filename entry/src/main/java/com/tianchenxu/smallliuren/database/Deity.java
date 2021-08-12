package com.tianchenxu.smallliuren.database;

import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.Entity;
import ohos.data.orm.annotation.PrimaryKey;

@Entity(tableName = "deity")
public class Deity extends OrmObject {
    @PrimaryKey(autoGenerate = true)
    private Integer deityId;
    private String deityName;

    public Integer getDeityId() {
        return deityId;
    }

    public void setDeityId(Integer deityId) {
        this.deityId = deityId;
    }

    public String getDeityName() {
        return deityName;
    }

    public void setDeityName(String deityName) {
        this.deityName = deityName;
    }

    public Deity() {
    }

    public Deity(Integer deityId, String deityName) {
        this.deityId = deityId;
        this.deityName = deityName;
    }
}
