package com.bs.knows.view;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.bs.knows.R;
import com.bs.knows.databinding.ActivityAddTaskBinding;
import com.bs.knows.viewmodel.AddTaskVM;

public class AddTaskActivity extends BaseActivtyView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAddTaskBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_add_task);
        AddTaskVM addTaskVM=new AddTaskVM(binding,this);
        binding.setAddTask(addTaskVM);
        initNavBar(true,"发布课题",false);
    }
}
