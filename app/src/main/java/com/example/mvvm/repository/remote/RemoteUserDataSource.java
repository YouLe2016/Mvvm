package com.example.mvvm.repository.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.mvvm.model.User;
import com.example.mvvm.model.ui.StateFactory;
import com.example.mvvm.model.ui.StateModel;
import com.example.mvvm.repository.UserDataSource;
import com.example.mvvm.repository.local.LocalUserDataSource;
import com.example.mvvm.utils.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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

    @Override
    public LiveData<StateModel<User>> queryUserByUsername(String username) {
        MutableLiveData<StateModel<User>> data = new MutableLiveData<>();
        data.setValue(StateFactory.loading());
        userApi.queryUserByUsername(username).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                User user = response.body();
                if (user == null) {
                    data.setValue(StateFactory.empty());
                } else {
                    data.setValue(StateFactory.content(user));
                }
                // update cache
                LocalUserDataSource.getInstance().addUser(user);
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                t.printStackTrace();
                data.setValue(StateFactory.error(t));
            }
        });
        return data;
    }

}
