package com.bs.knows.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.bs.knows.R;
import com.bs.knows.view.RegisterActivityView;
import com.bs.knows.databinding.ActivityLoginBinding;
import static com.bs.knows.model.UserUtilsModel.userLogin;

public class UserLoginVM {

    private ActivityLoginBinding mbinding;


    public UserLoginVM(ActivityLoginBinding binding){
        this.mbinding=binding;

    }

    public void LoginCheckUserMessage(View view){
        Context context=view.getContext();

                userLogin(context,
                        mbinding.RGLogin.getCheckedRadioButtonId(),
                        mbinding.inputPhoneLogin.getInputStr(),
                        mbinding.inputPasswordLogin.getInputStr());


        }

    public void gotoRegister(View view){
        Context context=view.getContext();
        Intent intent=new Intent(context, RegisterActivityView.class);
        intent.putExtra("from","toRegister");
        context.startActivity(intent);
    }
}
