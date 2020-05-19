package com.bs.knows.connect;

public interface ConnectListener {
    void onStart();//下载开始

    void onProgress(int progress);//下载进度

    void onFinish(String path);//下载完成

    void onUploadFinish();

    void onFail(String errorInfo);//下载失败
}
