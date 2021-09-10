package com.tianchenxu.smallliuren.database;

import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.*;

@Entity(tableName = "jingu", foreignKeys = {
        @ForeignKey(name = "f_mainTiangan", parentEntity = Tiangan.class, parentColumns = {"tianganId"}, childColumns = {"mainTiangan"}),
        @ForeignKey(name = "f_viceTiangan", parentEntity = Tiangan.class, parentColumns = {"tianganId"}, childColumns = {"viceTiangan"}),
        @ForeignKey(name = "f_dominantAffair", parentEntity = Affair.class, parentColumns = {"affairId"}, childColumns = {"dominantAffair"}),
        @ForeignKey(name = "f_recessiveAffair", parentEntity = Affair.class, parentColumns = {"affairId"}, childColumns = {"recessiveAffair"})
})
public class Jingu extends OrmObject {
    @PrimaryKey(autoGenerate = true)
    private Integer jinguId;
    private String jinguName;
    private int jinguNum;
    private String presentation;
    private String presentationDetail;
    @Column(name = "mainTiangan")
    private Integer mainTiangan;
    @Column(name = "viceTiangan")
    private Integer viceTiangan;
    @Column(name = "dominantAffair")
    private Integer dominantAffair;
    @Column(name = "recessiveAffair")
    private Integer recessiveAffair;

    public Integer getMainTiangan() {
        return mainTiangan;
    }

    public void setMainTiangan(Integer mainTiangan) {
        this.mainTiangan = mainTiangan;
    }

    public Integer getDominantAffair() {
        return dominantAffair;
    }

    public void setDominantAffair(Integer dominantAffair) {
        this.dominantAffair = dominantAffair;
    }

    public Integer getRecessiveAffair() {
        return recessiveAffair;
    }

    public void setRecessiveAffair(Integer recessiveAffair) {
        this.recessiveAffair = recessiveAffair;
    }

    public Integer getViceTiangan() {
        return viceTiangan;
    }

    public void setViceTiangan(Integer viceTiangan) {
        this.viceTiangan = viceTiangan;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getPresentationDetail() {
        return presentationDetail;
    }

    public void setPresentationDetail(String presentationDetail) {
        this.presentationDetail = presentationDetail;
    }

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

    public Jingu(Integer jinguId, String jinguName, int jinguNum, String presentation, String presentationDetail, Integer mainTiangan, Integer viceTiangan, Integer dominantAffair, Integer recessiveAffair) {
        this.jinguId = jinguId;
        this.jinguName = jinguName;
        this.jinguNum = jinguNum;
        this.presentation = presentation;
        this.presentationDetail = presentationDetail;
        this.mainTiangan = mainTiangan;
        this.viceTiangan = viceTiangan;
        this.dominantAffair = dominantAffair;
        this.recessiveAffair = recessiveAffair;
    }
}
