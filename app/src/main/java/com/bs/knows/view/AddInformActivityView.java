package com.bs.knows.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bs.knows.R;
import com.bs.knows.databinding.ActivityAddInformBinding;
import com.bs.knows.model.AddInformModel;
import com.bs.knows.model.UserUtilsModel;
import com.bs.knows.utils.StaticUtils;
import com.bs.knows.viewmodel.AddInfromVM;

public class AddInformActivityView extends BaseActivtyView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAddInformBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_add_inform);
        AddInfromVM addInfromVM = new AddInfromVM(this, binding);
        binding.setAddinform(addInfromVM);
        if (UserUtilsModel.UserIsLogin.getInstance().getRole().equals("学生")) {
            initNavBar(true, "添加留言", false);
            AddInformModel.InitInputView(this, binding);
        } else {
            initNavBar(true, "发布通知", false);
            AddInformModel.InitInputView(this, binding);
        }

        ImageView ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(v -> {
            if(UserUtilsModel.UserIsLogin.getInstance().getRole().equals("教师")){
                startActivity(new Intent(getApplicationContext(), TeacherActivityView.class));
            }
            if(UserUtilsModel.UserIsLogin.getInstance().getRole().equals("管理员")){
                startActivity(new Intent(getApplicationContext(), AdminActivityView.class));
            }
            if(UserUtilsModel.UserIsLogin.getInstance().getRole().equals("学生")){
                startActivity(new Intent(getApplicationContext(), StudentActivityView.class));
            }



        });

    }
}
