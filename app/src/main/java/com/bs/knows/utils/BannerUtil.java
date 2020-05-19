package com.bs.knows.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import com.bs.knows.R;
import com.bs.knows.databinding.ActivityAdminViewBinding;
import com.bs.knows.databinding.ActivityCheckTaskViewBinding;
import com.bs.knows.databinding.ActivityStudentViewBinding;
import com.bs.knows.databinding.ActivityTeacherViewBinding;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class BannerUtil {

    private static Banner mBanner;
    private static LocalImageLoader mImageLoader;
    private static ArrayList<String> list_path;
    private static ArrayList<String> imageTitle;
    private static ArrayList<String> BannerContentUrl;
    private static String str1 = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3865853263,11644748&fm=26&gp=0.jpg";
    private static String str2 = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1564246718,1778206304&fm=26&gp=0.jpg";
    private static String str3 = "https://www.buu.edu.cn/picture/0/1907041220228267875.jpg";
    public static void InitBannerStu(ActivityStudentViewBinding bindings, Context context) {
        initData();
        initViewStu(bindings, context);
    }

    public static void InitBannerTea(ActivityTeacherViewBinding binding, Context context) {
        initData();
        initViewTea(binding, context);
    }

    public static void InitBannerAdm(ActivityAdminViewBinding binding, Context context) {
        initData();
        initViewAdm(binding, context);
    }

    private static void initData() {
        list_path = new ArrayList<>();
        imageTitle = new ArrayList<>();
        BannerContentUrl = new ArrayList<>();

        list_path.add(str1);
        list_path.add(str2);
        list_path.add(str3);
        imageTitle.add("北京联合大学");
        imageTitle.add("北京联合大学机器人学院");
        imageTitle.add("北京联合大学校园风光");
        BannerContentUrl.add("https://www.buu.edu.cn/");
        BannerContentUrl.add("https://robotics.buu.edu.cn/");
        BannerContentUrl.add("https://www.buu.edu.cn/col/col144/index.html");

    }

    private static void initViewStu(ActivityStudentViewBinding binding, Context context) {
        mImageLoader = new LocalImageLoader();
        mBanner = binding.bannerMain;
        //样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //加载器
        mBanner.setImageLoader(mImageLoader);
        //动画效果
        mBanner.setBannerAnimation(Transformer.ZoomOutSlide);
        //图片标题
        mBanner.setBannerTitles(imageTitle);
        //间隔时间
        mBanner.setDelayTime(4500);
        //是否为自动轮播
        mBanner.isAutoPlay(true);
        //图片小点显示所在位置
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //图片加载地址
        mBanner.setImages(list_path);
        //启动轮播图。
        mBanner.start();
        //监听轮播图
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                BannerOnClick(context,position);
            }
        });
    }

    private static void initViewTea(ActivityTeacherViewBinding binding, Context context) {
        mImageLoader = new LocalImageLoader();
        mBanner = binding.bannerMain;
        //样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //加载器
        mBanner.setImageLoader(mImageLoader);
        //动画效果
        mBanner.setBannerAnimation(Transformer.ZoomOutSlide);
        //图片标题
        mBanner.setBannerTitles(imageTitle);
        //间隔时间
        mBanner.setDelayTime(4500);
        //是否为自动轮播
        mBanner.isAutoPlay(true);
        //图片小点显示所在位置
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //图片加载地址
        mBanner.setImages(list_path);
        //启动轮播图。
        mBanner.start();
        //监听轮播图
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                BannerOnClick(context,position);
            }
        });
    }

    private static class LocalImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load((String) path)
                    .into(imageView);
        }
    }

    private static void initViewAdm(ActivityAdminViewBinding binding, Context context) {
        mImageLoader = new LocalImageLoader();
        mBanner = binding.bannerMain;
        //样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //加载器
        mBanner.setImageLoader(mImageLoader);
        //动画效果
        mBanner.setBannerAnimation(Transformer.ZoomOutSlide);
        //图片标题
        mBanner.setBannerTitles(imageTitle);
        //间隔时间
        mBanner.setDelayTime(4500);
        //是否为自动轮播
        mBanner.isAutoPlay(true);
        //图片小点显示所在位置
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //图片加载地址
        mBanner.setImages(list_path);
        //启动轮播图。
        mBanner.start();
        //监听轮播图
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                BannerOnClick(context,position);
            }
        });
    }

    private static void BannerOnClick(Context context,int i) {
        Intent intent = new Intent();
        intent.setData(Uri.parse(BannerContentUrl.get(i)));//Url 就是你要打开的网址
        intent.setAction(Intent.ACTION_VIEW);
        context.startActivity(intent); //启动浏览器
    }

}

