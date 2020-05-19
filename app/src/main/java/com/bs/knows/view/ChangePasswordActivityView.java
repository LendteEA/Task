package com.bs.knows.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.bs.knows.R;
import com.bs.knows.databinding.ActivityChangePasswdBinding;
import com.bs.knows.viewmodel.UserUpdatePasswordVM;

public class ChangePasswordActivityView extends BaseActivtyView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityChangePasswdBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_change_passwd);
        UserUpdatePasswordVM updatePasswordViewModel=new UserUpdatePasswordVM(binding);
        binding.setUserupdatepassword(updatePasswordViewModel);

        initNavBar(true, "修改密码", false);
    }
}
