package com.bs.knows.connect;

import com.bs.knows.connect.bean.AddInformBean;
import com.bs.knows.connect.bean.AddMessageBean;
import com.bs.knows.connect.bean.AddTaskBean;
import com.bs.knows.connect.bean.CheckTaskBean;
import com.bs.knows.connect.bean.CheckUploadTaskBean;
import com.bs.knows.connect.bean.ChooseTaskStuConformBean;
import com.bs.knows.connect.bean.ChooseTaskStuDeleteBean;
import com.bs.knows.connect.bean.ChooseTaskStuSelectBean;
import com.bs.knows.connect.bean.DeleteData;
import com.bs.knows.connect.bean.HistoryDataText;
import com.bs.knows.connect.bean.InformDetailBean;
import com.bs.knows.connect.bean.ChooseTaskStuBean;
import com.bs.knows.connect.bean.NewsListBean;
import com.bs.knows.connect.bean.ProgressUpate;
import com.bs.knows.connect.bean.TaskDetail;
import com.bs.knows.connect.bean.TaskListBean;
import com.bs.knows.connect.bean.TaskPassBean;
import com.bs.knows.connect.bean.TaskStatusBean;
import com.bs.knows.connect.bean.TaskUnPassBean;
import com.bs.knows.connect.bean.UploadFileBean;
import com.bs.knows.connect.bean.UploadNewPic;
import com.bs.knows.connect.bean.UserDetailBean;
import com.bs.knows.connect.bean.UserHistoryData;
import com.bs.knows.connect.bean.UserRegister;
import com.bs.knows.connect.bean.UserUpdatePs;
import com.bs.knows.connect.bean.CheckUserId;
import com.bs.knows.connect.bean.CheckUserPswd;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

public interface Api {


    //==========================================  用户功能  ==========================================
    //                         查询密码是否正确
    @FormUrlEncoded
    @POST("/z_app.php?action=check_pswd")
    Call<CheckUserPswd> UserDataPasswdisCorrect(@Field("username") String username, @Field("password") String password, @Field("role") String role);

    //                         查询用户是否存在
    @FormUrlEncoded
    @POST("/z_app.php?action=check_user_exist")
    Call<CheckUserId> UserIsExist(@Field("username") String username,
                                  @Field("role") String role);
    //  注册
    @FormUrlEncoded
    @POST("/z_app.php?action=register")
    Call<UserRegister> UserRegister(@Field("usernumber") String usernumber,
                                    @Field("password") String password,
                                    @Field("role") String role,
                                    @Field("major") String major,
                                    @Field("college") String college,
                                    @Field("username") String username);

    @FormUrlEncoded
    @POST("/z_app.php?action=updates")
    Call<UserUpdatePs> UserUpdatePs(@Field("userID") String username,@Field("role") String userRole, @Field("old_password") String old_password, @Field("new_password") String new_password);



    //    获得用户信息
    @FormUrlEncoded
    @POST("/z_app.php?action=checkUserDetail")
    Call<UserDetailBean> UserDetailShow(@Field("userID") String userID,@Field("role") String role);


    //==========================================   管理员审核课题  ==========================================
    //  admin列表显示题目
    @GET("/z_app.php?action=checkAllTask")
    Call<CheckTaskBean> AllTaskListShow();

    //    通过
    @FormUrlEncoded
    @POST("/z_app.php?action=TaskPass")
    Call<TaskPassBean> TaskPass(@Field("taskname") String taskname);

    //    未通过
    @FormUrlEncoded
    @POST("/z_app.php?action=TaskUnPass")
    Call<TaskUnPassBean> TaskUnPass(@Field("admin") String admin,
                                    @Field("taskname") String taskname,
                                    @Field("reason") String reason,
                                    @Field("taskteacher") String taskteacher);




    //==========================================  查看课题与确定学生  ==========================================

    //获得题目状态
    @GET("/z_app.php?action=getProgress")
    Call<TaskStatusBean> getStatus();

    //                         查询新闻
    @GET("/z_app.php?action=check_news")
    Call<NewsListBean> newsList();


    //  列表显示题目
    @FormUrlEncoded
    @POST("/z_app.php?action=checkTask")
    Call<TaskListBean> TaskListShow(@Field("userID") String userID,@Field("role") String role);

    //  获得详细题目信息
    @FormUrlEncoded
    @POST("/z_app.php?action=checkTaskDetail")
    Call<TaskDetail> TaskDetailShow(@Field("taskName") String taskname);


    //  学生选择该课题
    @FormUrlEncoded
    @POST("/z_app.php?action=stuChooseTask")
    Call<ChooseTaskStuSelectBean> StuChooseTask( @Field("stuID") String stuID,@Field("taskName") String taskName);



    //    获得选择该题目的学生
    @FormUrlEncoded
    @POST("/z_app.php?action=CheckSelectedTaskStu")
    Call<ChooseTaskStuBean> ChooseTaskStuDetailShow(@Field("taskname") String taskName);



    //  确定该学生可以进行该课题
    @FormUrlEncoded
    @POST("/z_app.php?action=ConformTaskStu")
    Call<ChooseTaskStuConformBean> chooseTaskStuConformBean(@Field("taskname") String taskName,
                                                            @Field("stuID") String stuID);

    //  取消该学生选择该课题
    @FormUrlEncoded
    @POST("/z_app.php?action=DeleteTaskStu")
    Call<ChooseTaskStuDeleteBean> chooseTaskStuDeleteBean(@Field("taskname") String taskName, @Field("reason") String reason,@Field("stuID") String stuID);






    //==========================================   添加课题  ==========================================

    @FormUrlEncoded
    @POST("/z_app.php?action=addTask")
    Call<AddTaskBean> AddTasks(@Field("taskname") String taskname,
                               @Field("taskabstract") String taskabstract,
                               @Field("taskteacher") String taskteacher,
                               @Field("major") String major,
                               @Field("college") String college);

    //==========================================  课题进度功能  ==========================================
    //       查询课题进度
    @FormUrlEncoded
    @POST("/z_app.php?action=updateProgress")
    Call<ProgressUpate> ProgressUpdate(@Field("progress") String progress);

    //       查询已上传文件
    @FormUrlEncoded
    @POST("/z_app.php?action=CheckStuUploadFile")
    Call<CheckUploadTaskBean> checkUploadFile(@Field("userID") String userID,@Field("role") String role);



    //==========================================  通知功能  ==========================================
    //查询通知消息
    @FormUrlEncoded
    @POST("/z_app.php?action=checkInform")
    Call<InformDetailBean> InformListShow(@Field("userID") String userID,@Field("role") String role);

    //添加通知
    @FormUrlEncoded
    @POST("/z_app.php?action=AddInform")
    Call<AddInformBean> AddInfrom(@Field("userID") String userID,
                                  @Field("role") String role,
                                  @Field("informTitle") String InformTitle,
                                  @Field("informDetail") String InformDetail,
                                  @Field("major") String Major);

    //添加留言
    @FormUrlEncoded
    @POST("/z_app.php?action=AddMessage")
    Call<AddMessageBean> AddMessage(@Field("userID") String userID,
                                   @Field("message") String message);





    //=================================================文件下载==================================================
    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url);











    @FormUrlEncoded
    @POST("/app.php?action=check_history")
    Call<UserHistoryData> UserHistoryData(@Field("username") String username);

    @FormUrlEncoded
    @POST("/app.php?action=delete_img")
    Call<DeleteData> DeleteDatas(@Field("imgPath") String imgpath);

    @FormUrlEncoded
    @POST("/app.php?action=findscantextfile")
    Call<HistoryDataText> getScanDataFilePath(@Field("imgpath") String imgpath);

    @FormUrlEncoded
    @POST("/app.php?action=getscantext")
    Call<HistoryDataText> ShowScanText(@Field("imgscanfilepath") String imgscanfilepath);



    @Multipart
    @POST("/app.php?action=uploadnewpic")
    Observable<UploadNewPic> UploadnewpicRX(@Part("username") RequestBody username, @Part MultipartBody.Part file);


}
