package com.tianchenxu.smallliuren.database;

import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.*;

@Entity(tableName = "jingu", foreignKeys = {
        @ForeignKey(name = "f_mainTiangan", parentEntity = Tiangan.class, parentColumns = {"tianganId"}, childColumns = {"mainTiangan"}),
        @ForeignKey(name = "f_viceTiangan", parentEntity = Tiangan.class, parentColumns = {"tianganId"}, childColumns = {"viceTiangan"}),
        @ForeignKey(name = "f_dominantAffair", parentEntity = Affair.class, parentColumns = {"affairId"}, childColumns = {"dominantAffair"}),
        @ForeignKey(name = "f_recessiveAffair", parentEntity = Affair.class, parentColumns = {"affairId"}, childColumns = {"recessiveAffair"}),
        @ForeignKey(name = "f_mainRealms", parentEntity = Realms.class, parentColumns = {"realmsId"}, childColumns = {"mainRealms"}),
        @ForeignKey(name = "f_viceRealms", parentEntity = Realms.class, parentColumns = {"realmsId"}, childColumns = {"viceRealms"}),
        @ForeignKey(name = "f_mainStar", parentEntity = Star.class, parentColumns = {"starId"}, childColumns = {"mainStar"}),
        @ForeignKey(name = "f_viceStar", parentEntity = Star.class, parentColumns = {"starId"}, childColumns = {"viceStar"}),
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
    @Column(name = "mainRealms")
    private Integer mainRealms;
    @Column(name = "viceRealms")
    private Integer viceRealms;
    @Column(name = "mainStar")
    private Integer mainStar;
    @Column(name = "viceStar")
    private Integer viceStar;
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

    public Integer getMainRealms() {
        return mainRealms;
    }

    public void setMainRealms(Integer mainRealms) {
        this.mainRealms = mainRealms;
    }

    public Integer getViceRealms() {
        return viceRealms;
    }

    public void setViceRealms(Integer viceRealms) {
        this.viceRealms = viceRealms;
    }

    public Integer getMainStar() {
        return mainStar;
    }

    public void setMainStar(Integer mainStar) {
        this.mainStar = mainStar;
    }

    public Integer getViceStar() {
        return viceStar;
    }

    public void setViceStar(Integer viceStar) {
        this.viceStar = viceStar;
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

    public Jingu(Integer jinguId, String jinguName, int jinguNum, String presentation, String presentationDetail, Integer mainTiangan, Integer viceTiangan, Integer dominantAffair, Integer recessiveAffair, Integer mainRealms, Integer viceRealms, Integer mainStar, Integer viceStar) {
        this.jinguId = jinguId;
        this.jinguName = jinguName;
        this.jinguNum = jinguNum;
        this.presentation = presentation;
        this.presentationDetail = presentationDetail;
        this.mainTiangan = mainTiangan;
        this.viceTiangan = viceTiangan;
        this.dominantAffair = dominantAffair;
        this.recessiveAffair = recessiveAffair;
        this.mainRealms = mainRealms;
        this.viceRealms = viceRealms;
        this.mainStar = mainStar;
        this.viceStar = viceStar;
    }
}
