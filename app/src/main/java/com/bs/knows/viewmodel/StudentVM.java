package com.bs.knows.viewmodel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.bs.knows.connect.InitRetrofit;
import com.bs.knows.databinding.ActivityStudentViewBinding;
import com.bs.knows.utils.StaticUtils;
import com.bs.knows.view.AddInformActivityView;
import com.bs.knows.view.InformsActivityView;
import com.bs.knows.view.ShowDetailActivityView;
import com.bs.knows.view.TaskListActivityView;

public class StudentVM {
    private ActivityStudentViewBinding binding;
    private Context context;
    private String TAG="TAG";

    public StudentVM(ActivityStudentViewBinding binding, Context context) {
        this.binding = binding;
        this.context = context;
    }

    public void TaskList(View view){

        if(StaticUtils.STU_FINISH_CHOOSE_TASK){
            Intent intent=new Intent(context, ShowDetailActivityView.class);
            intent.putExtra("from","finishChooseTask");
            context.startActivity(intent);
            StaticUtils.TASK_STATUS="选题完成";

        }else {
            Intent intent=new Intent(context, TaskListActivityView.class);
            intent.putExtra("from","beganChooseTask");
            context.startActivity(intent);
        }

    }

    //通知 showlist展示 inforUtil
    public void inform(View view){
        Intent intent=new Intent(context, InformsActivityView.class);
        context.startActivity(intent);
    }

    //提交
    public void TaskPlan(View view){
        if (StaticUtils.TASK_STATUS.equals("选题中")&&! StaticUtils.STU_FINISH_CHOOSE_TASK) {
            AlertDialog alertDialog = new AlertDialog.Builder(context)
                    .setMessage("当前正在选题中！请先选择课题")
                    .create();
            alertDialog.show();
        }else {
            Intent intent=new Intent(context,ShowDetailActivityView.class);
            intent.putExtra("from","StuTaskPlan");
            context.startActivity(intent);
        }


    }

    //留言 showlist    talksUtil
    public void talks(View view){
        Intent intent=new Intent(context, AddInformActivityView.class);
        context.startActivity(intent);

    }
}
