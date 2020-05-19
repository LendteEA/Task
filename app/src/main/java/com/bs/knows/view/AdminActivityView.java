package com.bs.knows.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.os.Bundle;

import com.bs.knows.R;
import com.bs.knows.databinding.ActivityAdminViewBinding;
import com.bs.knows.model.InitListModel;
import com.bs.knows.utils.BannerUtil;
import com.bs.knows.viewmodel.AdminsVM;

import pub.devrel.easypermissions.EasyPermissions;

public class AdminActivityView extends BaseActivtyView {

    private ActivityAdminViewBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_admin_view);
        AdminsVM adminsVM=new AdminsVM(binding,this);
        binding.setAdminVM(adminsVM);

        initView();

    }

    private void initView() {
        initNavBar(false,"管理员页面",true,this);
        getTaskStatus();

        InitListModel.initNewsListViewADM(binding,this);
        BannerUtil.InitBannerAdm(binding,this);

        EasyPermissions.requestPermissions(this,
                " 为保证您的使用体验\n 请允许Task申请文件读写权限",
                0,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getTaskStatus();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getTaskStatus();

    }
}
