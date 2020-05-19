package com.bs.knows.model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.widget.Toast;

import com.bs.knows.connect.Api;
import com.bs.knows.connect.InitRetrofit;
import com.bs.knows.connect.bean.AddTaskBean;
import com.bs.knows.databinding.ActivityAddTaskBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddTaskModel {

    public static void confromAddTask(ActivityAddTaskBinding binding, Context context) {

        if (!UserUtilsModel.checkInput(context,
                binding.inputTaskTitle.getInputStr(),
                binding.inputTaskAbstract.getText().toString(),
                binding.inputTaskMajor.getInputStr(),
                binding.inputTaskCollege.getInputStr())) {
            return;
        }


        if (TextUtils.isEmpty(binding.inputTaskTeacher.getInputStr())) {

            if(UserUtilsModel.UserIsLogin.getInstance().getRole().equals("管理员")){
                Toast.makeText(context,"请输入指导教师！",Toast.LENGTH_SHORT).show();
            }else {
                AlertDialog alertDialog = new AlertDialog.Builder(context)
                        .setTitle("发布教师确定")
                        .setMessage("将以当前登录账号信息确定该课题指导教师!\n请再次确认！")
                        .setNegativeButton("确定", (dialog, which) -> {
                            addTaskConnectServers(binding,context, UserUtilsModel.UserIsLogin.getInstance().getPhone());
                        })
                        .setPositiveButton("取消", null)
                        .create();
                alertDialog.show();
            }

        }else {
            addTaskConnectServers(binding,context,null);
        }


    }


    private static void addTaskConnectServers(ActivityAddTaskBinding binding,Context context,String teachername){

        if(teachername == null){
            teachername=binding.inputTaskTeacher.getInputStr();
        }
        Retrofit retrofit = InitRetrofit.getUserData();
        Api api = retrofit.create(Api.class);
        api.AddTasks(binding.inputTaskTitle.getInputStr(),
                binding.inputTaskAbstract.getText().toString(),
                teachername,
                binding.inputTaskMajor.getInputStr(),
                binding.inputTaskCollege.getInputStr())
                .enqueue(new Callback<AddTaskBean>() {
                    @Override
                    public void onResponse(Call<AddTaskBean> call, Response<AddTaskBean> response) {
                        assert response.body() != null;
                        if (response.body().isAddTaskSuccess()) {
                            Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<AddTaskBean> call, Throwable t) {
                        Toast.makeText(context, "系统错误！请稍后重试！", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}