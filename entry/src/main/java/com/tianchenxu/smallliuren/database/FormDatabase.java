package com.tianchenxu.smallliuren.database;

import ohos.data.orm.OrmDatabase;
import ohos.data.orm.annotation.Database;

/**
 * Card Database
 */
@Database(
        entities = {
                Form.class,
        },
        version = 1)
public abstract class FormDatabase extends OrmDatabase {
}
