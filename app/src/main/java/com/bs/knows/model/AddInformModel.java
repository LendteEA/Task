package com.bs.knows.model;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.bs.knows.connect.Api;
import com.bs.knows.connect.InitRetrofit;
import com.bs.knows.connect.bean.AddInformBean;
import com.bs.knows.connect.bean.AddMessageBean;
import com.bs.knows.databinding.ActivityAddInformBinding;
import com.bs.knows.view.AdminActivityView;
import com.bs.knows.view.InformsActivityView;
import com.bs.knows.view.StudentActivityView;
import com.bs.knows.view.TeacherActivityView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddInformModel {

    public static void InitInputView(Context context,ActivityAddInformBinding binding){

        if(UserUtilsModel.UserIsLogin.getInstance().getRole().equals("学生")){
            binding.LyInputInform.setVisibility(View.GONE);

            binding.confirmAddInformButton.setText("确认发布留言");

        }else {
            binding.LyInputMessage.setVisibility(View.GONE);
            binding.confirmAddInformButton.setText("确认发布通知");
        }

    }

    //=======================================  教师管理员添加通知  =====================================================

    public static void conformAddInform(Context context, ActivityAddInformBinding binding){

        Retrofit retrofit= InitRetrofit.getUserData();
        Api api=retrofit.create(Api.class);

        api.AddInfrom(UserUtilsModel.UserIsLogin.getInstance().getPhone(),
                UserUtilsModel.UserIsLogin.getInstance().getRole(),
                binding.inputTitle.getInputStr(),
                binding.inputDetail.getText().toString(),
                binding.inputMajor.getInputStr()).enqueue(new Callback<AddInformBean>() {
            @Override
            public void onResponse(Call<AddInformBean> call, Response<AddInformBean> response) {
                if(response.body().isAddInfrom()){
                    Toast.makeText(context,"添加通知成功",Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(context, InformsActivityView.class);
                        context.startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<AddInformBean> call, Throwable t) {
                Toast.makeText(context,"系统错误！请稍后重试",Toast.LENGTH_SHORT).show();
            }
        });

    }


    //=======================================  学生添加留言  =====================================================

    public static void conformAddMessage(Context context, ActivityAddInformBinding binding){

        Retrofit retrofit= InitRetrofit.getUserData();
        Api api=retrofit.create(Api.class);
        api.AddMessage(UserUtilsModel.UserIsLogin.getInstance().getPhone(),
                binding.inputMessage.getText().toString())
                .enqueue(new Callback<AddMessageBean>() {
            @Override
            public void onResponse(Call<AddMessageBean> call, Response<AddMessageBean> response) {
                if(response.body().isAddMessage()){
                    Toast.makeText(context,"添加留言成功",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(context, StudentActivityView.class);
                    context.startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<AddMessageBean> call, Throwable t) {
                Toast.makeText(context,"系统错误！请稍后重试",Toast.LENGTH_SHORT).show();
            }
        });

    }


}
