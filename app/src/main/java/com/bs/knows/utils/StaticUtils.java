package com.bs.knows.utils;

import android.os.Environment;

import java.util.ArrayList;
import java.util.List;

public class StaticUtils {

    public static final String FILE_PATH= Environment.getDataDirectory().getPath()+"/Task/";
    public static final String BASE_URL = "http://39.107.119.177";
    public static final String DOWNLOAD_BASE_URL = "a_downloadimg.php?path=";
    public static String TAG = "TAG";

    public static boolean STU_FINISH_CHOOSE_TASK = false;
    public static boolean STU_READ_CHOOSE_TASK = false;
    public static String STU__CHOOSE_TASK_NAME = "1";
    public static String TASK_STATUS = "1";


}
