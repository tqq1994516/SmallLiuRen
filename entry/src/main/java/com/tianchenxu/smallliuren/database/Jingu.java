package com.tianchenxu.smallliuren.database;

import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.Entity;
import ohos.data.orm.annotation.PrimaryKey;

@Entity(tableName = "jingu")
public class Jingu extends OrmObject {
    @PrimaryKey(autoGenerate = true)
    private Integer jinguId;
    private String jinguName;
    private int jinguNum;

    public int getJinguNum() {
        return jinguNum;
    }

    public void setJinguNum(int jinguNum) {
        this.jinguNum = jinguNum;
    }

    public Integer getJinguId() {
        return jinguId;
    }

    public void setJinguId(Integer jinguId) {
        this.jinguId = jinguId;
    }

    public String getJinguName() {
        return jinguName;
    }

    public void setJinguName(String jinguName) {
        this.jinguName = jinguName;
    }

    public Jingu() {
    }

    public Jingu(Integer jinguId, String jinguName, int jinguNum) {
        this.jinguId = jinguId;
        this.jinguName = jinguName;
        this.jinguNum = jinguNum;
    }
}
