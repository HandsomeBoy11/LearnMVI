package com.wj.learnmvi.dao;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Author:WJ
 * Date:2022/9/28 15:46
 * Describe：OpenHelper
 */

public class BuriedPointOpenHelper extends DaoMaster.OpenHelper {
    public BuriedPointOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);

        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        }, UserInfoDao.class, ModInfoDao.class, PageInfoDao.class, BtnInfoDao.class);
    }
}
