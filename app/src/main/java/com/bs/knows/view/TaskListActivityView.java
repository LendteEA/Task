package com.bs.knows.view;

;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.databinding.DataBindingUtil;

import com.bs.knows.R;
import com.bs.knows.databinding.ActivityShowListBinding;
import com.bs.knows.model.InitListModel;
import com.bs.knows.model.UserUtilsModel;
import com.bs.knows.viewmodel.ShowListVM;

public class TaskListActivityView extends BaseActivtyView {

    private Intent intent;
    private ActivityShowListBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_list);
        ShowListVM showListVM = new ShowListVM(binding, this);
        binding.setShowlistActivity(showListVM);

        intent = getIntent();
        initBase();

    }

    private void initBase() {

        switch (intent.getStringExtra("from")) {
            case "Admin_StuData":
                initNavBarAdmin(this,"学生信息", intent);
                InitListModel.initStuListView(binding, this);
                break;

            case "Admin_TeaData":
                initNavBarAdmin(this,"教师信息", intent);
                InitListModel.initTeaListView(binding, this);
                break;

            case "Tea_CheckTask":
            case "Admin_CheckTask":
                initNavBarAdmin(this,"课题信息", intent);
                InitListModel.initTaskListView(binding, this);
                break;

            default:
                initNavBar(true,"课题选择",false);
                InitListModel.initTaskListView(binding, this);
                return;
        }


        ImageView ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(v -> {
            switch (UserUtilsModel.UserIsLogin.getInstance().getRole()) {
                case "学生":
                    startActivity(new Intent(getApplicationContext(), StudentActivityView.class));
                    break;
                case "教师":
                    startActivity(new Intent(getApplicationContext(), TeacherActivityView.class));
                    break;
                case "管理员":
                    startActivity(new Intent(getApplicationContext(), AdminActivityView.class));
                    break;
            }

        });


    }


}
