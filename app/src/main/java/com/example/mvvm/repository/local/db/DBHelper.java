package com.example.mvvm.repository.local.db;

import android.arch.persistence.room.Room;

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

    public void init() {
        db = Room.databaseBuilder(App.getAppContext(), DB.class, DATABASE_NAME).build();
    }

    public DB getDb() {
        return db;
    }
}
