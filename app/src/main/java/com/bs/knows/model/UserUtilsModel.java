package com.bs.knows.model;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bs.knows.R;
import com.bs.knows.databinding.ActivityRegisterBinding;
import com.bs.knows.utils.StaticUtils;
import com.bs.knows.view.AdminActivityView;
import com.bs.knows.view.LoginActivityView;
import com.bs.knows.view.MineActivityView;
import com.bs.knows.connect.Api;
import com.bs.knows.connect.bean.UserRegister;
import com.bs.knows.connect.bean.UserUpdatePs;
import com.bs.knows.connect.bean.CheckUserId;
import com.bs.knows.connect.bean.CheckUserPswd;
import com.bs.knows.connect.InitRetrofit;
import com.bs.knows.databinding.ActivityMineBinding;
import com.bs.knows.utils.SPUtils;
import com.bs.knows.view.StudentActivityView;
import com.bs.knows.view.TeacherActivityView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class UserUtilsModel {

    private static String TAG = "TAG";


//    ======================================================  用户登录  ======================================================

    /**
     * 用户登录
     *
     * @param context  获得View
     * @param phone    用户名
     * @param password 密码
     */
    public static void userLogin(final Context context, int id, String phone, String password) {
        String role = "";
        if (!validateLogin(context, phone, password)) {
            return;
        }
        switch (id) {
            case R.id.rb_student:
                role = "学生";
                break;
            case R.id.rb_teacher:
                role = "教师";
                break;
            case R.id.rb_admin:
                role = "管理员";
                break;
            default:
                Toast.makeText(context, "请选择登录角色", Toast.LENGTH_SHORT).show();
                return;
        }


        Retrofit retrofit = InitRetrofit.getUserData();
        Api api = retrofit.create(Api.class);
        String finalRole = role;
        api.UserIsExist(phone, role).enqueue(new Callback<CheckUserId>() {
            @Override
            public void onResponse(Call<CheckUserId> call, Response<CheckUserId> response) {
                assert response.body() != null;
                boolean isExist = response.body().isUserIsExist();

                if (isExist) {
                    api.UserDataPasswdisCorrect(phone, password, finalRole).enqueue(new Callback<CheckUserPswd>() {
                        @Override
                        public void onResponse(Call<CheckUserPswd> call, Response<CheckUserPswd> response) {
                            assert response.body() != null;
                            boolean isPassword = response.body().isPassword();
                            boolean isE = response.body().isError();
                            Log.d(TAG, "onResponse: " + isPassword + isE);
                            if (isPassword) {
                                //保存用户登录标记，若失败则不可登录
                                boolean isSave = SPUtils.saveUser(context, phone, finalRole);
                                if (!isSave) {
                                    Toast.makeText(context, "系统错误！请稍后重试", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "登录成功！", Toast.LENGTH_SHORT).show();
                                    ToActivity(context, finalRole, phone);
                                }
                            } else {
                                Toast.makeText(context, "登录失败！密码错误！", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<CheckUserPswd> call, Throwable t) {
                            Toast.makeText(context, "系统错误！请稍后重试", Toast.LENGTH_SHORT).show();
                        }
                    });


                } else {
                    Toast.makeText(context, "用户名不存在！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CheckUserId> call, Throwable t) {
                Toast.makeText(context, "系统错误！请稍后重试", Toast.LENGTH_SHORT).show();
            }
        });

    }


//    ====================================================  用户自动登录  ====================================================

    /**
     * 1.用户登录
     * 当用户登录应用程序时，利用SharedPreferences保存用户标记
     * 用全局单例类UserIsLogin保存登录信息
     * 在用户登录之后
     * 在用户重新打开程序时，检测SharedPreferences是否保存标记，
     * 如果存在则为UserIsLogin辅助，并进入主页。若不存在，则进入登录页面
     * <p>
     * 2.用户退出
     * 删除SharedPreferences中的标记，退出到登录页面
     */

    public static class UserIsLogin {
        private static UserIsLogin instance;

        private UserIsLogin() {


        }

        /**
         * 验证是否存在已登录用户
         *
         * @return 返回标识符
         */
        public static boolean validateUserLogin(Context context) {
            return SPUtils.isLoginUser(context);
        }

        public static String validateUserLoginRole(Context context) {
            return SPUtils.LoginUserRole(context);
        }


        public static UserIsLogin getInstance() {
            if (instance == null) {
                synchronized (UserIsLogin.class) {
                    if (instance == null) {
                        instance = new UserIsLogin();
                    }
                }
            }
            return instance;
        }


        private String phone;
        private String role;

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

    }
    //    ======================================================  用户信息  ======================================================

    @SuppressLint("SetTextI18n")
    public static void setUserName(ActivityMineBinding binding) {
        binding.tvUser.setText("当前登录" + UserIsLogin.getInstance().getRole() + ":" + UserIsLogin.getInstance().getPhone());
    }


    //    ======================================================  用户注册  ======================================================

    /**
     * 账号密码注册
     *
     * @param context 获得View
     * @param binding binding
     */
    public static void signUp(final Context context, ActivityRegisterBinding binding) {
        String role = "";
        int id = binding.RGRegister.getCheckedRadioButtonId();
        String phone = binding.inputIDRegister.getInputStr();
        String password = binding.inputPasswordRegister.getInputStr();
        String repassword = binding.inputRepasswordRegister.getInputStr();
        String major = binding.inputMajorRegister.getInputStr();
        String college = binding.inputCollegeRegister.getInputStr();
        String name = binding.inputUsernameRegister.getInputStr();

        switch (id) {
            case R.id.rb_student_re:
                role = "学生";
                break;
            case R.id.rb_teacher_re:
                role = "教师";
                break;
            case R.id.rb_admin_re:
                role = "管理员";
                break;
            default:
                Toast.makeText(context, "请选择注册角色", Toast.LENGTH_SHORT).show();
                return;
        }
        if (!validateRegister(context, phone, password, repassword, major, college, name, role)) {
            return;
        }

        Retrofit retrofit = InitRetrofit.getUserData();
        Api api = retrofit.create(Api.class);
        String finalRole = role;
        api.UserIsExist(phone, role).enqueue(new Callback<CheckUserId>() {
            @Override
            public void onResponse(Call<CheckUserId> call, Response<CheckUserId> response) {
                boolean isExist = response.body().isUserIsExist();
                if (!isExist) {
                    api.UserRegister(phone, password, finalRole, major, college, name).enqueue(new Callback<UserRegister>() {
                        @Override
                        public void onResponse(Call<UserRegister> call, Response<UserRegister> response) {
                            boolean isRegister = response.body().isRegisterSuccess();
                            if (isRegister) {
                                //保存用户登录标记，若失败则不可登录
                                boolean isSave = SPUtils.saveUser(context, phone, finalRole);
                                if (!isSave) {
                                    Toast.makeText(context, "系统错误！请稍后重试", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(context, "注册成功！", Toast.LENGTH_SHORT).show();
                                    ToActivity(context, finalRole, phone);
                                }
                            } else {
                                Toast.makeText(context, "注册失败！请稍后再试！", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserRegister> call, Throwable t) {

                        }
                    });
                } else {
                    Toast.makeText(context, "注册失败！用户已存在！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CheckUserId> call, Throwable t) {

            }
        });


    }

    // admin 添加用户
    public static void AddUser(final Context context, ActivityRegisterBinding binding, Intent intent) {
        String role = "";
        binding.RGRegister.setVisibility(View.GONE);
        String phone = binding.inputIDRegister.getInputStr();
        String password = binding.inputPasswordRegister.getInputStr();
        String repassword = binding.inputRepasswordRegister.getInputStr();
        String major = binding.inputMajorRegister.getInputStr();
        String college = binding.inputCollegeRegister.getInputStr();
        String name = binding.inputUsernameRegister.getInputStr();

        switch (intent.getStringExtra("from")) {
            case "Admin_Add_Stu":
                role = "学生";
                break;
            case "Admin_Add_Tea":
                role = "教师";
                break;

            default:
                return;
        }
        if (!validateRegister(context, phone, password, repassword, major, college, name, role)) {
            return;
        }

        Retrofit retrofit = InitRetrofit.getUserData();
        Api api = retrofit.create(Api.class);
        String finalRole = role;
        api.UserIsExist(phone, role).enqueue(new Callback<CheckUserId>() {
            @Override
            public void onResponse(Call<CheckUserId> call, Response<CheckUserId> response) {
                boolean isExist = response.body().isUserIsExist();
                if (!isExist) {
                    api.UserRegister(phone, password, finalRole, major, college, name).enqueue(new Callback<UserRegister>() {
                        @Override
                        public void onResponse(Call<UserRegister> call, Response<UserRegister> response) {
                            boolean isRegister = response.body().isRegisterSuccess();
                            if (isRegister) {
                                //保存用户登录标记，若失败则不可登录
                                boolean isSave = SPUtils.saveUser(context, phone, finalRole);
                                if (!isSave) {
                                    Toast.makeText(context, "系统错误！请稍后重试", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(context, "添加成功！", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(context, "注册失败！请稍后再试！", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserRegister> call, Throwable t) {

                        }
                    });
                } else {
                    Toast.makeText(context, "注册失败！用户已存在！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CheckUserId> call, Throwable t) {

            }
        });


    }


//    ======================================================  用户退出登录  ======================================================

    /**
     * 退出登录
     *
     * @param context 获得View
     */
    public static void logout(Context context) {
//        删除sp保存的用户标记
        boolean isRemove = SPUtils.removeUser(context);
        if (!isRemove) {
            Toast.makeText(context, "系统错误!请稍后重试", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(context, LoginActivityView.class);
        StaticUtils.STU_FINISH_CHOOSE_TASK = false;
        StaticUtils.STU_READ_CHOOSE_TASK = false;
        StaticUtils.STU__CHOOSE_TASK_NAME = "1";
        StaticUtils.TASK_STATUS = "1";
//        添加intent标志符，清理task栈，并且新生成一个task栈
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
//        定义Activity跳转动画
        ((Activity) context).overridePendingTransition(R.anim.open_enter, R.anim.open_exit);
    }

//    ======================================================  用户修改密码  ======================================================

    /**
     * 用户修改密码
     *
     * @param context        获得view
     * @param old_password   旧密码
     * @param new_password   新密码
     * @param new_repassword 重新输入新密码
     */
    public static void updatePassword(final Context context, String old_password, String new_password, String new_repassword) {
        if (!validateUpdatePassword(context, old_password, new_password, new_repassword)) {
            return;
        }

        Retrofit retrofit = InitRetrofit.getUserData();
        Api api = retrofit.create(Api.class);
        Log.d(TAG, "updatePassword: " + UserIsLogin.getInstance().getPhone());
        api.UserUpdatePs(UserIsLogin.getInstance().getPhone(), UserIsLogin.getInstance().getRole(),old_password, new_password).enqueue(new Callback<UserUpdatePs>() {
            @Override
            public void onResponse(Call<UserUpdatePs> call, Response<UserUpdatePs> response) {
                if (response.body().isOld_password()) {
                    if (response.body().isUpdateSuccess()) {
                        Toast.makeText(context, "密码修改成功！", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, MineActivityView.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        //        定义Activity跳转动画
                        ((Activity) context).overridePendingTransition(R.anim.open_enter, R.anim.open_exit);
                    }
                } else {
                    Toast.makeText(context, "修改密码失败！旧密码错误！", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserUpdatePs> call, Throwable t) {

            }
        });

    }

//    ==============================================================================================================================

    private static void ToActivity(Context context, String finalRole1, String phone) {
        if (finalRole1.equals("学生")) {
            Intent intent = new Intent(context, StudentActivityView.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            //定义Activity跳转动画
            ((Activity) context).overridePendingTransition(R.anim.close_enter, R.anim.close_exit);
            //保存用户标记（手机号）
            UserIsLogin.getInstance().setPhone(phone);
            UserIsLogin.getInstance().setRole(finalRole1);
        }
        if (finalRole1.equals("教师")) {
            Intent intent = new Intent(context, TeacherActivityView.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            //定义Activity跳转动画
            ((Activity) context).overridePendingTransition(R.anim.close_enter, R.anim.close_exit);
            //保存用户标记（手机号）
            UserIsLogin.getInstance().setPhone(phone);
            UserIsLogin.getInstance().setRole(finalRole1);
        }
        if (finalRole1.equals("管理员")) {
            Intent intent = new Intent(context, AdminActivityView.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            //定义Activity跳转动画
            ((Activity) context).overridePendingTransition(R.anim.close_enter, R.anim.close_exit);
            //保存用户标记（手机号）
            UserIsLogin.getInstance().setPhone(phone);
            UserIsLogin.getInstance().setRole(finalRole1);
        }

    }


    /**
     * 验证登录输入框内容
     *
     * @param context  获得View
     * @param phone    Username
     * @param password UserPassword
     * @return boolean
     */
    private static boolean validateLogin(Context context, String phone, String password) {

        //        精确验证手机号
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(context, "请输入学号", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    /**
     * 验证注册输入框内容
     *
     * @param context    获得View
     * @param phone      Username
     * @param password   UserPassword
     * @param repassword UserRePassword
     * @return boolean
     */
    private static boolean validateRegister(Context context, String phone, String password, String repassword, String major, String college, String name, String role) {

//        精确地
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(context, "请输入学号/员工号", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(repassword)) {
            Toast.makeText(context, "请再次输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!password.equals(repassword)) {
            Toast.makeText(context, "两次新密码输入不一致，请重新输入", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!role.equals("管理员")) {
            if (TextUtils.isEmpty(major)) {
                Toast.makeText(context, "请输入专业", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(college)) {
                Toast.makeText(context, "请输入学院", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(context, "请输入姓名", Toast.LENGTH_SHORT).show();
                return false;
            }
        }


        return true;
    }


    /**
     * 验证重置密码输入框内容
     *
     * @param context        获得View
     * @param old_password   旧密码
     * @param new_password   新密码
     * @param new_repassword 重新输入新密码
     * @return boolean
     */
    private static boolean validateUpdatePassword(Context context, String old_password, String new_password, String new_repassword) {
//        简单的
//        RegexUtils.isMobileSimple(phone);
//        精确地
        if (TextUtils.isEmpty(old_password)) {
            Toast.makeText(context, "请输入旧密码", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(new_password)) {
            Toast.makeText(context, "请输入新密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(new_repassword)) {
            Toast.makeText(context, "请再次输入新密码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!new_password.equals(new_repassword)) {
            Toast.makeText(context, "两次新密码输入不一致，请重新输入", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    static boolean checkInput(Context context, String TaskTitle, String TaskDetail, String TaskMajor, String college) {
//        简单的
//        RegexUtils.isMobileSimple(phone);
//        精确地
        if (TextUtils.isEmpty(TaskTitle)) {
            Toast.makeText(context, "请输入课题标题", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(TaskDetail)) {
            Toast.makeText(context, "请输入课题内容", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(TaskMajor)) {
            Toast.makeText(context, "请输入课题适合专业", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(college)) {
            Toast.makeText(context, "请输入发布学院", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


}