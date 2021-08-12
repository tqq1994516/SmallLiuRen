package com.tianchenxu.smallliuren.database;

import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.Entity;
import ohos.data.orm.annotation.PrimaryKey;

@Entity(tableName = "fiveElements")
public class FiveElements extends OrmObject {
    @PrimaryKey(autoGenerate = true)
    private Integer fiveElementsId;
    private String fiveElementsName;

    public Integer getFiveElementsId() {
        return fiveElementsId;
    }

    public void setFiveElementsId(Integer fiveElementsId) {
        this.fiveElementsId = fiveElementsId;
    }

    public String getFiveElementsName() {
        return fiveElementsName;
    }

    public void setFiveElementsName(String fiveElementsName) {
        this.fiveElementsName = fiveElementsName;
    }

    public FiveElements(Integer fiveElementsId, String fiveElementsName) {
        this.fiveElementsId = fiveElementsId;
        this.fiveElementsName = fiveElementsName;
    }

    public FiveElements() {
    }
}
