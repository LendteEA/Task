package com.bs.knows.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bs.knows.R;
import com.bs.knows.connect.bean.TaskListBean;
import com.bs.knows.databinding.ActivityShowListBinding;
import com.bs.knows.model.UserUtilsModel;
import com.bs.knows.utils.StaticUtils;
import com.bs.knows.view.ShowDetailActivityView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.viewHolder> {

    private Context mContext;
    private String TAG="TAG";
    private List<TaskListBean.DataBean> data = new ArrayList<>();
    private ActivityShowListBinding mBinding;

    public TaskListAdapter(ActivityShowListBinding binding,Context context) {
        mBinding=binding;
        mContext = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_history_list, parent, false));
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        TaskListBean.DataBean databean = data.get(position);
        holder.tvTitle.setText(databean.getTaskname());
        holder.tvLastUpdateTime.setText("指导教师：" + databean.getTaskteachername());

        Glide.with(mContext)
                .load(R.drawable.ic_person)
                .into(holder.ivIcon)
                .onStart();


        holder.itemView.setOnClickListener(v -> {
            if (UserUtilsModel.UserIsLogin.getInstance().getRole().equals("学生") && databean.getChosed().equals("3")) {
                AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                        .setTitle("人数已满")
                        .setMessage("该课题已达到最大选课人数，您将无法选择该课题。\n是否继续查看?")
                        .setNegativeButton("确定", (dialog, which) -> {
                            toDetailActivity(databean, "showTaskDetail_count3");
                        })
                        .setPositiveButton("取消", null)
                        .create();
                alertDialog.show();
                Log.d(TAG, "onBindViewHolder: showTaskDetail_count3"+databean.getTaskname());
            } else {
                toDetailActivity(databean, "showTaskDetail");
            }
        });


    }

    private void toDetailActivity(TaskListBean.DataBean dataBean, String intentData) {
        Intent intent = new Intent(mContext, ShowDetailActivityView.class);
        intent.putExtra("taskname", dataBean.getTaskname());
        intent.putExtra("from", intentData);
        mContext.startActivity(intent);

    }

    @Override
    public int getItemCount() {
        return data.size();

    }


    /**
     * 1、获取ItemView的高度
     * 2、itemView的数量
     * 3、使用 itemViewHeight * itemViewNum = RecyclerView的高度
     */


    public void setData(TaskListBean taskListBean) {
        data.clear();
        data.addAll(taskListBean.getData());
        notifyDataSetChanged();
        mBinding.TaskListLoadingStyle.setVisibility(View.GONE);

        if (!UserUtilsModel.UserIsLogin.getInstance().getRole().equals("管理员")) {
            if (!StaticUtils.TASK_STATUS.equals("选题中")) {
                AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                        .setTitle("已过选题阶段！")
                        .setMessage("现已过选题阶段！将只展示题目列表！")
                        .create();
                alertDialog.show();
            }
        }


    }


    static class viewHolder extends RecyclerView.ViewHolder {
        View itemView;
        ImageView ivIcon, ivConformList;
        TextView tvTitle, tvLastUpdateTime;

        viewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            ivIcon = itemView.findViewById(R.id.iv_icon_list);
            tvTitle = itemView.findViewById(R.id.tv_name_list);
            tvLastUpdateTime = itemView.findViewById(R.id.tv_author_list);
            ivConformList = itemView.findViewById(R.id.iv_stu_conform);

            if (UserUtilsModel.UserIsLogin.getInstance().getRole().equals("教师") || UserUtilsModel.UserIsLogin.getInstance().getRole().equals("管理员") || !StaticUtils.TASK_STATUS.equals("选题中")) {
                ivConformList.setVisibility(View.GONE);
            }

        }
    }
}
