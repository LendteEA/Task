package com.bs.knows.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.bs.knows.databinding.ActivityAdminViewBinding;
import com.bs.knows.view.ShowDetailActivityView;
import com.bs.knows.view.TaskCheckActivityView;
import com.bs.knows.view.InformsActivityView;
import com.bs.knows.view.TaskListActivityView;

public class AdminsVM {
    private ActivityAdminViewBinding binding;
    private Context context;

    public AdminsVM(ActivityAdminViewBinding binding, Context context) {
        this.binding = binding;
        this.context = context;
    }

    public void TaskList(View view){
        Intent intent=new Intent(context, TaskListActivityView.class);
        intent.putExtra("from","Admin_CheckTask");
        context.startActivity(intent);
    }

    public void inform(View view){
        Intent intent=new Intent(context, InformsActivityView.class);
        context.startActivity(intent);
    }

    public void TaskCheck(View view){
        Intent intent=new Intent(context, TaskCheckActivityView.class);
        context.startActivity(intent);
    }

    public void TaskProgress(View view){
        Intent intent=new Intent(context, ShowDetailActivityView.class);
        intent.putExtra("from","TeaTaskPlan");
        context.startActivity(intent);
    }

    public void StuData(View view){
        Intent intent=new Intent(context, TaskListActivityView.class);
        intent.putExtra("from","Admin_StuData");
        context.startActivity(intent);
    }

    public void TeacherData(View view){
        Intent intent=new Intent(context, TaskListActivityView.class);
        intent.putExtra("from","Admin_TeaData");
        context.startActivity(intent);
    }


}
