package com.example.mvvm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.mvvm.model.User;
import com.example.mvvm.repository.UserRepository;


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
    private UserRepository userRepository = UserRepository.getInstance();
    private LiveData<User> user;

    public LiveData<User> getUser(String username) {
        if (user == null) {
            user = userRepository.getUser(username);
        }
        return user;
    }

    @Deprecated
    public void setUsername(String username) {
//        user.setValue(new User(1, username));
    }
}
