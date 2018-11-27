package com.example.mvvm.repository;

import android.arch.lifecycle.LiveData;

import com.example.mvvm.model.User;

/**
 * 时间：2018/11/26 16:09
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
public interface UserDataSource {
    LiveData<User> queryUserByUsername(String username);
}
