package com.bs.knows.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bs.knows.R;
import com.bs.knows.adapters.InformListAdapter;
import com.bs.knows.connect.Api;
import com.bs.knows.connect.InitRetrofit;
import com.bs.knows.connect.bean.TaskStatusBean;
import com.bs.knows.model.UserUtilsModel;
import com.bs.knows.utils.StaticUtils;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * 作为整个Activity的父类 描述所有Activity的共性
 */

@SuppressLint("Registered")
public class BaseActivtyView extends Activity implements EasyPermissions.PermissionCallbacks {

    public String TAG = "TAG";

    /**
     * 初始化NavBar
     *
     * @param isShowBack 是否显示返回键
     * @param title      页面名称
     * @param isShowMine 是否显示我的
     */
    protected void initNavBar(boolean isShowBack, String title, boolean isShowMine) {

        ImageView ivBack = findViewById(R.id.iv_back);
        ImageView ivMine = findViewById(R.id.iv_mine);
        ImageView ivInform = findViewById(R.id.iv_addinform);
        TextView tvTitle = findViewById(R.id.tv_title);


        ivBack.setVisibility(isShowBack ? View.VISIBLE : View.GONE);
        ivMine.setVisibility(isShowMine ? View.VISIBLE : View.GONE);
        ivInform.setVisibility(View.GONE);
        tvTitle.setText(title);

        ivBack.setOnClickListener(v -> onBackPressed());

    }

    protected void initNavBar(boolean isShowBack, String title, boolean isShowMine, Context context) {

        ImageView ivBack = findViewById(R.id.iv_back);
        ImageView ivMine = findViewById(R.id.iv_mine);
        ImageView ivInform = findViewById(R.id.iv_addinform);
        TextView tvTitle = findViewById(R.id.tv_title);

        ivBack.setVisibility(isShowBack ? View.VISIBLE : View.GONE);
        ivMine.setVisibility(isShowMine ? View.VISIBLE : View.GONE);
        ivInform.setVisibility(View.GONE);
        tvTitle.setText(title);

        ivBack.setOnClickListener(v -> onBackPressed());
        ivMine.setOnClickListener(v -> {
            Intent intent = new Intent(context, MineActivityView.class);
            context.startActivity(intent);
        });

    }

    protected void initNavBar(boolean isShowBack, String title, boolean isShowMine, boolean isShowAddInform, Context context) {

        ImageView ivBack = findViewById(R.id.iv_back);
        ImageView ivMine = findViewById(R.id.iv_mine);
        ImageView ivInform = findViewById(R.id.iv_addinform);
        TextView tvTitle = findViewById(R.id.tv_title);


        ivBack.setVisibility(isShowBack ? View.VISIBLE : View.GONE);
        ivMine.setVisibility(isShowMine ? View.VISIBLE : View.GONE);
        tvTitle.setText(title);
        if (UserUtilsModel.UserIsLogin.getInstance().getRole().equals("学生")) {
            ivInform.setVisibility(View.GONE);
        }

        ivBack.setOnClickListener(v -> onBackPressed());
        ivInform.setOnClickListener(v -> {
            Intent intent = new Intent(context, AddInformActivityView.class);
            context.startActivity(intent);
        });


    }


    protected void initNavBarAdmin(Context context, String title, Intent intent) {

        ImageView ivBack = findViewById(R.id.iv_back);
        ImageView ivMine = findViewById(R.id.iv_mine);
        ImageView ivInform = findViewById(R.id.iv_addinform);
        TextView tvTitle = findViewById(R.id.tv_title);

        ivMine.setVisibility(View.GONE);
        tvTitle.setText(title);


        ivBack.setOnClickListener(v -> onBackPressed());


        ivInform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (intent.getStringExtra("from")) {
                    case "Admin_StuData":
                        Intent intentStu = new Intent(context, RegisterActivityView.class);
                        intentStu.putExtra("from", "Admin_Add_Stu");
                        startActivity(intentStu);
                        break;

                    case "Admin_TeaData":
                        Intent intentTea = new Intent(context, RegisterActivityView.class);
                        intentTea.putExtra("from", "Admin_Add_Tea");
                        startActivity(intentTea);
                        break;

                    case "Tea_CheckTask":
                    case "Admin_CheckTask":
                        Intent intentTask = new Intent(context, AddTaskActivity.class);
                        intentTask.putExtra("from", "Admin_Add_Task");
                        startActivity(intentTask);
                        break;

                    default:
                        return;
                }
            }
        });


    }

    protected void getTaskStatus() {
        Retrofit retrofit = InitRetrofit.getUserData();
        Api api = retrofit.create(Api.class);

        if(!StaticUtils.STU_FINISH_CHOOSE_TASK){
            api.getStatus().enqueue(new Callback<TaskStatusBean>() {
                @Override
                public void onResponse(Call<TaskStatusBean> call, Response<TaskStatusBean> response) {
                    if (!response.body().isError()) {
                        StaticUtils.TASK_STATUS = response.body().getStatus();
                    }
                }

                @Override
                public void onFailure(Call<TaskStatusBean> call, Throwable t) {

                }
            });
        }



    }

    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
//        switch (requestCode){
//            case 0:
//                Toast.makeText(this, "已获取相机权限", Toast.LENGTH_SHORT).show();
//                break;
//            case 1:
//                Toast.makeText(this, "已获取文件读取权限", Toast.LENGTH_SHORT).show();
//                break;
//            case 2:
//                Toast.makeText(this, "已获取文件写入权限", Toast.LENGTH_SHORT).show();
//                break;
//        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        //处理权限名字字符串
        StringBuilder sb = new StringBuilder();
        for (String str : perms) {
            sb.append(str);
            sb.append("\n");
        }
        sb.replace(sb.length() - 2, sb.length(), "");

        switch (requestCode) {
            case 0:
                Toast.makeText(this, "已拒绝Camera权限" + perms.get(0), Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, "已拒绝文件读取权限", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "已拒绝文件写入权限", Toast.LENGTH_SHORT).show();
                break;
        }
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            Toast.makeText(this, "已拒绝权限" + sb + "并不再询问", Toast.LENGTH_SHORT).show();
            new AppSettingsDialog
                    .Builder(this)
                    .setRationale("此功能需要" + sb + "权限，否则无法正常使用，是否打开设置")
                    .setPositiveButton("是")
                    .setNegativeButton("否")
                    .build()
                    .show();
        }
    }


}