package com.tianchenxu.smallliuren.database;


import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.Entity;
import ohos.data.orm.annotation.Index;
import ohos.data.orm.annotation.PrimaryKey;

@Entity(tableName = "tiangan", indices = {@Index(value = {"tianganName"}, name = "tianganName_index", unique = true)})
public class Tiangan extends OrmObject {
    @PrimaryKey()
    private Long tianganId;
    private String tianganName;
    private int tianganNum;
    private int tianganYinyang;

    public Long getTianganId() {
        return tianganId;
    }

    public void setTianganId(Long tianganId) {
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

    public int getTianganYinyang() {
        return tianganYinyang;
    }

    public void setTianganYinyang(int tianganYinyang) {
        this.tianganYinyang = tianganYinyang;
    }

    public Tiangan() {
    }

    public Tiangan(Long tianganId, String tianganName, int tianganNum, int tianganYinyang) {
        this.tianganId = tianganId;
        this.tianganName = tianganName;
        this.tianganNum = tianganNum;
        this.tianganYinyang = tianganYinyang;
    }
}
