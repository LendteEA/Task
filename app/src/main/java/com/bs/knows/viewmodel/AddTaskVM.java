package com.bs.knows.viewmodel;

import android.content.Context;
import android.view.View;

import com.bs.knows.databinding.ActivityAddTaskBinding;
import com.bs.knows.model.AddTaskModel;


public class AddTaskVM {

    private ActivityAddTaskBinding binding;
    private Context context;

    public AddTaskVM(ActivityAddTaskBinding binding, Context context) {
        this.binding = binding;
        this.context = context;
    }

    public void conformAdd(View view){
        AddTaskModel.confromAddTask(binding,context);
    }
}
