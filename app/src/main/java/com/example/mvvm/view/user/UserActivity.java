package com.example.mvvm.view.user;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.mvvm.R;
import com.example.mvvm.databinding.ActivityUserBinding;
import com.example.mvvm.model.User;
import com.example.mvvm.model.exception.ApiException;
import com.example.mvvm.model.ui.StateFactory;
import com.example.mvvm.model.ui.StateModel;

/**
 * 时间：2018/11/26 13:30
 * 描述：Mvvm基本使用
 * <p>
 * 1. ViewModel + LiveData 的使用
 * <p>
 * 2. Repository （数据仓库）
 * Retrofit的使用
 * <p>
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
public class UserActivity extends AppCompatActivity implements View.OnClickListener {
    private UserViewModel userViewModel;
    private ActivityUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user);
        binding.btGet.setOnClickListener(this);

        initData();
    }

    private void initData() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getUser().observe(this, this::updateView);
    }

    private void updateView(StateModel<User> user) {
        switch (user.getStatus()) {
            case Content:
                binding.networkSateView.showSuccess();
                binding.setUser(user.getData());
                break;
            case Empty: {
                binding.networkSateView.showEmpty();
                break;
            }
            case Error: {
                binding.networkSateView.showError();
                break;
            }
            case Loading: {
                binding.networkSateView.showLoading();
                break;
            }
            case NoNet: {
                binding.networkSateView.showNoNetworkRetry();
                break;
            }
            default:
                break;
        }
    }

    int i = 0;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_get:
                if (i == 0) {
                    updateView(StateFactory.empty());
                    i++;
                } else if (i == 1) {
                    updateView(StateFactory.error(null));
                    i++;
                } else if (i == 2) {
                    updateView(StateFactory.noNet());
                    i++;
                } else if (i == 3) {
                    userViewModel.reload("YouLe2016");
                    i = 0;
                }
                break;
            default:
                break;
        }
    }
}
