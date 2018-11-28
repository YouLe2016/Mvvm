package com.example.mvvm.repository.local.service;

import android.os.AsyncTask;

import com.example.mvvm.model.User;
import com.example.mvvm.repository.local.dao.UserDao;
import com.example.mvvm.repository.local.db.DBHelper;
import com.example.mvvm.repository.remote.Result;

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
    private static final String TAG = "UserServiceImpl";

    private static final UserServiceImpl instance = new UserServiceImpl();

    private UserDao userDao = DBHelper.getInstance().getDb().getUserDao();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public void add(User user) {
        // 注意: room 中 insert方法不可以在主线程中执行
        new AddUserTask().init(userDao).execute(user);
    }

    @Override
    public void queryByUsername(String username, Result<User> result) throws RuntimeException {
        new QueryUserTask().init(userDao, result).execute(username);
    }

    private static class AddUserTask extends AsyncTask<User, Void, Long> {
        UserDao userDao;

        private AddUserTask init(UserDao userDao) {
            this.userDao = userDao;
            return this;
        }

        @Override
        protected Long doInBackground(User... users) {
            return userDao.add(users[0]);
        }
    }

    private static class QueryUserTask extends AsyncTask<String, Void, Object> {

        private UserDao userDao;
        private Result<User> result;

        private QueryUserTask init(UserDao dao, Result<User> result) {
            this.userDao = dao;
            this.result = result;
            return this;
        }

        @Override
        protected Object doInBackground(String... username) {
            try {
                return userDao.queryByUsername(username[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return e;
            }
        }

        @Override
        protected void onPostExecute(Object obj) {
            if (obj instanceof User) {
                result.onSuccess((User) obj);
            } else if (obj instanceof Exception) {
                result.onFailed((Exception) obj);
            } else {
                result.onSuccess(null);
            }
        }
    }

}
