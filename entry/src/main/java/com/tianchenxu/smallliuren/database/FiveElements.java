package com.tianchenxu.smallliuren.database;

import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.Column;
import ohos.data.orm.annotation.Entity;
import ohos.data.orm.annotation.ForeignKey;
import ohos.data.orm.annotation.PrimaryKey;

@Entity(tableName = "fiveElements")
public class FiveElements extends OrmObject {
    @PrimaryKey(autoGenerate = true)
    private Integer fiveElementsId;
    private String fiveElementsName;
    private String growFiveElement;
    private String restrainFiveElement;

    public String getGrowFiveElement() {
        return growFiveElement;
    }

    public void setGrowFiveElement(String growFiveElement) {
        this.growFiveElement = growFiveElement;
    }

    public String getRestrainFiveElement() {
        return restrainFiveElement;
    }

    public void setRestrainFiveElement(String restrainFiveElement) {
        this.restrainFiveElement = restrainFiveElement;
    }

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

    public FiveElements(Integer fiveElementsId, String fiveElementsName, String growFiveElement, String restrainFiveElement) {
        this.fiveElementsId = fiveElementsId;
        this.fiveElementsName = fiveElementsName;
        this.growFiveElement = growFiveElement;
        this.restrainFiveElement = restrainFiveElement;
    }

    public FiveElements() {
    }
}
