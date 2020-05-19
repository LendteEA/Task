package com.bs.knows.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.bs.knows.R;
import com.bs.knows.databinding.ActivityRegisterBinding;
import com.bs.knows.viewmodel.UserRegisterVM;
import com.bs.knows.views.InputView;

public class RegisterActivityView extends BaseActivtyView {

    private InputView mInputPhone, mInputPassword, mInputPasswordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityRegisterBinding binding=
                DataBindingUtil.setContentView(this,R.layout.activity_register);
        Intent intent=getIntent();
        UserRegisterVM userRegisterVM =new UserRegisterVM(binding,intent);
        binding.setUserRegister(userRegisterVM);

        String from=getIntent().getStringExtra("from");

        switch (from){
            case "Admin_Add_Stu":
                initNavBar(true,"添加学生",false);
                binding.RGRegister.setVisibility(View.GONE);
                binding.registerGoMain.setVisibility(View.GONE);
                break;

            case "Admin_Add_Tea":
                initNavBar(true,"添加教师",false);
                binding.RGRegister.setVisibility(View.GONE);
                binding.registerGoMain.setVisibility(View.GONE);
                break;

            case "toRegister":
                initNavBar(true,"注册",false);
                break;


        }





        binding.RGRegister.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rb_admin_re){
                    binding.notAdmin.setVisibility(View.GONE);
                }
            }
        });
    }
}
