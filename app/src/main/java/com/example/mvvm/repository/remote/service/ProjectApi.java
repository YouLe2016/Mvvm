package com.example.mvvm.repository.remote.service;

import com.example.mvvm.model.project.Projects;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 时间：2018/11/28 14:29
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
public interface ProjectApi {

    @GET("search/repositories?q=tetris+language:assembly&sort=stars&order=desc")
    Observable<Projects> queryProjects(@Query("page") int page);
}
