package com.bs.knows.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.DataBindingUtil;

import com.bs.knows.R;
import com.bs.knows.databinding.ActivityMineBinding;
import com.bs.knows.model.UserUtilsModel;
import com.bs.knows.viewmodel.UserMineVM;

public class MineActivityView extends BaseActivtyView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        ActivityMineBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_mine);
        UserMineVM userMineVM =new UserMineVM();
        binding.setUserDetail(userMineVM);

        initView();
        UserUtilsModel.setUserName(binding);
    }

    private void initView() {
        initNavBar(true, "我的", false);

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
