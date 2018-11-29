package com.example.mvvm.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.mvvm.R;
import com.example.mvvm.databinding.ActivityMain2Binding;
import com.example.mvvm.view.project.ProjectActivity;
import com.example.mvvm.view.user.UserActivity;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMain2Binding binding = DataBindingUtil.setContentView(this, R.layout.activity_main2);
        binding.btUser.setOnClickListener(this);
        binding.btProject.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_user:
                startActivity(new Intent(this, UserActivity.class));
                break;
            case R.id.bt_project:
                startActivity(new Intent(this, ProjectActivity.class));
                break;
            default:
                break;
        }
    }
}
