package com.example.mvvm.repository.remote;

import com.example.mvvm.model.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 时间：2018/11/26 14:07
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
public interface UserApi {
    @GET("users/{username}")
    Call<User> queryUserByUsername(@Path("username") String username);
}
