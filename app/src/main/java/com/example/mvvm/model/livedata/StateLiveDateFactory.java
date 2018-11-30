package com.example.mvvm.model.livedata;

import android.arch.lifecycle.LiveData;

import com.example.mvvm.model.ui.StateModel;

import io.reactivex.Observable;

/**
 * 时间：2018/11/30 9:58
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
public class StateLiveDateFactory {
    public static <T> LiveData<StateModel<T>> createStateModel(Observable<T> observable) {
        return new ObservableStateLiveData<>(observable);
    }
}
