package com.example.mvvm;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * 时间：2018/11/26 13:30
 * 描述：Mvvm基本使用
 * ViewModel + LiveData 的使用
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
public class MainActivity extends AppCompatActivity {

    private TextView tvId;
    private TextView tvName;

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getUser().observe(this, this::updateView);

        userViewModel.setUsername("Mouse");
    }

    private void updateView(User user) {
        tvId.setText(String.valueOf(user.getId()));
        tvName.setText(user.getName());
    }

    private void initView() {
        tvId = findViewById(R.id.tv_id);
        tvName = findViewById(R.id.tv_name);
    }
}
