package com.example.mvvm.model.ui;

/**
 * 时间：2018/11/27 11:48
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
public class StateFactory {

    public static <T> StateModel<T> loading() {
        return new StateModel<>(Status.Loading, null, null);
    }

    public static <T> StateModel<T> content(T data) {
        return new StateModel<>(Status.Content, data, null);
    }


    public static <T> StateModel<T> empty() {
        return new StateModel<>(Status.Empty, null, null);
    }


    public static <T> StateModel<T> error(Throwable error) {
        return new StateModel<>(Status.Error, null, error);
    }

    public static <T> StateModel<T> noNet() {
        return new StateModel<>(Status.NoNet, null, null);
    }
}
