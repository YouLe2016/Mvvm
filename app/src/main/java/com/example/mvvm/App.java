package com.example.mvvm;

import android.app.Application;
import android.content.Context;

import com.example.mvvm.repository.local.db.DBHelper;

/**
 * 时间：2018/11/26 15:07
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
public class App extends Application {
    private static Context context;

    public static Context getAppContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        DBHelper.getInstance().init();
    }
}
