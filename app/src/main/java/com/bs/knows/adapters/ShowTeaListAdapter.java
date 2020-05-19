package com.bs.knows.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bs.knows.R;
import com.bs.knows.connect.bean.TaskListBean;
import com.bs.knows.connect.bean.UserDetailBean;
import com.bs.knows.databinding.ActivityShowListBinding;
import com.bs.knows.model.UserUtilsModel;
import com.bs.knows.utils.StaticUtils;
import com.bs.knows.view.ShowDetailActivityView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class ShowTeaListAdapter extends RecyclerView.Adapter<ShowTeaListAdapter.viewHolder> {

    private Context mContext;

    private List<UserDetailBean.DataBean> data = new ArrayList<>();
private ActivityShowListBinding mBinding;
    public ShowTeaListAdapter(ActivityShowListBinding binding,Context context) {
        mBinding=binding;
        mContext = context;
    }

    @NonNull
    @Override
    public ShowTeaListAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShowTeaListAdapter.viewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_choosed_stu_list, parent, false));
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ShowTeaListAdapter.viewHolder holder, int position) {
        UserDetailBean.DataBean databean = data.get(position);
        holder.tvUserName.setText(databean.getTeachername());
        holder.tvUserMager.setText( databean.getMajor()+"专业");
        holder.tvUserCollege.setText(databean.getCollege());

        Glide.with(mContext)
                .load(R.drawable.ic_person)
                .into(holder.ivIcon)
                .onStart();


        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, ShowDetailActivityView.class);
            intent.putExtra("from","TeacherDetail");
            intent.putExtra("teaID",databean.getTeachernumber());
            mContext.startActivity(intent);
        });


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


    public void setData(UserDetailBean userDetailBean) {
        data.clear();
        data.addAll(userDetailBean.getData());
        mBinding.TaskListLoadingStyle.setVisibility(View.GONE);
        notifyDataSetChanged();

    }


    static class viewHolder extends RecyclerView.ViewHolder {
        View itemView;
        ImageView ivIcon, ivConformList,ivDelete;
        TextView tvUserName, tvUserMager,tvUserCollege;

        viewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            ivIcon = itemView.findViewById(R.id.iv_icon_list);
            tvUserName = itemView.findViewById(R.id.tv_choose_stu_name);
            tvUserMager = itemView.findViewById(R.id.tv_choose_stu_major_list);
            tvUserCollege = itemView.findViewById(R.id.tv_choose_stu_college_list);
            ivConformList = itemView.findViewById(R.id.iv_choose_stu_conform);
            ivDelete = itemView.findViewById(R.id.iv_choose_stu_delete);
            ivDelete.setVisibility(View.GONE);
            ivConformList.setVisibility(View.GONE);

        }
    }
}
