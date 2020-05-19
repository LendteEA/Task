package com.bs.knows.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.bs.knows.databinding.ActivityTeacherViewBinding;
import com.bs.knows.view.AddTaskActivity;
import com.bs.knows.view.InformsActivityView;
import com.bs.knows.view.ShowDetailActivityView;
import com.bs.knows.view.TaskListActivityView;

public class TeacherVM {
    private ActivityTeacherViewBinding binding;
    private Context context;

    public TeacherVM(ActivityTeacherViewBinding binding, Context context) {
        this.binding = binding;
        this.context = context;
    }

    public void InitList(View view){

        Intent intent=new Intent(context, TaskListActivityView.class);
        intent.putExtra("from","Tea_CheckTask");
        context.startActivity(intent);
    }

    public void addTask(View view){

        Intent intent=new Intent(context, AddTaskActivity.class);
        context.startActivity(intent);

    }
    public void TaskPlan(View view){
        Intent intent=new Intent(context, ShowDetailActivityView.class);
        intent.putExtra("from","TeaTaskPlan");
        context.startActivity(intent);

    }
    public void addInform(View view){
        Intent intent=new Intent(context, InformsActivityView.class);
        context.startActivity(intent);
    }
}
