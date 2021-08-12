package com.tianchenxu.smallliuren.database;

import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.Entity;
import ohos.data.orm.annotation.PrimaryKey;

@Entity(tableName = "assert")
public class Assert extends OrmObject {
    @PrimaryKey()
    private Long assertId;
    private int dayNum;
    private int timeNum;
    private String assertText;

    public String getAssertText() {
        return assertText;
    }

    public void setAssertText(String assertText) {
        this.assertText = assertText;
    }

    public Long getAssertId() {
        return assertId;
    }

    public void setAssertId(Long assertId) {
        this.assertId = assertId;
    }

    public int getDayNum() {
        return dayNum;
    }

    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }

    public int getTimeNum() {
        return timeNum;
    }

    public void setTimeNum(int timeNum) {
        this.timeNum = timeNum;
    }

    public Assert() {
    }

    public Assert(Long assertId, int dayNum, int timeNum, String assertText) {
        this.assertId = assertId;
        this.dayNum = dayNum;
        this.timeNum = timeNum;
        this.assertText = assertText;
    }
}
