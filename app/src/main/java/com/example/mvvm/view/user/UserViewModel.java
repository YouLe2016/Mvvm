package com.example.mvvm.view.user;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.mvvm.model.User;
import com.example.mvvm.model.ui.StateModel;
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
    private static final String TAG = "UserViewModel";

    private LiveData<StateModel<User>> user;
    private MutableLiveData<String> username;

    /*
    我们可以通过 Transformations.switchMap 来生成一个监听 username 的 LiveData
     */
    public LiveData<StateModel<User>> getUser() {
        if (user == null) {
            this.username = new MutableLiveData<>();
            user = Transformations.switchMap(this.username, username -> UserRepository.getInstance().getUser(username));
        }
        return user;
    }

    public void reload(String username) {
        this.username.setValue(username);
    }

    @Deprecated
    public void setUsername(String username) {
//        user.setValue(new User(1, username));
    }


}
