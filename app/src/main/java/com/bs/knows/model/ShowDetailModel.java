package com.bs.knows.model;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.bs.knows.connect.Api;
import com.bs.knows.connect.ConnectListener;
import com.bs.knows.connect.DownloadFiles.DownloadFile;
import com.bs.knows.connect.DownloadFiles.UploadFiles;
import com.bs.knows.connect.bean.CheckUploadTaskBean;
import com.bs.knows.connect.bean.ChooseTaskStuSelectBean;
import com.bs.knows.connect.bean.ProgressUpate;
import com.bs.knows.connect.bean.TaskDetail;
import com.bs.knows.connect.InitRetrofit;

import com.bs.knows.connect.bean.UserDetailBean;
import com.bs.knows.databinding.ActivityShowDetailBinding;
import com.bs.knows.databinding.ActivityShowListBinding;
import com.bs.knows.utils.StaticUtils;
import com.bs.knows.view.BaseActivtyView;
import com.bs.knows.view.ShowDetailActivityView;
import com.bs.knows.view.StudentActivityView;
import com.leon.lfilepickerlibrary.LFilePicker;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ShowDetailModel extends BaseActivtyView {


    private static String TAG = "TAGSS";
    private static Retrofit retrofit = InitRetrofit.getUserData();
    private static Api api = retrofit.create(Api.class);
    private static Context mContext;

    //下拉选择框
    private static String[] ProgressList = {"选择教学计划", "准备选题", "选题中", "选题完成", "准备开题答辩", "开题答辩中", "准备中期答辩", "中期答辩中", "准备毕业答辩", "毕业答辩中", "答辩完成！"};
    private static Spinner spinner;
    private static ArrayAdapter<String> adapter;

    public static void showTaskDetail(Context context, ActivityShowDetailBinding binding, Intent intent) {
        mContext = context;
        Retrofit retrofit = InitRetrofit.getUserData();
        Api api = retrofit.create(Api.class);

        String taskname = intent.getStringExtra("taskname");
        String fromActivity = intent.getStringExtra("from");


        if (!StaticUtils.TASK_STATUS.equals("选题中")) {
            binding.btnConformTask.setVisibility(View.GONE);
        }


        switch (fromActivity) {
            case "showTaskDetail":
            case "beganChooseTask":
            case "showTaskDetail_count3":
                TaskInitServers(binding, mContext, intent, taskname);
                break;

            case "chooseTaskStu":
            case "StudentDetail":
                api.UserDetailShow(intent.getStringExtra("stuID"), "学生").enqueue(new Callback<UserDetailBean>() {
                    @Override
                    public void onResponse(Call<UserDetailBean> call, Response<UserDetailBean> response) {
                        if (!response.body().isError()) {
                            List<UserDetailBean.DataBean> userDetail = response.body().getData();
                            StuDetailInitData(binding, userDetail, intent);
                        }
                    }
                    @Override
                    public void onFailure(Call<UserDetailBean> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                });
                break;

            case "TeacherDetail":
                api.UserDetailShow(intent.getStringExtra("teaID"), "教师").enqueue(new Callback<UserDetailBean>() {
                    @Override
                    public void onResponse(Call<UserDetailBean> call, Response<UserDetailBean> response) {
                        if (!response.body().isError()) {
                            List<UserDetailBean.DataBean> userDetail = response.body().getData();
                            TeaDetailInitData(binding, userDetail, intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<UserDetailBean> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                });
                break;

            case "TeaTaskPlan":
                showTaskProgress(binding);
                break;

            case "finishChooseTask":
                api.TaskDetailShow(StaticUtils.STU__CHOOSE_TASK_NAME).enqueue(new Callback<TaskDetail>() {
                    @Override
                    public void onResponse(Call<TaskDetail> call, Response<TaskDetail> response) {
                        if (!response.body().isError()) {
                            List<TaskDetail.DataBean> dataBeans = response.body().getData();
                            InitTaskData(binding, dataBeans);

                        }
                    }

                    @Override
                    public void onFailure(Call<TaskDetail> call, Throwable t) {
                        Toast.makeText(context, "系统错误！请稍后重试！", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, StudentActivityView.class));
                    }
                });
                break;

            case "StuTaskPlan":
                stuChooseTaskFile(binding, context);
                break;


            case "checkUploadTaskDetail":

                api.TaskDetailShow(intent.getStringExtra("fromUploadFileTask")).enqueue(new Callback<TaskDetail>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<TaskDetail> call, Response<TaskDetail> response) {
                        assert response.body() != null;
                        List<TaskDetail.DataBean> taskdata = response.body().getData();
                        showTaskUploadDetail(binding, taskdata);

                    }

                    @Override
                    public void onFailure(Call<TaskDetail> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());

                    }
                });
                break;
            default:
                Log.d(TAG, "onFailure: ");

        }


    }


    //列表显示网络请求
    private static void TaskInitServers(ActivityShowDetailBinding binding, Context context, Intent intent, String taskName) {

        api.TaskDetailShow(taskName).enqueue(new Callback<TaskDetail>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<TaskDetail> call, Response<TaskDetail> response) {
                assert response.body() != null;
                List<TaskDetail.DataBean> taskdata = response.body().getData();
                StuTaskInitData(binding, taskdata, intent);

            }

            @Override
            public void onFailure(Call<TaskDetail> call, Throwable t) {
                Toast.makeText(context, "系统错误！请稍后再试!", Toast.LENGTH_SHORT).show();

            }
        });
    }


    //学生显示课题
    private static void InitTaskData(ActivityShowDetailBinding binding, List<TaskDetail.DataBean> taskdata) {

        binding.loadingStyle.setVisibility(View.GONE);

        binding.t1.tvShowDetailTitle.setText("标题：");
        binding.t1.showDetailBaseList.setVisibility(View.VISIBLE);
        binding.t1.tvShowDetailDetail.setText(taskdata.get(0).getTaskname());


        binding.t2.tvShowDetailTitle.setText("简介：");
        binding.t2.showDetailBaseList.setVisibility(View.VISIBLE);
        binding.t2.tvShowDetailDetail.setText(taskdata.get(0).getTaskabstract());

        binding.t3.tvShowDetailTitle.setText("指导教师：");
        binding.t3.showDetailBaseList.setVisibility(View.VISIBLE);
        binding.t3.tvShowDetailDetail.setText(taskdata.get(0).getTaskteachername());

        binding.t4.tvShowDetailTitle.setText("专业：");
        binding.t4.showDetailBaseList.setVisibility(View.VISIBLE);
        binding.t4.tvShowDetailDetail.setText(taskdata.get(0).getMajor());

        binding.t5.tvShowDetailTitle.setText("学院：");
        binding.t5.showDetailBaseList.setVisibility(View.VISIBLE);
        binding.t5.tvShowDetailDetail.setText(taskdata.get(0).getCollege());

        binding.t6.tvShowDetailTitle.setText("进度：");
        binding.t6.showDetailBaseList.setVisibility(View.VISIBLE);
        binding.t6.tvShowDetailDetail.setText(taskdata.get(0).getStatus());


    }


    //含已选学生 显示课题详情
    private static void StuTaskInitData(ActivityShowDetailBinding binding, List<TaskDetail.DataBean> taskdata, Intent intent) {
        binding.loadingStyle.setVisibility(View.GONE);

        binding.t1.tvShowDetailTitle.setText("标题：");
        binding.t1.showDetailBaseList.setVisibility(View.VISIBLE);
        binding.t1.tvShowDetailDetail.setText(taskdata.get(0).getTaskname());

        binding.t2.tvShowDetailTitle.setText("简介：");
        binding.t2.showDetailBaseList.setVisibility(View.VISIBLE);
        binding.t2.tvShowDetailDetail.setText(taskdata.get(0).getTaskabstract());

        binding.t3.tvShowDetailTitle.setText("指导教师：");
        binding.t3.showDetailBaseList.setVisibility(View.VISIBLE);
        binding.t3.tvShowDetailDetail.setText(taskdata.get(0).getTaskteachername());

        binding.t4.tvShowDetailTitle.setText("专业：");
        binding.t4.showDetailBaseList.setVisibility(View.VISIBLE);
        binding.t4.tvShowDetailDetail.setText(taskdata.get(0).getMajor());

        binding.t5.tvShowDetailTitle.setText("学院：");
        binding.t5.showDetailBaseList.setVisibility(View.VISIBLE);
        binding.t5.tvShowDetailDetail.setText(taskdata.get(0).getCollege());

        binding.t6.tvShowDetailTitle.setText("进度：");
        binding.t6.showDetailBaseList.setVisibility(View.VISIBLE);
        binding.t6.tvShowDetailDetail.setText(taskdata.get(0).getStatus());

        binding.t7.tvShowDetailTitle.setText("已选人数：");
        binding.t7.showDetailBaseList.setVisibility(View.VISIBLE);
        binding.t7.tvShowDetailDetail.setText(taskdata.get(0).getChosed() + "人");

        binding.ScrollShowListTitle.setVisibility(View.VISIBLE);
        binding.rvShowChasedStuList.setVisibility(View.VISIBLE);

        binding.btnConformTask.setVisibility(View.VISIBLE);
        binding.btnConformTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                        .setTitle("确认选题！")
                        .setMessage("请再次确认所选课题为：" + taskdata.get(0).getTaskname())
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                api.StuChooseTask(UserUtilsModel.UserIsLogin.getInstance().getPhone(),
                                        taskdata.get(0).getTaskname()).enqueue(new Callback<ChooseTaskStuSelectBean>() {
                                    @Override
                                    public void onResponse(Call<ChooseTaskStuSelectBean> call, Response<ChooseTaskStuSelectBean> response) {
                                        if (response.body().isChooseTask()) {
                                            Toast.makeText(mContext, "已选择该课题", Toast.LENGTH_SHORT).show();
                                            binding.btnConformTask.setVisibility(View.GONE);
                                        } else if (response.body().isSelectTask()) {
                                            Toast.makeText(mContext, "您已选择过该课题", Toast.LENGTH_SHORT).show();
                                            binding.btnConformTask.setVisibility(View.GONE);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ChooseTaskStuSelectBean> call, Throwable t) {
                                        Toast.makeText(mContext, "系统错误！请稍后再试！", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .create();
                alertDialog.show();

            }
        });

        if (!UserUtilsModel.UserIsLogin.getInstance().getRole().equals("学生")) {

            binding.tvShowChasedStuDetailListTitle.setVisibility(View.VISIBLE);
            binding.rvShowChasedStuList.setVisibility(View.VISIBLE);
            binding.btnConformTask.setVisibility(View.GONE);
        }
        if (intent.getStringExtra("from").equals("showTaskDetail_count3")) {
            binding.btnConformTask.setVisibility(View.GONE);
        }
        if (taskdata.get(0).getChosed().equals("0")) {
            binding.tvShowChasedStuDetailListTitle.setText("该课题尚未有学生选择");
            binding.tvShowChasedStuDetailListTitle.setVisibility(View.VISIBLE);
            binding.rvShowChasedStuList.setVisibility(View.VISIBLE);
        }
        if (taskdata.get(0).getStatus().equals("选题完成")) {
            binding.t7.showDetailBaseList.setVisibility(View.GONE);
            binding.tvShowChasedStuDetailListTitle.setText("该课题已完成确认学生,选择该课题学生为：");
            binding.tvShowChasedStuDetailListTitle.setVisibility(View.VISIBLE);
            binding.rvShowChasedStuList.setVisibility(View.VISIBLE);
            StaticUtils.STU_FINISH_CHOOSE_TASK = true;
        }


    }

    //列表显示学生信息
    private static void StuDetailInitData(ActivityShowDetailBinding binding, List<UserDetailBean.DataBean> taskdata, Intent intent) {
        binding.loadingStyle.setVisibility(View.GONE);

        binding.t1.tvShowDetailTitle.setText("学生姓名：");
        binding.t1.showDetailBaseList.setVisibility(View.VISIBLE);
        binding.t1.tvShowDetailDetail.setText(taskdata.get(0).getStudentname());

        binding.t2.tvShowDetailTitle.setText("就读专业：");
        binding.t2.showDetailBaseList.setVisibility(View.VISIBLE);
        binding.t2.tvShowDetailDetail.setText(taskdata.get(0).getMajor());

        binding.t3.tvShowDetailTitle.setText("所属学院：");
        binding.t3.showDetailBaseList.setVisibility(View.VISIBLE);
        binding.t3.tvShowDetailDetail.setText(taskdata.get(0).getCollege());


    }

    //列表显示教师信息
    public static void TeaDetailInitData(ActivityShowDetailBinding binding, List<UserDetailBean.DataBean> taskdata, Intent dintent) {
        binding.loadingStyle.setVisibility(View.GONE);

        binding.t1.tvShowDetailTitle.setText("教师姓名：");
        binding.t1.showDetailBaseList.setVisibility(View.VISIBLE);
        binding.t1.tvShowDetailDetail.setText(taskdata.get(0).getTeachername());

        binding.t2.tvShowDetailTitle.setText("主修专业：");
        binding.t2.showDetailBaseList.setVisibility(View.VISIBLE);
        binding.t2.tvShowDetailDetail.setText(taskdata.get(0).getMajor());

        binding.t3.tvShowDetailTitle.setText("所在学院：");
        binding.t3.showDetailBaseList.setVisibility(View.VISIBLE);
        binding.t3.tvShowDetailDetail.setText(taskdata.get(0).getCollege());


    }


    //    文件选择
    @SuppressLint("SetTextI18n")
    public static void stuChooseTaskFile(ActivityShowDetailBinding binding, Context context) {
        binding.loadingStyle.setVisibility(View.GONE);
        binding.TaskPlanTitle.setVisibility(View.VISIBLE);
        binding.UploadFile.setVisibility(View.VISIBLE);
        binding.TaskPlanUploadView.setVisibility(View.VISIBLE);
        binding.TaskPlanTitle.setText("当前论文进度为：" + StaticUtils.TASK_STATUS);

    }


    //上传文件
    public static void UploadFile(String filePath, ActivityShowDetailBinding binding) {

        UploadFiles.Upload(filePath, new ConnectListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onProgress(int progress) {

            }

            @Override
            public void onFinish(String path) {

            }

            @Override
            public void onUploadFinish() {
                binding.TaskPlanUploadView.post(new Runnable() {
                    @Override
                    public void run() {
                        binding.UploadFile.setText("上传完成！");
                    }
                });
            }

            @Override
            public void onFail(String errorInfo) {
                binding.TaskPlanUploadView.post(new Runnable() {
                    @Override
                    public void run() {
                        binding.UploadFile.setText("系统错误！请稍后重试！");
                    }
                });
            }
        });
    }

    //显示课题进度功能
    public static void showTaskProgress(ActivityShowDetailBinding binding) {
        binding.loadingStyle.setVisibility(View.GONE);
        binding.tvProgressTitle.setVisibility(View.VISIBLE);
        binding.rvShowChasedStuList.setVisibility(View.VISIBLE);


        binding.tvProgressTitle.setText("当前进度: " + StaticUtils.TASK_STATUS);

        if (UserUtilsModel.UserIsLogin.getInstance().getRole().equals("管理员")) {
            binding.spiTaskProgress.setVisibility(View.VISIBLE);

            spinner = binding.spiTaskProgress;
            //将可选内容与ArrayAdapter连接起来
            adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, ProgressList);
            //设置下拉列表的风格
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //将adapter 添加到spinner中
            spinner.setAdapter(adapter);
            //添加事件Spinner事件监听
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (!ProgressList[position].equals("选择教学计划")
                            && !ProgressList[position].equals(StaticUtils.TASK_STATUS)) {
                        AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                                .setTitle("确认进度！")
                                .setMessage("请确认所选课题进度：\n    " + ProgressList[position] + " 是否正确！\n确定更改后将改变正在进行中的任务！")

                                .setNegativeButton("确定", (dialog, which) -> api.ProgressUpdate(ProgressList[position]).enqueue(new Callback<ProgressUpate>() {
                                    @Override
                                    public void onResponse(Call<ProgressUpate> call, Response<ProgressUpate> response) {
                                        if (response.body().isUpdateProgress()) {
                                            binding.tvProgressTitle.setText("当前进度：" + ProgressList[position]);
                                            Toast.makeText(mContext, "更改课题进度成功！", Toast.LENGTH_SHORT).show();
                                            StaticUtils.TASK_STATUS = ProgressList[position];
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ProgressUpate> call, Throwable t) {
                                        Toast.makeText(mContext, "服务器异常！请稍后再试！", Toast.LENGTH_SHORT).show();
                                    }
                                }))
                                .setPositiveButton("取消", null)
                                .create();
                        alertDialog.show();
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            spinner.setVisibility(View.VISIBLE);

        }


    }

    //显示课题进度详情功能
    public static void showTaskUploadDetail(ActivityShowDetailBinding binding, List<TaskDetail.DataBean> taskdata) {
        binding.loadingStyle.setVisibility(View.GONE);

        binding.ScrollShowListTitle.setVisibility(View.VISIBLE);
        binding.rvShowChasedStuList.setVisibility(View.VISIBLE);
        binding.tvShowChasedStuDetailListTitle.setText("点击即可下载学生上传的文件");

        binding.t1.tvShowDetailTitle.setText("标题：");
        binding.t1.showDetailBaseList.setVisibility(View.VISIBLE);
        binding.t1.tvShowDetailDetail.setText(taskdata.get(0).getTaskname());

        binding.t2.tvShowDetailTitle.setText("简介：");
        binding.t2.showDetailBaseList.setVisibility(View.VISIBLE);
        binding.t2.tvShowDetailDetail.setText(taskdata.get(0).getTaskabstract());

        binding.t3.tvShowDetailTitle.setText("指导教师：");
        binding.t3.showDetailBaseList.setVisibility(View.VISIBLE);
        binding.t3.tvShowDetailDetail.setText(taskdata.get(0).getTaskteachername());

        binding.t4.tvShowDetailTitle.setText("专业：");
        binding.t4.showDetailBaseList.setVisibility(View.VISIBLE);
        binding.t4.tvShowDetailDetail.setText(taskdata.get(0).getMajor());

        binding.t5.tvShowDetailTitle.setText("学院：");
        binding.t5.showDetailBaseList.setVisibility(View.VISIBLE);
        binding.t5.tvShowDetailDetail.setText(taskdata.get(0).getCollege());

        binding.t6.tvShowDetailTitle.setText("进度：");
        binding.t6.showDetailBaseList.setVisibility(View.VISIBLE);
        binding.t6.tvShowDetailDetail.setText(taskdata.get(0).getStatus());

        String Path = taskdata.get(0).getUploedfilepath();
        if (!Path.isEmpty()) {
            String PathList[] = Path.split(",");
            for (int i = 6; i < PathList.length + 6; i++) {
                InitListModel.initDownloadUploadTaskFileView(binding, mContext, PathList);

            }
        }


    }


}
