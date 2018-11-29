package com.example.mvvm.widget.state;

/**
 * Created by haoran on 2018/5/11.
 */

public interface NetworkStateAction {
    void showSuccess();
    void showLoading();
    void showError();
    void showNoNetworkRetry();
    void showEmpty();
}
