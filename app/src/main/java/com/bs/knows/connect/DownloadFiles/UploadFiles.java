package com.bs.knows.connect.DownloadFiles;

import android.util.Log;

import com.bs.knows.connect.ConnectListener;

import com.bs.knows.model.UserUtilsModel;

import java.io.File;

import java.io.IOException;
import java.io.InputStream;


import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class UploadFiles {

    private static String TAG="TAG";
    public static void Upload(final String filePath, final ConnectListener connectListener) {


        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("userID", UserUtilsModel.UserIsLogin.getInstance().getPhone())
                .addFormDataPart("file", filePath,
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File(filePath)))
                .build();
        Request request = new Request.Builder()
                .url("http://39.107.119.177/z_app.php?action=uploadFile")
                .method("POST", body)
                .build();

        client.newCall(request)
                .enqueue(new okhttp3.Callback() {

                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {
                        connectListener.onFail("系统错误！请稍后重试！");
                    }

                    @Override
                    public void onResponse(okhttp3.Call call, okhttp3.Response response) {
                       writeFileFromIS(response.body().byteStream(),response.body().contentLength(),connectListener);
                    }
                });
    }

    private static int sBufferSize = 8192;

    private static void writeFileFromIS(InputStream byteStream, long contentLength, ConnectListener connectListener) {
        connectListener.onStart();

        long currentLength = 0;
        try {

            byte data[] = new byte[sBufferSize];
            int len;
            while ((len = byteStream.read(data, 0, sBufferSize)) != -1) {
                currentLength += len;
                //计算当前上传进度
                connectListener.onProgress((int) (100 * currentLength / contentLength));
                Log.d(TAG, "writeFileFromIS: "+(int) (100 * currentLength / contentLength));

            }
            connectListener.onUploadFinish();

        } catch (IOException e) {
            e.printStackTrace();
            connectListener.onFail("IOException");
        } finally {
            try {
                byteStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
}
