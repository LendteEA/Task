package com.bs.knows.connect.DownloadFiles;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.bs.knows.R;
import com.bs.knows.utils.StaticUtils;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    public static void SaveFile(Context context,String fileName){
        @SuppressLint("SdCardPath")
        File filepath = new File(StaticUtils.FILE_PATH);
        File tempFile = new File(filepath + fileName);
        if (!filepath.exists()) {
            try {
                //按照指定的路径创建文件夹
                filepath.mkdirs();
            } catch (Exception e) {
                AlertDialog alertDialog=new AlertDialog.Builder(context)
                        .setTitle("创建本地文件夹错误")
                        .setMessage("无法创建本地文件夹，请给予读写文件权限\n错误代码："+e.getMessage())
                        .setIcon(R.drawable.ic_warning_red_24dp)
                        .create();
                alertDialog.show();

            }
        }

        File createFiles = new File(filepath, fileName);
        try {
            createFiles.createNewFile();
            Toast.makeText(context,"文件已保存到"+tempFile,Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
