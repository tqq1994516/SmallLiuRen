package com.tianchenxu.smallliuren.database;

import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.Entity;
import ohos.data.orm.annotation.ForeignKey;
import ohos.data.orm.annotation.PrimaryKey;

@Entity(tableName = "relation")
public class Relation extends OrmObject {
    @PrimaryKey(autoGenerate = true)
    private Integer relationId;

    private String relationName;

    public Relation(Integer relationId, String relationName) {
        this.relationId = relationId;
        this.relationName = relationName;
    }

    public Relation() {
    }

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }
}
