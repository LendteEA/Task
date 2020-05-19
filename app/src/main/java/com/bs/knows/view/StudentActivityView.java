package com.bs.knows.view;

import android.Manifest;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.bs.knows.R;
import com.bs.knows.databinding.ActivityStudentViewBinding;
import com.bs.knows.model.InitListModel;
import com.bs.knows.model.StuActivityModel;
import com.bs.knows.utils.BannerUtil;
import com.bs.knows.viewmodel.StudentVM;

import pub.devrel.easypermissions.EasyPermissions;

public class StudentActivityView extends BaseActivtyView {
private ActivityStudentViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_student_view);
        StudentVM studentVM=new StudentVM(binding,this);
        binding.setStudentActivity(studentVM);

        initView();

    }

    private void initView() {
        initNavBar(false, "学生选题系统", true,this);
        getTaskStatus();

        BannerUtil.InitBannerStu(binding,this);
        StuActivityModel.InitStuMainActivity(binding,this);

        InitListModel.initNewsListViewSTU(binding,this);

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