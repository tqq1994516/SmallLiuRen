package com.tianchenxu.smallliuren.database;

import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.Entity;
import ohos.data.orm.annotation.PrimaryKey;

@Entity(tableName = "orientation")
public class Orientation extends OrmObject {
    @PrimaryKey(autoGenerate = true)
    private Integer orientationId;
    private String orientationName;

    public Integer getOrientationId() {
        return orientationId;
    }

    public void setOrientationId(Integer orientationId) {
        this.orientationId = orientationId;
    }

    public String getOrientationName() {
        return orientationName;
    }

    public void setOrientationName(String orientationName) {
        this.orientationName = orientationName;
    }

    public Orientation(Integer orientationId, String orientationName) {
        this.orientationId = orientationId;
        this.orientationName = orientationName;
    }

    public Orientation() {
    }
}
