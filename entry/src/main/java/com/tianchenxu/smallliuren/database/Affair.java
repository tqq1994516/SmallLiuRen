package com.tianchenxu.smallliuren.database;

import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.Entity;
import ohos.data.orm.annotation.PrimaryKey;

@Entity(tableName = "affair")
public class Affair extends OrmObject {
    @PrimaryKey(autoGenerate = true)
    private Integer affairId;
    private String affairName;

    public Affair() {
    }

    public Integer getAffairId() {
        return affairId;
    }

    public void setAffairId(Integer affairId) {
        this.affairId = affairId;
    }

    public String getAffairName() {
        return affairName;
    }

    public void setAffairName(String affairName) {
        this.affairName = affairName;
    }

    public Affair(Integer affairId, String affairName) {
        this.affairId = affairId;
        this.affairName = affairName;
    }
}
