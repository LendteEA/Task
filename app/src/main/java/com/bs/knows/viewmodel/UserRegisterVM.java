package com.bs.knows.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.bs.knows.view.LoginActivityView;
import com.bs.knows.databinding.ActivityRegisterBinding;
import com.bs.knows.model.UserUtilsModel;

public class UserRegisterVM extends BaseObservable {

    private ActivityRegisterBinding binding;
    private Intent intent;

    public UserRegisterVM(ActivityRegisterBinding binding,Intent intent) {
        this.intent=intent;
        this.binding = binding;
    }

    public void CheckUserRegisterMessage(View view) {
        Context context = view.getContext();

        switch (intent.getStringExtra("from")) {
            case "Admin_Add_Tea":
            case "Admin_Add_Stu":
                UserUtilsModel.AddUser(context,binding,intent);
                break;
            default:
                UserUtilsModel.signUp(context, binding);
                return;
        }

    }

    public void backtoLoginActivity(View view) {
        Context context=view.getContext();
        context.startActivity(new Intent(context, LoginActivityView.class));
    }
}
