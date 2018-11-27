package com.example.mvvm.repository.local.service;

import android.arch.lifecycle.LiveData;

import com.example.mvvm.model.User;

/**
 * 时间：2018/11/26 15:09
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
public interface UserService {
    LiveData<Long> add(User user);

    LiveData<User> queryByUsername(String username);
}
