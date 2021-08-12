package com.tianchenxu.smallliuren.database;

import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.Entity;
import ohos.data.orm.annotation.ForeignKey;
import ohos.data.orm.annotation.PrimaryKey;

@Entity(tableName = "attribute")
public class Attribute extends OrmObject {
    @PrimaryKey()
    private Long attributeId;
    private String deity;
    private String luckyNum;
    private String ominousNum;
    private String organ;
    @ForeignKey(name = "f_magnateOrientation", parentEntity = Orientation.class, parentColumns = {}, childColumns = {})
    private String magnateOrientation;
    private String offendOrientation;
    private String fiveElements;
    private String ghostsAndGods;

    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }

    public String getDeity() {
        return deity;
    }

    public void setDeity(String deity) {
        this.deity = deity;
    }

    public String getLuckyNum() {
        return luckyNum;
    }

    public void setLuckyNum(String luckyNum) {
        this.luckyNum = luckyNum;
    }

    public String getOminousNum() {
        return ominousNum;
    }

    public void setOminousNum(String ominousNum) {
        this.ominousNum = ominousNum;
    }

    public String getOrgan() {
        return organ;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    public String getMagnateOrientation() {
        return magnateOrientation;
    }

    public void setMagnateOrientation(String magnateOrientation) {
        this.magnateOrientation = magnateOrientation;
    }

    public String getOffendOrientation() {
        return offendOrientation;
    }

    public void setOffendOrientation(String offendOrientation) {
        this.offendOrientation = offendOrientation;
    }

    public String getFiveElements() {
        return fiveElements;
    }

    public void setFiveElements(String fiveElements) {
        this.fiveElements = fiveElements;
    }

    public String getGhostsAndGods() {
        return ghostsAndGods;
    }

    public void setGhostsAndGods(String ghostsAndGods) {
        this.ghostsAndGods = ghostsAndGods;
    }

    public Attribute() {
    }

    public Attribute(Long attributeId, String deity, String luckyNum, String ominousNum, String organ, String magnateOrientation, String offendOrientation, String fiveElements, String ghostsAndGods) {
        this.attributeId = attributeId;
        this.deity = deity;
        this.luckyNum = luckyNum;
        this.ominousNum = ominousNum;
        this.organ = organ;
        this.magnateOrientation = magnateOrientation;
        this.offendOrientation = offendOrientation;
        this.fiveElements = fiveElements;
        this.ghostsAndGods = ghostsAndGods;
    }
}
