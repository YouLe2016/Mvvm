package com.example.mvvm.repository.remote;

/**
 * 时间：2018/11/27 15:43
 * 描述：老项目的数据模型
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
public interface Result<T> {
    void onSuccess(T data);

    void onFailed(Throwable throwable);
}