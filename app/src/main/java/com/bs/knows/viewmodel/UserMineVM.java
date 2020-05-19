package com.bs.knows.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.databinding.BaseObservable;

import com.bs.knows.view.AboutActivityView;
import com.bs.knows.view.ChangePasswordActivityView;;
import com.bs.knows.model.UserUtilsModel;

public class UserMineVM extends BaseObservable {

    public void gotoUpdatePassword(View view){
        Context context=view.getContext();
        context.startActivity(new Intent(context, ChangePasswordActivityView.class));
    }

    public void gotoAboutView(View view){
        Context context=view.getContext();
        context.startActivity(new Intent(context, AboutActivityView.class));
    }

    public void userLogout(View view){
        Context context=view.getContext();
        UserUtilsModel.logout(context);
    }
}
