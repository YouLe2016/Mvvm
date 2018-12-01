package com.example.mvvm.repository.local.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

import com.example.mvvm.App;

/**
 * 时间：2018/11/26 14:55
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
public class DBHelper {
    private static final DBHelper instance = new DBHelper();
    private static final String DATABASE_NAME = "c_cache";

    private DBHelper() {

    }

    public static DBHelper getInstance() {
        return instance;
    }

    private DB db;

    private Migration migration = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `projects` (`totalCount` INTEGER NOT NULL, `incompleteResults` INTEGER NOT NULL, `page` INTEGER NOT NULL, `items` TEXT, PRIMARY KEY(`page`))");
        }
    };

    public void init() {
        db = Room.databaseBuilder(App.getAppContext(), DB.class, DATABASE_NAME)
                .addMigrations(migration)
                .build();
    }

    public DB getDb() {
        return db;
    }
}
