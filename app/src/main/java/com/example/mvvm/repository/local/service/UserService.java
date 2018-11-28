package com.example.mvvm.repository.local.service;

import com.example.mvvm.model.User;
import com.example.mvvm.repository.remote.Result;

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
    void add(User user);

    void queryByUsername(String username, Result<User> result) throws RuntimeException;

}
