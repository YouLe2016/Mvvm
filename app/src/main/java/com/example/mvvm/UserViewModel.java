package com.example.mvvm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;


/**
 * 时间：2018/11/26 13:17
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
public class UserViewModel extends ViewModel {
    private MutableLiveData<User> user;

    public MutableLiveData<User> getUser() {
        if (user == null) {
            user = new MutableLiveData<>();
        }
        return user;
    }

    public void setUsername(String username) {
        user.setValue(new User(1, username));
    }
}
