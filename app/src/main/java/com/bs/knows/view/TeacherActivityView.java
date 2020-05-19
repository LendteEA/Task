package com.bs.knows.view;

import android.Manifest;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.bs.knows.R;
import com.bs.knows.databinding.ActivityTeacherViewBinding;
import com.bs.knows.model.InitListModel;
import com.bs.knows.utils.BannerUtil;
import com.bs.knows.viewmodel.TeacherVM;

import pub.devrel.easypermissions.EasyPermissions;

public class TeacherActivityView extends BaseActivtyView {

    private ActivityTeacherViewBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_teacher_view);
        TeacherVM teacherVM=new TeacherVM(binding,this);
        binding.setTeacherActivity(teacherVM);

        initView();

    }

    private void initView() {
        initNavBar(false, "教师选题系统", true,this);
        getTaskStatus();

        InitListModel.initNewsListViewTEA(binding,this);

        BannerUtil.InitBannerTea(binding,this);
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
}