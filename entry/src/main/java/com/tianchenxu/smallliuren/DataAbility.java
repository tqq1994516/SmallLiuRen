package com.tianchenxu.smallliuren;

import com.tianchenxu.smallliuren.database.FormDatabase;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.data.DatabaseHelper;
import ohos.data.orm.OrmContext;
import ohos.data.resultset.ResultSet;
import ohos.data.rdb.ValuesBucket;
import ohos.data.dataability.DataAbilityPredicates;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.utils.net.Uri;
import ohos.utils.PacMap;

import java.io.FileDescriptor;

public class DataAbility extends Ability {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "Demo");
    private static final String DATABASE_NAME = "FormDatabase.db";
    private static final String DATABASE_NAME_CLASS = "FormDatabase";
    private OrmContext connect;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        DatabaseHelper helper = new DatabaseHelper(this);
        connect = helper.getOrmContext(DATABASE_NAME_CLASS, DATABASE_NAME, FormDatabase.class);
        HiLog.info(LABEL_LOG, "DataAbility onStart");
    }

    @Override
    public ResultSet query(Uri uri, String[] columns, DataAbilityPredicates predicates) {
        return null;
    }

    @Override
    public int insert(Uri uri, ValuesBucket value) {
        HiLog.info(LABEL_LOG, "DataAbility insert");
        return 999;
    }

    @Override
    public int delete(Uri uri, DataAbilityPredicates predicates) {
        return 0;
    }

    @Override
    public int update(Uri uri, ValuesBucket value, DataAbilityPredicates predicates) {
        return 0;
    }

    @Override
    public FileDescriptor openFile(Uri uri, String mode) {
        return null;
    }

    @Override
    public String[] getFileTypes(Uri uri, String mimeTypeFilter) {
        return new String[0];
    }

    @Override
    public PacMap call(String method, String arg, PacMap extras) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }
}