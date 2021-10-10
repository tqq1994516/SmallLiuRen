package com.tianchenxu.smallliuren.database;

import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.Entity;
import ohos.data.orm.annotation.PrimaryKey;

@Entity(tableName = "realms")
public class Realms extends OrmObject {
    @PrimaryKey(autoGenerate = true)
    private Integer realmsId;
    private String realmsName;

    public Realms(Integer realmsId, String realmsName) {
        this.realmsId = realmsId;
        this.realmsName = realmsName;
    }

    public Integer getRealmsId() {
        return realmsId;
    }

    public void setRealmsId(Integer realmsId) {
        this.realmsId = realmsId;
    }

    public String getRealmsName() {
        return realmsName;
    }

    public void setRealmsName(String realmsName) {
        this.realmsName = realmsName;
    }

    public Realms() {
    }
}
