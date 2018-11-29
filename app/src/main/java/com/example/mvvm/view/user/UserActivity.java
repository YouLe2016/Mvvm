package com.example.mvvm.view.user;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mvvm.R;
import com.example.mvvm.model.User;
import com.example.mvvm.model.ui.StateFactory;
import com.example.mvvm.model.ui.StateModel;
import com.example.mvvm.widget.state.NetworkStateView;

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
    private static final String TAG = "UserActivity";

    private UserViewModel userViewModel;

    private TextView tvId;
    private TextView tvName;
    /**
     * 获取用户信息
     */
    private Button btGet;
    private NetworkStateView networkSateView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();
        initData();
    }

    private void initData() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getUser().observe(this, this::updateView);
    }

    private void updateView(StateModel<User> user) {
        switch (user.getStatus()) {
            case Content:
                networkSateView.showSuccess();
                tvId.setText(String.valueOf(user.getData().getId()));
                tvName.setText(user.getData().getName());
                break;
            case Empty: {
                networkSateView.showEmpty();
                break;
            }
            case Error: {
                networkSateView.showError();
                break;
            }
            case Loading: {
                networkSateView.showLoading();
                break;
            }
            case NoNet: {
                networkSateView.showNoNetworkRetry();
                break;
            }
            default:
                break;
        }
    }

    private void initView() {
        tvId = findViewById(R.id.tv_id);
        tvName = findViewById(R.id.tv_name);
        btGet = findViewById(R.id.bt_get);
        btGet.setOnClickListener(this);
        networkSateView = findViewById(R.id.network_sate_view);
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
