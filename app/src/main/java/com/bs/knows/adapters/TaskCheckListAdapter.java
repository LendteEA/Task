package com.bs.knows.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bs.knows.R;
import com.bs.knows.connect.Api;
import com.bs.knows.connect.bean.CheckTaskBean;
import com.bs.knows.connect.bean.TaskPassBean;
import com.bs.knows.connect.bean.TaskUnPassBean;
import com.bs.knows.connect.InitRetrofit;
import com.bs.knows.databinding.ActivityCheckTaskViewBinding;
import com.bs.knows.model.UserUtilsModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TaskCheckListAdapter extends RecyclerView.Adapter<TaskCheckListAdapter.viewHolder> {

    private Context mContext;
    private ActivityCheckTaskViewBinding mBinding;
    private List<CheckTaskBean.DataBean> checkTaskData = new ArrayList<>();

    //    初始化Retrofit
    private Retrofit retrofit = InitRetrofit.getUserData();
    //初始化接口
    Api api = retrofit.create(Api.class);

    public TaskCheckListAdapter(ActivityCheckTaskViewBinding binding, Context context) {
        mBinding = binding;
        mContext = context;
    }

    @NonNull
    @Override
    public TaskCheckListAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskCheckListAdapter.viewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_check_task_list, parent, false));
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TaskCheckListAdapter.viewHolder holder, int position) {

        CheckTaskBean.DataBean databean = checkTaskData.get(position);
        holder.tvTitle.setText(databean.getTaskname());
        holder.tvLastUpdateTime.setText("发布者：" + databean.getTaskteachername());

        Glide.with(mContext)
                .load(R.drawable.ic_person)
                .into(holder.ivIcon)
                .onStart();

        holder.ivConform.setOnClickListener(v -> {


            api.TaskPass(databean.getTaskname()).enqueue(new Callback<TaskPassBean>() {
                @Override
                public void onResponse(Call<TaskPassBean> call, Response<TaskPassBean> response) {
                    AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                            .setTitle("审核通过")
                            .setMessage(databean.getTaskteachername() +
                                    "发布的课题：" + databean.getTaskname() +
                                    "审核已通过！")
                            .create();
                    alertDialog.show();
                }

                @Override
                public void onFailure(Call<TaskPassBean> call, Throwable t) {
                    Toast.makeText(mContext, "系统错误！请稍后重试！", Toast.LENGTH_SHORT).show();
                }
            });


        });


        // 删除题目
        holder.ivDelete.setOnClickListener(v -> {

            AlertDialog alertDialog1 = new AlertDialog.Builder(mContext)
                    .setTitle("驳回题目！")//标题
                    .setMessage(databean.getTaskteachername() +
                            "发布的课题：" + databean.getTaskname() +
                            "将会被删除！\n请再次确认该题目是否合规！")//内容
                    .setIcon(R.drawable.ic_warning_red_24dp)//图标
                    .setNegativeButton("取消", null)
                    .setPositiveButton("确认删除", (dialog, which) -> {
                        //输入原因
                        final EditText inputServer = new EditText(mContext);
                        androidx.appcompat.app.AlertDialog.Builder builder =
                                new androidx.appcompat.app.AlertDialog.Builder(mContext);
                        builder.setTitle("请输入驳回理由")
                                .setView(inputServer)
                                .setNegativeButton("取消", null);
                        builder.setPositiveButton("确定", (dialog1, which1) ->
                                api.TaskUnPass(UserUtilsModel.UserIsLogin.getInstance().getPhone(),
                                databean.getTaskname(),
                                inputServer.getText().toString(),
                                databean.getTaskteacher())
                                .enqueue(new Callback<TaskUnPassBean>() {
                                    @Override
                                    public void onResponse(Call<TaskUnPassBean> call, Response<TaskUnPassBean> response) {
                                        if (response.body().isTaskUnPass()) {
                                            AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                                                    .setTitle("课题已驳回")
                                                    .setMessage(databean.getTaskteachername() +
                                                            "发布的课题：" + databean.getTaskname() +
                                                            "已驳回并通知！")
                                                    .create();
                                            alertDialog.show();

                                        }else{
                                            Toast.makeText(mContext, "系统错误！请稍后重试！", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<TaskUnPassBean> call, Throwable t) {
                                        Toast.makeText(mContext, "系统错误！请稍后重试！", Toast.LENGTH_SHORT).show();
                                    }
                                }));
                        builder.show();


                    })

                    .create();
            alertDialog1.show();

        });

    }

    @Override
    public int getItemCount() {
        return checkTaskData.size();

    }


    /**
     * 1、获取ItemView的高度
     * 2、itemView的数量
     * 3、使用 itemViewHeight * itemViewNum = RecyclerView的高度
     */


    public void setData(CheckTaskBean checkTaskBean) {
        checkTaskData.clear();
        checkTaskData.addAll(checkTaskBean.getData());
        mBinding.checkTaskLoadingStyle.setVisibility(View.GONE);
        notifyDataSetChanged();

    }


    static class viewHolder extends RecyclerView.ViewHolder {
        View itemView;
        ImageView ivIcon, ivDelete, ivConform;
        TextView tvTitle, tvLastUpdateTime;

        viewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            ivIcon = itemView.findViewById(R.id.iv_icon_list);
            tvTitle = itemView.findViewById(R.id.tv_name_list);
            tvLastUpdateTime = itemView.findViewById(R.id.tv_author_list);
            ivDelete = itemView.findViewById(R.id.iv_delete);
            ivConform = itemView.findViewById(R.id.iv_conform);
        }
    }
}
