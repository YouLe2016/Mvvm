package com.example.mvvm.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.mvvm.model.User;
import com.example.mvvm.utils.RetrofitFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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


    private UserApi userApi = RetrofitFactory.getInstance().create(UserApi.class);

    public LiveData<User> getUser(String username) {
        MutableLiveData<User> user = new MutableLiveData<>();
        userApi.queryUserByUsername(username).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, Response<User> response) {
                user.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<User> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return user;
    }
}
