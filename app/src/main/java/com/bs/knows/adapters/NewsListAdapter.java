package com.bs.knows.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bs.knows.R;
import com.bs.knows.connect.bean.NewsListBean;

import java.util.ArrayList;
import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.viewHolder> {
    private String TAG="TAG";
    private Context mContext;
    private List<NewsListBean.NewsBean> informData = new ArrayList<>();


    public NewsListAdapter( Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public NewsListAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsListAdapter.viewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_news_list, parent, false));
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NewsListAdapter.viewHolder holder, int position) {

        NewsListBean.NewsBean databean = informData.get(position);
        holder.tvTitle.setText(databean.getNewsTitles());
        holder.tvTime.setText(databean.getNewTime());
        Log.d(TAG, "onResponse: 333333333333333333333333333333333333333333333333333333333");
        Log.d(TAG, "onBindViewHolder: "+databean.getNewsTitles());

        holder.itemView.setOnClickListener(v -> {
            onClick(databean.getNewsUrl());

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


    public void setData(NewsListBean newsListBean) {
        Log.d(TAG, "onResponse: 222222222222222222222222222222222222222222222222222222222222");
        informData.clear();
        informData.addAll(newsListBean.getNews());
        notifyDataSetChanged();
    }


    public void onClick(String url) {
        Intent intent = new Intent();
        intent.setData(Uri.parse(url));//Url 就是你要打开的网址
        intent.setAction(Intent.ACTION_VIEW);
        mContext.startActivity(intent); //启动浏览器
    }


    static class viewHolder extends RecyclerView.ViewHolder {
        View itemView;
        TextView tvTitle, tvTime;

        viewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            tvTitle=itemView.findViewById(R.id.tv_news_title);
            tvTime=itemView.findViewById(R.id.tv_news_time);
        }
    }
}
