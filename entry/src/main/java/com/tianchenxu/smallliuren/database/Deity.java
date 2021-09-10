package com.tianchenxu.smallliuren.database;

import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.Entity;
import ohos.data.orm.annotation.PrimaryKey;

@Entity(tableName = "deity")
public class Deity extends OrmObject {
    @PrimaryKey(autoGenerate = true)
    private Integer deityId;
    private String deityName;
    private String presentation;

    public Integer getDeityId() {
        return deityId;
    }

    public void setDeityId(Integer deityId) {
        this.deityId = deityId;
    }

    public String getDeityName() {
        return deityName;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public void setDeityName(String deityName) {
        this.deityName = deityName;
    }

    public Deity() {
    }

    public Deity(Integer deityId, String deityName, String presentation) {
        this.deityId = deityId;
        this.deityName = deityName;
        this.presentation = presentation;
    }
}
