package com.tianchenxu.smallliuren.utils;

import com.tianchenxu.smallliuren.database.Form;
import ohos.data.orm.OrmContext;
import ohos.data.orm.OrmPredicates;

import java.util.List;

/**
 * Card Database Operations
 */
public class DatabaseUtils {
    /**
     * delete data
     *
     * @param formId form id
     * @param connect data connection
     */
    public static void deleteFormData(long formId, OrmContext connect) {
        OrmPredicates where = connect.where(Form.class);
        where.equalTo("formId", formId);
        List<Form> query = connect.query(where);
        if (!query.isEmpty()) {
            connect.delete(query.get(0));
            connect.flush();
        }
    }

    /**
     * add card info
     *
     * @param form card object
     * @param connect data connection
     */
    public static void insertForm(Form form, OrmContext connect) {
        connect.insert(form);
        connect.flush();
    }
}
