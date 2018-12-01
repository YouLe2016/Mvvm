package com.example.mvvm.repository.local.service;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.example.mvvm.model.User;
import com.example.mvvm.repository.local.dao.UserDao;
import com.example.mvvm.repository.local.db.DBHelper;

/**
 * 时间：2018/11/26 15:10
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
public class UserServiceImpl implements UserService {

    private static final UserServiceImpl instance = new UserServiceImpl();

    private UserDao userDao = DBHelper.getInstance().getDb().getUserDao();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public LiveData<Long> add(User user) {
        // 注意: room 中 insert方法不可以在主线程中执行
        final MutableLiveData<Long> data = new MutableLiveData<>();
        new AddUserTask().init(userDao, data).execute();
        return data;
    }

    @Override
    public LiveData<User> queryByUsername(String username) {
        return userDao.queryByUsername(username);
    }

    private static class AddUserTask extends AsyncTask<User, Void, Long> {
        UserDao userDao;
        MutableLiveData<Long> result;

        private AddUserTask init(UserDao userDao, MutableLiveData<Long> result) {
            this.userDao = userDao;
            this.result = result;
            return this;
        }

        @Override
        protected Long doInBackground(User... user) {
            return userDao.add(user[0]);
        }

        @Override
        protected void onPostExecute(Long rowId) {
            result.setValue(rowId);
        }
    }

}
