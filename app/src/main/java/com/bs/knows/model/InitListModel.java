package com.bs.knows.model;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bs.knows.adapters.ChooseTaskStuAdapter;
import com.bs.knows.adapters.NewsListAdapter;
import com.bs.knows.adapters.ShowDownloadFilePathAdapter;
import com.bs.knows.adapters.ShowStuListAdapter;
import com.bs.knows.adapters.ShowStuUploadFileAdapter;
import com.bs.knows.adapters.ShowTeaListAdapter;
import com.bs.knows.adapters.TaskCheckListAdapter;
import com.bs.knows.adapters.InformListAdapter;
import com.bs.knows.adapters.TaskListAdapter;
import com.bs.knows.connect.Api;
import com.bs.knows.connect.bean.CheckTaskBean;
import com.bs.knows.connect.bean.CheckUploadTaskBean;
import com.bs.knows.connect.bean.InformDetailBean;
import com.bs.knows.connect.bean.ChooseTaskStuBean;
import com.bs.knows.connect.bean.NewsListBean;
import com.bs.knows.connect.bean.TaskListBean;
import com.bs.knows.connect.InitRetrofit;
import com.bs.knows.connect.bean.UserDetailBean;
import com.bs.knows.databinding.ActivityAdminViewBinding;
import com.bs.knows.databinding.ActivityCheckTaskViewBinding;
import com.bs.knows.databinding.ActivityInformsViewBinding;
import com.bs.knows.databinding.ActivityShowDetailBinding;
import com.bs.knows.databinding.ActivityShowListBinding;
import com.bs.knows.databinding.ActivityStudentViewBinding;
import com.bs.knows.databinding.ActivityTeacherViewBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class InitListModel {
    private static String TAG = "TAG";
    private static NewsListAdapter newsListAdapter;
    private static TaskListAdapter taskListAdapter;
    private static InformListAdapter informListAdapter;
    private static ShowStuListAdapter showStuListAdapter;
    private static ShowTeaListAdapter showTeaListAdapter;
    private static TaskCheckListAdapter taskCheckListAdapter;
    private static ChooseTaskStuAdapter chooseTaskStuAdapter;
    private static ShowStuUploadFileAdapter showStuUploadFileAdapter;
    private static ShowDownloadFilePathAdapter showDownloadFilePathAdapter;


    //    ==================================================      初始化课题列表       ================================================================

    public static void initTaskListView(ActivityShowListBinding binding, Context context) {
        /*
         * 1、假如已知列表高度的情况下，可以直接在布局中把RecyclerView的高度定义上
         * 2、不知道列表高度的情况下，需要手动计算RecyclerView的高度
         */

        binding.rvList.setLayoutManager(new LinearLayoutManager(context));
        binding.rvList.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        binding.rvList.setNestedScrollingEnabled(false);
        taskListAdapter = new TaskListAdapter(binding,context);
        binding.rvList.setAdapter(taskListAdapter);
        getTaskListData();

    }

    private static void getTaskListData() {

        Retrofit retrofit = InitRetrofit.getUserData();
        Api api = retrofit.create(Api.class);
        Call<TaskListBean> task = api.TaskListShow(UserUtilsModel.UserIsLogin.getInstance().getPhone(), UserUtilsModel.UserIsLogin.getInstance().getRole());
        task.enqueue(new Callback<TaskListBean>() {
            @Override
            public void onResponse(Call<TaskListBean> call, Response<TaskListBean> response) {
                try {
                    TaskListBean taskListBean = response.body();
                    Log.d(TAG, "onResponse: " + response.body());
                    TaskUpdateList(taskListBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<TaskListBean> call, Throwable t) {
                Log.d(TAG, "onResponse: " + t.getMessage());
            }
        });

    }


    private static void TaskUpdateList(TaskListBean taskListBean) {
        taskListAdapter.setData(taskListBean);
    }


//    ==================================================      初始化通知列表       ================================================================

    public static void initInformListView(ActivityInformsViewBinding binding, Context context) {
        /*
         * 1、假如已知列表高度的情况下，可以直接在布局中把RecyclerView的高度定义上
         * 2、不知道列表高度的情况下，需要手动计算RecyclerView的高度
         */

        binding.rvList.setLayoutManager(new LinearLayoutManager(context));
        binding.rvList.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        binding.rvList.setNestedScrollingEnabled(false);
        informListAdapter = new InformListAdapter(binding,context);
        binding.rvList.setAdapter(informListAdapter);
        getInformListData();

    }

    private static void getInformListData() {

        //初始化Retrofit
        Retrofit retrofit = InitRetrofit.getUserData();
        //初始化接口
        Api api = retrofit.create(Api.class);

        //开始网络请求
        api.InformListShow(UserUtilsModel.UserIsLogin.getInstance().getPhone(), UserUtilsModel.UserIsLogin.getInstance().getRole())
                .enqueue(new Callback<InformDetailBean>() {
                    @Override
                    public void onResponse(Call<InformDetailBean> call, Response<InformDetailBean> response) {
                        try {
                            InformDetailBean informDetailBean = response.body();
                            Log.d(TAG, "onResponses: " + response.body().getData().get(0).getInformdetail());
                            informUpdateList(informDetailBean);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.d(TAG, "oF: " + e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<InformDetailBean> call, Throwable t) {

                    }
                });

    }

    private static void informUpdateList(InformDetailBean informDetailBean) {
        informListAdapter.setData(informDetailBean);
    }


    //    ==================================================      初始化审核课题列表       ================================================================

    public static void initCheckTaskListView(ActivityCheckTaskViewBinding binding, Context context) {
        /*
         * 1、假如已知列表高度的情况下，可以直接在布局中把RecyclerView的高度定义上
         * 2、不知道列表高度的情况下，需要手动计算RecyclerView的高度
         */

        binding.rvList.setLayoutManager(new LinearLayoutManager(context));
        binding.rvList.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        binding.rvList.setNestedScrollingEnabled(false);
        taskCheckListAdapter = new TaskCheckListAdapter(binding, context);
        binding.rvList.setAdapter(taskCheckListAdapter);
        getCheckTaskListData();

    }

    private static void getCheckTaskListData() {

        //初始化Retrofit
        Retrofit retrofit = InitRetrofit.getUserData();
        //初始化接口
        Api api = retrofit.create(Api.class);

        //开始网络请求
        api.AllTaskListShow().enqueue(new Callback<CheckTaskBean>() {
            @Override
            public void onResponse(Call<CheckTaskBean> call, Response<CheckTaskBean> response) {
                try {
                    CheckTaskBean checkTaskBean = response.body();
                    Log.d(TAG, "onResponses: " + response.body());
                    CheckUpdateList(checkTaskBean);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d(TAG, "oF: " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<CheckTaskBean> call, Throwable t) {

            }
        });

    }

    private static void CheckUpdateList(CheckTaskBean checkTaskBean) {
        taskCheckListAdapter.setData(checkTaskBean);
    }


    //    ==================================================      初始化课题已选学生列表       ================================================================

    public static void InitChooseTaskStuListView(ActivityShowDetailBinding binding, Context context, Intent intent) {
        /*
         * 1、假如已知列表高度的情况下，可以直接在布局中把RecyclerView的高度定义上
         * 2、不知道列表高度的情况下，需要手动计算RecyclerView的高度
         */
        String taskName = intent.getStringExtra("taskname");

        binding.rvShowChasedStuList.setLayoutManager(new LinearLayoutManager(context));
        binding.rvShowChasedStuList.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        binding.rvShowChasedStuList.setNestedScrollingEnabled(false);
        chooseTaskStuAdapter = new ChooseTaskStuAdapter(context, binding, intent);
        binding.rvShowChasedStuList.setAdapter(chooseTaskStuAdapter);
        getChasedTaskStuListData(taskName,context);

    }

    private static void getChasedTaskStuListData(String taskName,Context context) {

        //初始化Retrofit
        Retrofit retrofit = InitRetrofit.getUserData();
        //初始化接口
        Api api = retrofit.create(Api.class);


        api.ChooseTaskStuDetailShow(taskName).enqueue(new Callback<ChooseTaskStuBean>() {
            @Override
            public void onResponse(Call<ChooseTaskStuBean> call, Response<ChooseTaskStuBean> response) {
                try {
                    ChooseTaskStuBean chooseTaskStuBean = response.body();
                    CheckTaskStuList(chooseTaskStuBean);
                    Log.d(TAG, "getChasedTaskStuListData: taskName"+response.body().getData());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d(TAG, "oF: " + e.getMessage());

                }
            }

            @Override
            public void onFailure(Call<ChooseTaskStuBean> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());

            }
        });


    }

    private static void CheckTaskStuList(ChooseTaskStuBean chooseTaskStuBean) {
        chooseTaskStuAdapter.setData(chooseTaskStuBean);
    }

    //    ==================================================      初始化学生用户列表       ================================================================

    public static void initStuListView(ActivityShowListBinding binding, Context context) {
        /*
         * 1、假如已知列表高度的情况下，可以直接在布局中把RecyclerView的高度定义上
         * 2、不知道列表高度的情况下，需要手动计算RecyclerView的高度
         */

        binding.rvList.setLayoutManager(new LinearLayoutManager(context));
        binding.rvList.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        binding.rvList.setNestedScrollingEnabled(false);
        showStuListAdapter = new ShowStuListAdapter(binding,context);
        binding.rvList.setAdapter(showStuListAdapter);
        getStuData();

    }

    private static void getStuData() {

        Retrofit retrofit = InitRetrofit.getUserData();
        Api api = retrofit.create(Api.class);
        api.UserDetailShow("checkUserDetail", "学生")
                .enqueue(new Callback<UserDetailBean>() {
                    @Override
                    public void onResponse(Call<UserDetailBean> call, Response<UserDetailBean> response) {
                        try {
                            UserDetailBean userDetailBean = response.body();
                            ShowStuDetail(userDetailBean);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserDetailBean> call, Throwable t) {

                    }
                });

    }


    private static void ShowStuDetail(UserDetailBean userDetailBean) {
        showStuListAdapter.setData(userDetailBean);
    }


    //    ==================================================      初始化教师用户列表       ================================================================

    public static void initTeaListView(ActivityShowListBinding binding, Context context) {
        /*
         * 1、假如已知列表高度的情况下，可以直接在布局中把RecyclerView的高度定义上
         * 2、不知道列表高度的情况下，需要手动计算RecyclerView的高度
         */

        binding.rvList.setLayoutManager(new LinearLayoutManager(context));
        binding.rvList.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        binding.rvList.setNestedScrollingEnabled(false);
        showTeaListAdapter = new ShowTeaListAdapter(binding,context);
        binding.rvList.setAdapter(showTeaListAdapter);
        getTeaData();

    }

    private static void getTeaData() {

        Retrofit retrofit = InitRetrofit.getUserData();
        Api api = retrofit.create(Api.class);
        api.UserDetailShow("checkUserDetail", "教师")
                .enqueue(new Callback<UserDetailBean>() {
                    @Override
                    public void onResponse(Call<UserDetailBean> call, Response<UserDetailBean> response) {
                        try {
                            UserDetailBean userDetailBean = response.body();
                            ShowTeaDetail(userDetailBean);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserDetailBean> call, Throwable t) {

                    }
                });

    }


    private static void ShowTeaDetail(UserDetailBean userDetailBean) {
        showTeaListAdapter.setData(userDetailBean);
    }


    //    ==================================================      初始化课题上传列表       ================================================================

    public static void initUploadTaskListView(ActivityShowDetailBinding binding, Context context) {
        /*
         * 1、假如已知列表高度的情况下，可以直接在布局中把RecyclerView的高度定义上
         * 2、不知道列表高度的情况下，需要手动计算RecyclerView的高度
         */

        binding.rvShowChasedStuList.setLayoutManager(new LinearLayoutManager(context));
        binding.rvShowChasedStuList.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        binding.rvShowChasedStuList.setNestedScrollingEnabled(false);
        showStuUploadFileAdapter = new ShowStuUploadFileAdapter(context);
        binding.rvShowChasedStuList.setAdapter(showStuUploadFileAdapter);
        getUploadTaskData(context);

    }

    private static void getUploadTaskData(Context context) {

        Retrofit retrofit = InitRetrofit.getUserData();
        Api api = retrofit.create(Api.class);
        api.checkUploadFile(UserUtilsModel.UserIsLogin.getInstance().getPhone(),
                UserUtilsModel.UserIsLogin.getInstance().getRole())
                .enqueue(new Callback<CheckUploadTaskBean>() {
                    @Override
                    public void onResponse(Call<CheckUploadTaskBean> call, Response<CheckUploadTaskBean> response) {
                        try {

                            CheckUploadTaskBean checkUploadTaskBean = response.body();

                            ShowUploadTaskDetail(checkUploadTaskBean);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<CheckUploadTaskBean> call, Throwable t) {

                    }
                });

    }


    private static void ShowUploadTaskDetail(CheckUploadTaskBean checkUploadTaskBean) {
        showStuUploadFileAdapter.setData(checkUploadTaskBean);
    }



    // ===================================初始化下载列表=================================================
    public static void initDownloadUploadTaskFileView(ActivityShowDetailBinding binding, Context context,String downloadPath[]) {
        /*
         * 1、假如已知列表高度的情况下，可以直接在布局中把RecyclerView的高度定义上
         * 2、不知道列表高度的情况下，需要手动计算RecyclerView的高度
         */

        binding.rvShowChasedStuList.setLayoutManager(new LinearLayoutManager(context));
        binding.rvShowChasedStuList.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        binding.rvShowChasedStuList.setNestedScrollingEnabled(false);
        showDownloadFilePathAdapter = new ShowDownloadFilePathAdapter(context,binding);
        binding.rvShowChasedStuList.setAdapter(showDownloadFilePathAdapter);
        getTaskDownloadPath(downloadPath);

    }


    private static void getTaskDownloadPath(String downloadPath[]) {
        showDownloadFilePathAdapter.setData(downloadPath);
    }


    //    ==================================================      初始化—新闻—列表       ================================================================

    public static void initNewsListViewSTU(ActivityStudentViewBinding binding, Context context) {
        /*
         * 1、假如已知列表高度的情况下，可以直接在布局中把RecyclerView的高度定义上
         * 2、不知道列表高度的情况下，需要手动计算RecyclerView的高度
         */

        binding.rvShowNews.setLayoutManager(new LinearLayoutManager(context));
        binding.rvShowNews.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        binding.rvShowNews.setNestedScrollingEnabled(false);
        newsListAdapter = new NewsListAdapter(context);
        binding.rvShowNews.setAdapter(newsListAdapter);
        ShowNewsList(context);

    }

    public static void initNewsListViewTEA(ActivityTeacherViewBinding binding, Context context) {
        /*
         * 1、假如已知列表高度的情况下，可以直接在布局中把RecyclerView的高度定义上
         * 2、不知道列表高度的情况下，需要手动计算RecyclerView的高度
         */

        binding.rvShowNews.setLayoutManager(new LinearLayoutManager(context));
        binding.rvShowNews.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        binding.rvShowNews.setNestedScrollingEnabled(false);
        newsListAdapter = new NewsListAdapter(context);
        binding.rvShowNews.setAdapter(newsListAdapter);
        ShowNewsList(context);

    }

    public static void initNewsListViewADM(ActivityAdminViewBinding binding, Context context) {
        /*
         * 1、假如已知列表高度的情况下，可以直接在布局中把RecyclerView的高度定义上
         * 2、不知道列表高度的情况下，需要手动计算RecyclerView的高度
         */

        binding.rvShowNews.setLayoutManager(new LinearLayoutManager(context));
        binding.rvShowNews.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        binding.rvShowNews.setNestedScrollingEnabled(false);
        newsListAdapter = new NewsListAdapter(context);
        binding.rvShowNews.setAdapter(newsListAdapter);
        ShowNewsList(context);

    }

    private static void ShowNewsList(Context context) {

        Retrofit retrofit = InitRetrofit.getUserData();
        Api api = retrofit.create(Api.class);
        api.newsList()
                .enqueue(new Callback<NewsListBean>() {
                    @Override
                    public void onResponse(Call<NewsListBean> call, Response<NewsListBean> response) {
                        try {

                            NewsListBean newsListBean = response.body();
                            Log.d(TAG, "onResponse: 1111111111111111111111111111111111111111111111111111111111111111");
                            ShowNewsList(newsListBean);

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.d(TAG, "onResponse: "+e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsListBean> call, Throwable t) {
                        Toast.makeText(context,"系统错误！新闻模块将无法展示",Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onFailure: "+t.getMessage());
                    }
                });

    }


    private static void ShowNewsList(NewsListBean newsListBean) {
        newsListAdapter.setData(newsListBean);
    }


}
