package com.bs.knows.model;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

import com.bs.knows.connect.Api;
import com.bs.knows.connect.InitRetrofit;
import com.bs.knows.connect.bean.UserDetailBean;
import com.bs.knows.databinding.ActivityStudentViewBinding;
import com.bs.knows.utils.StaticUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class StuActivityModel {

    public static void InitStuMainActivity(ActivityStudentViewBinding binding, Context context){

        Retrofit retrofit= InitRetrofit.getUserData();
        Api api=retrofit.create(Api.class);

        api.UserDetailShow(UserUtilsModel.UserIsLogin.getInstance().getPhone(), UserUtilsModel.UserIsLogin.getInstance().getRole())
                .enqueue(new Callback<UserDetailBean>() {
                    @Override
                    public void onResponse(Call<UserDetailBean> call, Response<UserDetailBean> response) {
                        if(!response.body().getData().get(0).getConformTask().isEmpty()&&response.body().getData().get(0).getConformTask().equals("1")){
                            binding.button1.setText("已选课题情况");
                            StaticUtils.STU_FINISH_CHOOSE_TASK=true;
                            StaticUtils.STU__CHOOSE_TASK_NAME=response.body().getData().get(0).getTask();
                            if(!StaticUtils.STU_READ_CHOOSE_TASK){
                                AlertDialog alertDialog=new AlertDialog.Builder(context)
                                        .setTitle("选题成功！")
                                        .setMessage("您的选题："+StaticUtils.STU__CHOOSE_TASK_NAME+
                                                " 已通过指导教师审核！\n您已选题成功！无需选择课题！将不再显示选题列表！")
                                        .create();
                                alertDialog.show();
                                StaticUtils.STU_READ_CHOOSE_TASK=true;
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<UserDetailBean> call, Throwable t) {
                        Toast.makeText(context,"页面初始化失败！数据更新可能会有延迟！",Toast.LENGTH_SHORT).show();
                    }
                });



    }
}
