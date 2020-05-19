package com.bs.knows.view;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;


import com.bs.knows.R;

import com.bs.knows.databinding.ActivityInformsViewBinding;
import com.bs.knows.model.InitListModel;
import com.bs.knows.model.UserUtilsModel;


public class InformsActivityView extends BaseActivtyView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityInformsViewBinding binding= DataBindingUtil.setContentView(this, R.layout.activity_informs_view);
        initNavBar(true,"通 知 消 息",false,true,this);
        InitListModel.initInformListView(binding,this);

        ImageView ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(v -> {
            switch (UserUtilsModel.UserIsLogin.getInstance().getRole()){
                case "学生":
                    startActivity(new Intent(getApplicationContext(),StudentActivityView.class));
                    break;
                case "教师":
                    startActivity(new Intent(getApplicationContext(),TeacherActivityView.class));
                    break;
                case "管理员":
                    startActivity(new Intent(getApplicationContext(),AdminActivityView.class));
                    break;
            }

        });

    }




}
