package com.tianchenxu.smallliuren.database;

import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.Entity;
import ohos.data.orm.annotation.Index;
import ohos.data.orm.annotation.PrimaryKey;

@Entity(tableName = "dizhi", indices = {@Index(value = {"dizhiName"}, name = "dizhiName_index", unique = true)})
public class Dizhi extends OrmObject {
    @PrimaryKey(autoGenerate = true)
    private Integer dizhiId;
    private String dizhiName;
    private Integer dizhiNum;
    private Integer dizhiYinyang;

    public Integer getDizhiId() {
        return dizhiId;
    }

    public void setDizhiId(Integer dizhiId) {
        this.dizhiId = dizhiId;
    }

    public String getDizhiName() {
        return dizhiName;
    }

    public void setDizhiName(String dizhiName) {
        this.dizhiName = dizhiName;
    }

    public Integer getDizhiNum() {
        return dizhiNum;
    }

    public void setDizhiNum(Integer dizhiNum) {
        this.dizhiNum = dizhiNum;
    }

    public Integer getDizhiYinyang() {
        return dizhiYinyang;
    }

    public void setDizhiYinyang(Integer dizhiYinyang) {
        this.dizhiYinyang = dizhiYinyang;
    }

    public Dizhi() {
    }

    public Dizhi(Integer dizhiId, String dizhiName, Integer dizhiNum, Integer dizhiYinyang) {
        this.dizhiId = dizhiId;
        this.dizhiName = dizhiName;
        this.dizhiNum = dizhiNum;
        this.dizhiYinyang = dizhiYinyang;
    }
}
