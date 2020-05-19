package com.bs.knows.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bs.knows.R;

import com.bs.knows.connect.bean.CheckUploadTaskBean;
import com.bs.knows.databinding.ActivityShowDetailBinding;
import com.bs.knows.view.ShowDetailActivityView;

import java.util.ArrayList;
import java.util.List;


public class ShowStuUploadFileAdapter extends RecyclerView.Adapter<ShowStuUploadFileAdapter.viewHolder>{
    private Context mContext;
    private String TAG="TAG";


    private List<CheckUploadTaskBean.DataBean> data = new ArrayList<>();

    public ShowStuUploadFileAdapter(Context context) {
        mContext = context;

    }

    @NonNull
    @Override
    public ShowStuUploadFileAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShowStuUploadFileAdapter.viewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_uploadfile_list, parent, false));
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ShowStuUploadFileAdapter.viewHolder holder, int position) {

        CheckUploadTaskBean.DataBean databean = data.get(position);
        holder.tvTaskTitle.setText(databean.getTaskname());
        holder.tvTaskStatus.setText(databean.getStatus());


        holder.itemView.setOnClickListener(v -> {
            Intent intent=new Intent(mContext,ShowDetailActivityView.class);
            intent.putExtra("fromUploadFileTask",databean.getTaskname());
            intent.putExtra("from","checkUploadTaskDetail");
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


    public void setData(CheckUploadTaskBean checkUploadTaskBean) {
        data.clear();
        data.addAll(checkUploadTaskBean.getData());
        notifyDataSetChanged();
    }


    static class viewHolder extends RecyclerView.ViewHolder {
        View itemView;
        TextView tvTaskTitle, tvTaskStatus;

        viewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;

            tvTaskTitle = itemView.findViewById(R.id.tv_upload_taskName);
            tvTaskStatus = itemView.findViewById(R.id.tv_upload_studentName);

        }

    }


}
