package com.tianchenxu.smallliuren.database;

import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.Column;
import ohos.data.orm.annotation.Entity;
import ohos.data.orm.annotation.ForeignKey;
import ohos.data.orm.annotation.PrimaryKey;

@Entity(tableName = "star", foreignKeys = {
        @ForeignKey(name = "f_realms", parentEntity = Realms.class, parentColumns = {"realmsId"}, childColumns = {"realms"})
})
public class Star extends OrmObject {
    @PrimaryKey(autoGenerate = true)
    private Integer starId;
    private String starName;
    private String detail;
    @Column(name = "realms")
    private Integer realms;

    public Integer getRealms() {
        return realms;
    }

    public void setRealms(Integer realms) {
        this.realms = realms;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Star(Integer starId, String starName, String detail, Integer realms) {
        this.starId = starId;
        this.starName = starName;
        this.detail = detail;
        this.realms = realms;
    }

    public Integer getStarId() {
        return starId;
    }

    public void setStarId(Integer starId) {
        this.starId = starId;
    }

    public String getStarName() {
        return starName;
    }

    public void setStarName(String starName) {
        this.starName = starName;
    }

    public Star() {
    }
}
