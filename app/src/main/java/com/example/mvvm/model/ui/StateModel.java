package com.example.mvvm.model.ui;

/**
 * 时间：2018/11/27 11:44
 * 描述：视图状态的实体类
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
public class StateModel<T> {
    private Status status;
    private T data;
    private Throwable error;

    public StateModel(Status status, T data, Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public Status getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public Throwable getError() {
        return error;
    }


}
