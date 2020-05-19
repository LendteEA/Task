package com.bs.knows.viewmodel;

import android.content.Context;
import android.view.View;

import com.bs.knows.model.UserUtilsModel;

public class AboutVM {


    public void userLogout(View view){
        Context context=view.getContext();
        UserUtilsModel.logout(context);
    }
}
