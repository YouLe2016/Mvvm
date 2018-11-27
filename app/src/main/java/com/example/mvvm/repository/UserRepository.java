package com.example.mvvm.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.mvvm.model.User;
import com.example.mvvm.model.ui.StateModel;
import com.example.mvvm.repository.local.LocalUserDataSource;
import com.example.mvvm.repository.remote.RemoteUserDataSource;
import com.example.mvvm.utils.NetworkUtils;

/**
 * 时间：2018/11/26 14:09
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
public class UserRepository {
    private static final UserRepository ourInstance = new UserRepository();

    public static UserRepository getInstance() {
        return ourInstance;
    }

    private UserRepository() {
    }

    private Context context;

    public void init(Context context) {
        this.context = context.getApplicationContext();
    }

    private UserDataSource remoteUserDataSource = RemoteUserDataSource.getInstance();
    private UserDataSource localUserDataSource = LocalUserDataSource.getInstance();

    public LiveData<StateModel<User>> getUser(String username) {
        if (NetworkUtils.isNetworkConnected(context)) {
            return remoteUserDataSource.queryUserByUsername(username);
        } else {
            return localUserDataSource.queryUserByUsername(username);
        }
    }

}
