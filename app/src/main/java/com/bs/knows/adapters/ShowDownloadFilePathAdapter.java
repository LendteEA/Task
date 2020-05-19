package com.bs.knows.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bs.knows.R;
import com.bs.knows.databinding.ActivityShowDetailBinding;
import com.bs.knows.connect.DownloadFiles.DownloadFile;
import com.bs.knows.connect.ConnectListener;
import com.bs.knows.utils.StaticUtils;

import java.io.File;


public class ShowDownloadFilePathAdapter extends RecyclerView.Adapter<ShowDownloadFilePathAdapter.viewHolder> {
    private Context mContext;
    private String TAG = "TAG";

    private ActivityShowDetailBinding mBinding;
    private String data[];

    public ShowDownloadFilePathAdapter(Context context, ActivityShowDetailBinding binding) {
        mContext = context;
        mBinding = binding;

    }

    @NonNull
    @Override
    public ShowDownloadFilePathAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShowDownloadFilePathAdapter.viewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_uploadfile_list, parent, false));
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ShowDownloadFilePathAdapter.viewHolder holder, int position) {

        String downloadFilePath = data[position];
        String status[] = downloadFilePath.split("/");

        holder.tvTaskTitle.setText(status[4]);
        holder.tvTaskStatus.setText(status[3] + "      " + status[5]);

        String DownloadPath = StaticUtils.FILE_PATH + status[4] + "/" + status[5];
        holder.itemView.setOnClickListener(v -> {
            File file = new File(DownloadPath);
            if (!file.exists()) {
                Log.d(TAG, "onBindViewHolder: "+DownloadPath);
                Toast.makeText(mContext, "开始下载", Toast.LENGTH_SHORT).show();
                DownloadFile.Downloads(mBinding, mContext, StaticUtils.DOWNLOAD_BASE_URL + downloadFilePath, DownloadPath, new ConnectListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onProgress(int progress) {
                        Looper.prepare();
                        Toast.makeText(mContext, "已下载：" + progress + " %", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }

                    @Override
                    public void onFinish(String path) {
                        Looper.prepare();
                        Toast.makeText(mContext, "下载完成，文件已保存在：" + path, Toast.LENGTH_SHORT).show();
                        Looper.loop();

                        Log.d(TAG, "onFinish: " + path);

                    }

                    @Override
                    public void onUploadFinish() {
                    }

                    @Override
                    public void onFail(String errorInfo) {
                        Log.d(TAG, "onFail: "+errorInfo);
                        Looper.prepare();
                        Toast.makeText(mContext, "下载失败！", Toast.LENGTH_SHORT).show();
                        Looper.loop();


                    }
                });


            } else {
                Toast.makeText(mContext, "文件已保存在：" + DownloadPath, Toast.LENGTH_SHORT).show();
            }

        });


    }

    @Override
    public int getItemCount() {
        return data.length;

    }


    /**
     * 1、获取ItemView的高度
     * 2、itemView的数量
     * 3、使用 itemViewHeight * itemViewNum = RecyclerView的高度
     */


    public void setData(String downloadPath[]) {
        data = downloadPath;
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
