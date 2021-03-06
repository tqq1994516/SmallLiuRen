package com.tianchenxu.smallliuren.database;

import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.*;

@Entity(tableName = "dizhi", indices = {@Index(value = {"dizhiName"}, name = "dizhiName_index", unique = true)},
        foreignKeys = { @ForeignKey(name = "f_fiveElements", parentEntity = FiveElements.class, parentColumns = {"fiveElementsId"}, childColumns = {"fiveElements"})
})
public class Dizhi extends OrmObject {
    @PrimaryKey(autoGenerate = true)
    private Long dizhiId;
    private String dizhiName;
    private int dizhiNum;
    private int dizhiYinyang;

    public Long getDizhiId() {
        return dizhiId;
    }

    @Column(name = "fiveElements")
    private Integer fiveElements;

    public Integer getFiveElements() {
        return fiveElements;
    }

    public void setFiveElements(Integer fiveElements) {
        this.fiveElements = fiveElements;
    }

    public void setDizhiId(Long dizhiId) {
        this.dizhiId = dizhiId;
    }

    public String getDizhiName() {
        return dizhiName;
    }

    public void setDizhiName(String dizhiName) {
        this.dizhiName = dizhiName;
    }

    public int getDizhiNum() {
        return dizhiNum;
    }

    public void setDizhiNum(int dizhiNum) {
        this.dizhiNum = dizhiNum;
    }

    public int getDizhiYinyang() {
        return dizhiYinyang;
    }

    public void setDizhiYinyang(int dizhiYinyang) {
        this.dizhiYinyang = dizhiYinyang;
    }

    public Dizhi() {
    }

    public Dizhi(Long dizhiId, String dizhiName, Integer dizhiNum, Integer dizhiYinyang, Integer fiveElements) {
        this.dizhiId = dizhiId;
        this.dizhiName = dizhiName;
        this.dizhiNum = dizhiNum;
        this.dizhiYinyang = dizhiYinyang;
        this.fiveElements = fiveElements;
    }
}
