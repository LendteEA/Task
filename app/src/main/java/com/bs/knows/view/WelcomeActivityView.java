package com.bs.knows.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.bs.knows.R;
import com.bs.knows.connect.Api;
import com.bs.knows.connect.InitRetrofit;
import com.bs.knows.connect.bean.TaskStatusBean;
import com.bs.knows.model.UserUtilsModel;
import com.bs.knows.utils.StaticUtils;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @author LendteEA
 * @since 2020/2/21 13:31
 * 欢迎页面 延迟三秒 跳转到主页面
 **/
public class WelcomeActivityView extends BaseActivtyView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initWelcome();
    }

    /**
     * @author LendteEA
     * @since 2020/2/21 13:30
     * 欢迎页面初始化
     **/
    private void initWelcome() {
        final boolean isLogin = UserUtilsModel.UserIsLogin.validateUserLogin(this);
        String UserRole=UserUtilsModel.UserIsLogin.validateUserLoginRole(this);
        Timer mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {

                if (isLogin) {
                    Log.d(TAG, "run: "+UserRole);
                    if (UserRole != null) {
                        if (UserRole.equals("学生")) {
                            toStuActivity();
                        }
                        if (UserRole.equals("教师")) {
                            toTeaActivity();
                        }
                        if (UserRole.equals("管理员")) {
                            toAdmActivity();
                        }
                    } else {
                        toLoginActivity();
                    }
                } else {
                    toLoginActivity();
                }
            }
        }, 1000);

    }

    /**
     * @author LendteEA
     * @since 2020/2/21 13:29
     * 跳转到MainActivity
     **/
    private void toStuActivity() {
        Intent intent = new Intent(this, StudentActivityView.class);
        startActivity(intent);
        finish();
    }

    private void toTeaActivity() {
        Intent intent = new Intent(this, TeacherActivityView.class);
        startActivity(intent);
        finish();
    }

    private void toAdmActivity() {
        Intent intent = new Intent(this, AdminActivityView.class);
        startActivity(intent);
        finish();
    }

    /**
     * @author LendteEA
     * @since 2020/2/21 13:29
     * 跳转到LoginActivity
     **/
    private void toLoginActivity() {
        Intent intent = new Intent(this, LoginActivityView.class);
        startActivity(intent);
        finish();
    }


}
