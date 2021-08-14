package com.tianchenxu.smallliuren.database;

import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.Entity;
import ohos.data.orm.annotation.PrimaryKey;

import java.sql.Time;
import java.util.Calendar;

@Entity(tableName = "oldLunarHour")
public class OldLunarHour extends OrmObject{
    @PrimaryKey(autoGenerate = true)
    private Integer oldLunarHourId;
    private String oldLunarHourText;
    private Calendar lastUpdateTime;

    public OldLunarHour() {
    }

    public OldLunarHour(Integer oldLunarHourId, String oldLunarHourText, Calendar lastUpdateTime) {
        this.oldLunarHourId = oldLunarHourId;
        this.oldLunarHourText = oldLunarHourText;
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getOldLunarHourId() {
        return oldLunarHourId;
    }

    public void setOldLunarHourId(Integer oldLunarHourId) {
        this.oldLunarHourId = oldLunarHourId;
    }

    public String getOldLunarHourText() {
        return oldLunarHourText;
    }

    public void setOldLunarHourText(String oldLunarHourText) {
        this.oldLunarHourText = oldLunarHourText;
    }

    public Calendar getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Calendar lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
