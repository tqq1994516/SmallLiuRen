package com.tianchenxu.smallliuren.database;


import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.*;

@Entity(tableName = "tiangan", indices = {@Index(value = {"tianganName"}, name = "tianganName_index", unique = true)}, foreignKeys = {
        @ForeignKey(name = "f_fiveElements", parentEntity = FiveElements.class, parentColumns = {"fiveElementsId"}, childColumns = {"fiveElements"})})
public class Tiangan extends OrmObject {
    @PrimaryKey(autoGenerate = true)
    private Integer tianganId;
    private String tianganName;
    private int tianganNum;
    private int tianganYinyang;
    private String representative;

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

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

    public int getTianganYinyang() {
        return tianganYinyang;
    }

    public void setTianganYinyang(int tianganYinyang) {
        this.tianganYinyang = tianganYinyang;
    }

    @Column(name = "fiveElements")
    private Integer fiveElements;

    public void setTianganNum(int tianganNum) {
        this.tianganNum = tianganNum;
    }

    public Integer getFiveElements() {
        return fiveElements;
    }

    public void setFiveElements(Integer fiveElements) {
        this.fiveElements = fiveElements;
    }

    public Tiangan() {
    }

    public Tiangan(Integer tianganId, String tianganName, int tianganNum, int tianganYinyang, Integer fiveElements, String representative) {
        this.tianganId = tianganId;
        this.tianganName = tianganName;
        this.tianganNum = tianganNum;
        this.tianganYinyang = tianganYinyang;
        this.fiveElements = fiveElements;
        this.representative = representative;
    }
}
