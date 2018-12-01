package com.example.mvvm.repository.remote;

import android.arch.lifecycle.LiveData;

import com.example.mvvm.model.User;
import com.example.mvvm.model.livedata.StateLiveDateFactory;
import com.example.mvvm.model.ui.StateModel;
import com.example.mvvm.repository.UserDataSource;
import com.example.mvvm.repository.local.LocalUserDataSource;
import com.example.mvvm.repository.remote.service.UserApi;
import com.example.mvvm.utils.net.RetrofitFactory;
import com.example.mvvm.utils.net.RxUtils;

/**
 * 时间：2018/11/26 16:44
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
public class RemoteUserDataSource implements UserDataSource {
    private static final RemoteUserDataSource instance = new RemoteUserDataSource();

    private RemoteUserDataSource() {
    }

    public static RemoteUserDataSource getInstance() {
        return instance;
    }


    private UserApi userApi = RetrofitFactory.getInstance().create(UserApi.class);
    private LocalUserDataSource localUserDataSource = LocalUserDataSource.getInstance();

    @Override
    public LiveData<StateModel<User>> queryUserByUsername(String username) {
        return StateLiveDateFactory.createStateModel(userApi.queryUserByUsername(username)
                .compose(RxUtils.transform())
                .map(user -> {
                    localUserDataSource.addUser(user);
                    return user;
                }));
    }

}
