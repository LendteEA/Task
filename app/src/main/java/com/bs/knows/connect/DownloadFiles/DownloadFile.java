package com.bs.knows.connect.DownloadFiles;

import android.content.Context;

import com.bs.knows.connect.Api;
import com.bs.knows.connect.ConnectListener;
import com.bs.knows.databinding.ActivityShowDetailBinding;
import com.bs.knows.utils.StaticUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Executors;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DownloadFile {

    private static ActivityShowDetailBinding mBinding;
    private static Context mContext;
    public static void Downloads(ActivityShowDetailBinding binding, Context context, String url, final String path, final ConnectListener connectListener) {
        mBinding=binding;
        mContext=context;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StaticUtils.BASE_URL)
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .build();

        Api api = retrofit.create(Api.class);

        Call<ResponseBody> call = api.download(url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                writeResponseToDisk(path, response, connectListener);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                connectListener.onFail("网络错误！请稍后重试");
            }
        });
    }

    private static void writeResponseToDisk(String path, Response<ResponseBody> response, ConnectListener connectListener) {
        writeFileFromIS(new File(path), response.body().byteStream(), response.body().contentLength(), connectListener);
    }

    private static int sBufferSize = 8192;

    private static void writeFileFromIS(File file, InputStream byteStream, long contentLength, ConnectListener connectListener) {
        connectListener.onStart();

        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdir();
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.getMessage();
                    connectListener.onFail("Fail" + e.getMessage());
                }
            }

        }


        OutputStream os = null;
        long currentLength = 0;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file));
            byte data[] = new byte[sBufferSize];
            int len;
            while ((len = byteStream.read(data, 0, sBufferSize)) != -1) {
                os.write(data, 0, len);
                currentLength += len;
                //计算当前下载进度
                connectListener.onProgress((int) (100 * currentLength / contentLength));


            }
            //下载完成，并返回保存的文件路径
            connectListener.onFinish(file.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
            connectListener.onFail("IOException");
        } finally {
            try {
                byteStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
