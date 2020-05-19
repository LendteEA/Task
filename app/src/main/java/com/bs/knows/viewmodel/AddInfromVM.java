package com.bs.knows.viewmodel;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.bs.knows.databinding.ActivityAddInformBinding;
import com.bs.knows.model.AddInformModel;
import com.bs.knows.model.UserUtilsModel;

public class AddInfromVM {
    private Context context;
    private ActivityAddInformBinding binding;

    public AddInfromVM(Context context, ActivityAddInformBinding binding) {
        this.context = context;
        this.binding = binding;
    }

    public void conformAdd(View view) {
        if (UserUtilsModel.UserIsLogin.getInstance().getRole().equals("学生")) {
            AddInformModel.conformAddMessage(context, binding);
        } else {
            AddInformModel.conformAddInform(context, binding);
        }


    }
}
