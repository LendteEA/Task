package com.bs.knows.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.bs.knows.model.UserUtilsModel;

/**
 * 保存SharedPreferences标记
 */
public class SPUtils {

    /**
     * 当用户登录应用程序时，利用SharedPreferences保存用户标记
     */
    public static boolean saveUser(Context context, String phone,String role) {
        SharedPreferences sp_user = context.getSharedPreferences(SPconstants.SP_NAME_USER, Context.MODE_PRIVATE);
        SharedPreferences sp_role = context.getSharedPreferences(SPconstants.SP_LOGIN_ROLE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor_user = sp_user.edit();
        SharedPreferences.Editor editor_login = sp_role.edit();
        editor_user.putString(SPconstants.SP_KEY_PHONE, phone);
        editor_login.putString(SPconstants.SP_LOGIN_ROLE,role);
        boolean result = editor_user.commit()&&editor_login.commit();//保存成功则为true 反之为false
        return result;
    }



    /**
     * 验证是否存在已登录用户
     */
    public static boolean isLoginUser(Context context) {
        boolean result = false;
        SharedPreferences sp = context.getSharedPreferences(SPconstants.SP_NAME_USER, Context.MODE_PRIVATE);
        String phone = sp.getString(SPconstants.SP_KEY_PHONE, "");
        if (!TextUtils.isEmpty(phone)) {
            result = true;
            UserUtilsModel.UserIsLogin.getInstance().setPhone(phone);
        }

        return result;
    }

    public static String LoginUserRole(Context context) {

        SharedPreferences sp = context.getSharedPreferences(SPconstants.SP_LOGIN_ROLE, Context.MODE_PRIVATE);
        String role = sp.getString(SPconstants.SP_LOGIN_ROLE, "");
        if (!TextUtils.isEmpty(role)) {
            UserUtilsModel.UserIsLogin.getInstance().setRole(role);
            return role;

        }

        return role;
    }


    /**
     * 删除用户标记
     */

    public static boolean removeUser(Context context) {
        SharedPreferences sp_user = context.getSharedPreferences(SPconstants.SP_NAME_USER, Context.MODE_PRIVATE);
        SharedPreferences sp_role = context.getSharedPreferences(SPconstants.SP_LOGIN_ROLE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor_user = sp_user.edit();
        SharedPreferences.Editor editor_login = sp_role.edit();
        editor_user.remove(SPconstants.SP_KEY_PHONE);
        editor_login.remove(SPconstants.SP_LOGIN_ROLE);
        boolean result = editor_user.commit()&&editor_login.commit();//保存成功则为true 反之为false
        return result;

    }
}
