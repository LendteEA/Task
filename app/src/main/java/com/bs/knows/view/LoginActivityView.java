package com.bs.knows.view;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.bs.knows.connect.Api;
import com.bs.knows.viewmodel.UserLoginVM;
import com.bs.knows.R;
import com.bs.knows.databinding.ActivityLoginBinding;

import static android.widget.Toast.LENGTH_SHORT;

public class LoginActivityView extends BaseActivtyView {
    private Api api;
    private String TAG="RES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLoginBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_login);
        UserLoginVM userLoginVM = new UserLoginVM(binding);
        binding.setUserlogin(userLoginVM);
        initNavBar(false, "登录", false);

    }

}
