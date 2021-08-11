package com.tianchenxu.smallliuren.database;


import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.Entity;
import ohos.data.orm.annotation.Index;
import ohos.data.orm.annotation.PrimaryKey;

@Entity(tableName = "tiangan", indices = {@Index(value = {"tianganName"}, name = "tianganName_index", unique = true)})
public class Tiangan extends OrmObject {
    @PrimaryKey(autoGenerate = true)
    private Integer tianganId;
    private String tianganName;
    private Integer tianganNum;
    private Integer tianganYinyang;

    public Integer getTianganId() {
        return tianganId;
    }

    public void setTianganId(Integer tianganId) {
        this.tianganId = tianganId;
    }

    public String getTianganName() {
        return tianganName;
    }

    public void setTianganName(String tianganName) {
        this.tianganName = tianganName;
    }

    public Integer getTianganNum() {
        return tianganNum;
    }

    public void setTianganNum(Integer tianganNum) {
        this.tianganNum = tianganNum;
    }

    public Integer getTianganYinyang() {
        return tianganYinyang;
    }

    public void setTianganYinyang(Integer tianganYinyang) {
        this.tianganYinyang = tianganYinyang;
    }

    public Tiangan() {
    }

    public Tiangan(Integer tianganId, String tianganName, Integer tianganNum, Integer tianganYinyang) {
        this.tianganId = tianganId;
        this.tianganName = tianganName;
        this.tianganNum = tianganNum;
        this.tianganYinyang = tianganYinyang;
    }
}
