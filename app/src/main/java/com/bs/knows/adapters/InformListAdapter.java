package com.bs.knows.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bs.knows.R;
import com.bs.knows.connect.bean.InformDetailBean;
import com.bs.knows.databinding.ActivityInformsViewBinding;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class InformListAdapter extends RecyclerView.Adapter<InformListAdapter.viewHolder> {

    private Context mContext;
    private List<InformDetailBean.DataBean> informData = new ArrayList<>();
    private ActivityInformsViewBinding mBinding;

    public InformListAdapter(ActivityInformsViewBinding binding,Context context) {
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

            InformDetailBean.DataBean databean = informData.get(position);
            holder.tvTitle.setText(databean.getInformtitle());
            holder.tvLastUpdateTime.setText(databean.getRole()+"：" + databean.getNumber());

            Glide.with(mContext)
                    .load(R.drawable.ic_person)
                    .into(holder.ivIcon)
                    .onStart();

            holder.itemView.setOnClickListener(v -> {

                AlertDialog alertDialog1 = new AlertDialog.Builder(mContext)
                        .setTitle(databean.getInformtitle())//标题
                        .setMessage(databean.getInformdetail())//内容
                        .setIcon(R.drawable.ic_insert_comment)//图标
                        .setPositiveButton("确认已读", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mContext,"已读通知",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                alertDialog1.show();

            });

    }

    @Override
    public int getItemCount() {
            return informData.size();

    }




    /**
     * 1、获取ItemView的高度
     * 2、itemView的数量
     * 3、使用 itemViewHeight * itemViewNum = RecyclerView的高度
     */


    public void setData(InformDetailBean informDetailBean) {
        informData.clear();
        informData.addAll(informDetailBean.getData());
        notifyDataSetChanged();
        mBinding.InformListLoadingStyle.setVisibility(View.GONE);
    }




    static class viewHolder extends RecyclerView.ViewHolder {
        View itemView;
        ImageView ivIcon,ivDeleteList;
        TextView tvTitle, tvLastUpdateTime;

        viewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            ivIcon = itemView.findViewById(R.id.iv_icon_list);
            tvTitle = itemView.findViewById(R.id.tv_name_list);
            tvLastUpdateTime = itemView.findViewById(R.id.tv_author_list);
            ivDeleteList=itemView.findViewById(R.id.iv_stu_conform);

            ivDeleteList.setVisibility(View.GONE);
        }
    }
}
