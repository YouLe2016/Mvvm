package com.example.mvvm.repository.local;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.example.mvvm.model.User;
import com.example.mvvm.model.ui.StateFactory;
import com.example.mvvm.model.ui.StateModel;
import com.example.mvvm.repository.UserDataSource;
import com.example.mvvm.repository.local.service.UserService;
import com.example.mvvm.repository.local.service.UserServiceImpl;


/**
 * 时间：2018/11/26 16:11
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
public class LocalUserDataSource implements UserDataSource {
    private static final LocalUserDataSource instance = new LocalUserDataSource();

    private LocalUserDataSource() {
    }

    public static LocalUserDataSource getInstance() {
        return instance;
    }


    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public LiveData<StateModel<User>> queryUserByUsername(String username) {
        MediatorLiveData<StateModel<User>> data = new MediatorLiveData<>();
        data.setValue(StateFactory.loading());
        data.addSource(userService.queryByUsername(username), user -> {
            if (null == user) {
                data.setValue(StateFactory.empty());
            } else {
                data.setValue(StateFactory.content(user));
            }
        });
        return data;
    }

    public LiveData<Long> addUser(User user) {
        return userService.add(user);
    }
}
