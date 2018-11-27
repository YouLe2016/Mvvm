package com.example.mvvm;

import android.app.Application;

import com.example.mvvm.repository.UserRepository;
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
    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        DBHelper.getInstance().init(this);
        UserRepository.getInstance().init(this);
    }
}
