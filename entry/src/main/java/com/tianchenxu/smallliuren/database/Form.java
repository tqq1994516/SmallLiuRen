package com.tianchenxu.smallliuren.database;

import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.Entity;
import ohos.data.orm.annotation.PrimaryKey;

/**
 * Card Table
 */
@Entity(tableName = "form")
public class Form extends OrmObject {
    @PrimaryKey()
    private Long formId;
    private String formName;
    private Integer dimension;

    public Form(Long formId, String formName, Integer dimension) {
        this.formId = formId;
        this.formName = formName;
        this.dimension = dimension;
    }

    public Form() { }

    public Integer getDimension() {
        return dimension;
    }

    public void setDimension(Integer dimension) {
        this.dimension = dimension;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }
}
