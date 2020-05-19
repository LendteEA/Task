package com.bs.knows.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bs.knows.R;
import com.bs.knows.databinding.ActivityCheckTaskViewBinding;
import com.bs.knows.model.InitListModel;
import com.bs.knows.model.UserUtilsModel;
import com.bs.knows.viewmodel.CheckTaskVM;

public class TaskCheckActivityView extends BaseActivtyView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCheckTaskViewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_check_task_view);
        CheckTaskVM checkTaskVM = new CheckTaskVM(binding, this);
        binding.setCheckTask(checkTaskVM);
        initNavBar(true, "课题审核", false, this);
        InitListModel.initCheckTaskListView(binding, this);

        ImageView ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(v -> {

            startActivity(new Intent(getApplicationContext(), AdminActivityView.class));

        });
    }
}
