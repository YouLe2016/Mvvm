package com.example.mvvm;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mvvm.model.User;
import com.example.mvvm.model.ui.StateFactory;
import com.example.mvvm.model.ui.StateModel;
import com.example.mvvm.view.NetworkStateView;
import com.gyf.barlibrary.ImmersionBar;

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
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private UserViewModel userViewModel;

    private TextView tvId;
    private TextView tvName;
    /**
     * 获取用户信息
     */
    private Button btGet;
    private NetworkStateView networkSateView;
    /**
     * Name
     */
    private EditText editName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initUI() {
        ImmersionBar.with(this)
                .fitsSystemWindows(false)  //使用该属性,必须指定状态栏颜色
                .statusBarColor(R.color.colorPrimaryDark)  //指定状态栏颜色,根据情况是否设置
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
        //让布局向上移来显示软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    private void initView() {
        tvId = findViewById(R.id.tv_id);
        tvName = findViewById(R.id.tv_name);
        btGet = findViewById(R.id.bt_get);
        btGet.setOnClickListener(this);
        networkSateView = findViewById(R.id.network_sate_view);
        editName = findViewById(R.id.editText);
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
                    userViewModel.reload(editName.getText().toString());
                    i = 0;
                }
                break;
            default:
                break;
        }
    }

}
