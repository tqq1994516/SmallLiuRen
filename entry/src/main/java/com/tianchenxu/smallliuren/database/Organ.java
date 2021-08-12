package com.tianchenxu.smallliuren.database;

import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.Entity;
import ohos.data.orm.annotation.PrimaryKey;

@Entity(tableName = "organ")
public class Organ extends OrmObject {
    @PrimaryKey(autoGenerate = true)
    private Integer organId;
    private String organName;

    public Integer getOrganId() {
        return organId;
    }

    public void setOrganId(Integer organId) {
        this.organId = organId;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public Organ() {
    }

    public Organ(Integer organId, String organName) {
        this.organId = organId;
        this.organName = organName;
    }
}
