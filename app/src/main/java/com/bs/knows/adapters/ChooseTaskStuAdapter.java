package com.bs.knows.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
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
import com.bs.knows.connect.InitRetrofit;
import com.bs.knows.connect.bean.ChooseTaskStuBean;
import com.bs.knows.connect.bean.ChooseTaskStuConformBean;
import com.bs.knows.connect.bean.ChooseTaskStuDeleteBean;
import com.bs.knows.databinding.ActivityShowDetailBinding;
import com.bs.knows.model.ShowDetailModel;
import com.bs.knows.model.UserUtilsModel;
import com.bs.knows.utils.StaticUtils;
import com.bs.knows.view.ShowDetailActivityView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ChooseTaskStuAdapter extends RecyclerView.Adapter<ChooseTaskStuAdapter.viewHolder> {

    private String mTaskName;
    private Context mContext;


    private List<ChooseTaskStuBean.DataBean> data = new ArrayList<>();
    Retrofit retrofit = InitRetrofit.getUserData();
    Api api = retrofit.create(Api.class);

    public ChooseTaskStuAdapter(Context context, ActivityShowDetailBinding binding, Intent intent) {
        mContext = context;
        mTaskName = intent.getStringExtra("taskname");

    }

    @NonNull
    @Override
    public ChooseTaskStuAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChooseTaskStuAdapter.viewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_choosed_stu_list, parent, false));
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ChooseTaskStuAdapter.viewHolder holder, int position) {

        ChooseTaskStuBean.DataBean databean = data.get(position);


        holder.tvStu.setText(databean.getStudentname());
        holder.tvMajor.setText(databean.getMajor() + "专业");
        holder.tvCollege.setText(databean.getCollege());

        Glide.with(mContext)
                .load(R.drawable.ic_person)
                .into(holder.ivIcon)
                .onStart();

        holder.itemView.setOnClickListener(v -> {


            Intent intent = new Intent(mContext, ShowDetailActivityView.class);
            intent.putExtra("stuID", databean.getStudentnumber());
            intent.putExtra("from", "chooseTaskStu");
            mContext.startActivity(intent);


        });

        holder.ivConform.setOnClickListener(v -> {
            AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                    .setTitle("确认学生！")
                    .setMessage("将确定该同学：" + databean.getStudentname() + "为该选题：" + mTaskName)
                    .setNegativeButton("确定", (dialog, which) ->
                            api.chooseTaskStuConformBean(mTaskName, databean.getStudentnumber())
                                    .enqueue(new Callback<ChooseTaskStuConformBean>() {
                                        @Override
                                        public void onResponse(Call<ChooseTaskStuConformBean> call, Response<ChooseTaskStuConformBean> response) {
                                            if (response.body().isConformTaskStu()) {
                                                Toast.makeText(mContext, "已确认！", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<ChooseTaskStuConformBean> call, Throwable t) {
                                            Toast.makeText(mContext, "系统错误！请稍后重试！", Toast.LENGTH_SHORT).show();
                                        }
                                    })).setPositiveButton("取消", null)
                    .create();
            alertDialog.show();


        });

        holder.ivDeleteList.setOnClickListener(v -> {

            AlertDialog alertDialog1 = new AlertDialog.Builder(mContext)
                    .setTitle("驳回该学生选择！")//标题
                    .setMessage("将取消该学生：" + databean.getStudentname() + "选择该课题：" + mTaskName + "\n请再次确认！")//内容
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
                        builder.setPositiveButton("确定", (dialog1, which1) -> {
                                    api.chooseTaskStuDeleteBean(mTaskName, inputServer.getText().toString(), databean.getStudentnumber())
                                            .enqueue(new Callback<ChooseTaskStuDeleteBean>() {
                                                @Override
                                                public void onResponse(Call<ChooseTaskStuDeleteBean> call, Response<ChooseTaskStuDeleteBean> response) {
                                                    if (response.body().isDeleteTaskStu()) {
                                                        AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                                                                .setTitle("成功")
                                                                .setMessage(
                                                                        "已取消该学生选题，并通知原因！")
                                                                .create();
                                                        alertDialog.show();
                                                    }else {
                                                        Toast.makeText(mContext, "系统错误！请稍后重试！", Toast.LENGTH_SHORT).show();
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<ChooseTaskStuDeleteBean> call, Throwable t) {
                                                    Toast.makeText(mContext, "系统错误！请稍后重试！", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                }
                        );
                        builder.show();
                    })
                    .create();
            alertDialog1.show();

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


    public void setData(ChooseTaskStuBean chooseTaskStuBean) {
        data.clear();
        data.addAll(chooseTaskStuBean.getData());
        notifyDataSetChanged();
    }


    static class viewHolder extends RecyclerView.ViewHolder {
        View itemView;
        ImageView ivIcon, ivDeleteList, ivConform;
        TextView tvCollege, tvMajor, tvStu;

        viewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            ivIcon = itemView.findViewById(R.id.iv_icon_list);
            tvCollege = itemView.findViewById(R.id.tv_choose_stu_college_list);
            tvMajor = itemView.findViewById(R.id.tv_choose_stu_major_list);
            tvStu = itemView.findViewById(R.id.tv_choose_stu_name);
            ivConform = itemView.findViewById(R.id.iv_choose_stu_conform);
            ivDeleteList = itemView.findViewById(R.id.iv_choose_stu_delete);


            if (UserUtilsModel.UserIsLogin.getInstance().getRole().equals("学生")||StaticUtils.STU_FINISH_CHOOSE_TASK) {
                ivConform.setVisibility(View.GONE);
                ivDeleteList.setVisibility(View.GONE);
            }


        }


    }
}
