package com.tianchenxu.smallliuren.database;

import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.Column;
import ohos.data.orm.annotation.Entity;
import ohos.data.orm.annotation.ForeignKey;
import ohos.data.orm.annotation.PrimaryKey;

@Entity(tableName = "attribute", foreignKeys = {
        @ForeignKey(name = "f_jingu", parentEntity = Jingu.class, parentColumns = {"jinguNum"}, childColumns = {"jingu"}),
        @ForeignKey(name = "f_deity", parentEntity = Deity.class, parentColumns = {"deityId"}, childColumns = {"deity"}),
        @ForeignKey(name = "f_organ", parentEntity = Organ.class, parentColumns = {"organId"}, childColumns = {"organ"}),
        @ForeignKey(name = "f_magnateOrientation", parentEntity = Orientation.class, parentColumns = {"orientationId"}, childColumns = {"magnateOrientation"}),
        @ForeignKey(name = "f_offendOrientation", parentEntity = Orientation.class, parentColumns = {"orientationId"}, childColumns = {"offendOrientation"}),
        @ForeignKey(name = "f_fiveElements", parentEntity = FiveElements.class, parentColumns = {"fiveElementsId"}, childColumns = {"fiveElements"})
})
public class Attribute extends OrmObject {
    @PrimaryKey(autoGenerate = true)
    private Integer attributeId;

    @Column(name = "jingu")
    private int jingu;

    public int getJingu() {
        return jingu;
    }

    public void setJingu(int jingu) {
        this.jingu = jingu;
    }

    @Column(name = "deity")
    private Integer deity;
    private String luckyNum;
    private String successNum;

    public String getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(String successNum) {
        this.successNum = successNum;
    }

    private String ominousNum;
    @Column(name = "organ")
    private Integer organ;
    @Column(name = "magnateOrientation")
    private Integer magnateOrientation;
    @Column(name = "offendOrientation")
    private Integer offendOrientation;
    @Column(name = "fiveElements")
    private Integer fiveElements;
    private String ghostsAndGods;

    public Integer getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Integer attributeId) {
        this.attributeId = attributeId;
    }

    public Integer getDeity() {
        return deity;
    }

    public void setDeity(Integer deity) {
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

    public Integer getOrgan() {
        return organ;
    }

    public void setOrgan(Integer organ) {
        this.organ = organ;
    }

    public Integer getMagnateOrientation() {
        return magnateOrientation;
    }

    public void setMagnateOrientation(Integer magnateOrientation) {
        this.magnateOrientation = magnateOrientation;
    }

    public Integer getOffendOrientation() {
        return offendOrientation;
    }

    public void setOffendOrientation(Integer offendOrientation) {
        this.offendOrientation = offendOrientation;
    }

    public Integer getFiveElements() {
        return fiveElements;
    }

    public void setFiveElements(Integer fiveElements) {
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

    public Attribute(Integer attributeId, Integer deity, String luckyNum, String successNum, String ominousNum, Integer organ, Integer magnateOrientation, Integer offendOrientation, Integer fiveElements, String ghostsAndGods, int jingu) {
        this.attributeId = attributeId;
        this.jingu = jingu;
        this.deity = deity;
        this.luckyNum = luckyNum;
        this.successNum = successNum;
        this.ominousNum = ominousNum;
        this.organ = organ;
        this.magnateOrientation = magnateOrientation;
        this.offendOrientation = offendOrientation;
        this.fiveElements = fiveElements;
        this.ghostsAndGods = ghostsAndGods;
    }
}
