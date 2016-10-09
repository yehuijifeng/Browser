package com.lh.browser.utils;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.Window;

/**
 * Created by Luhao on 2016/10/9.
 */
public class WindowUtils {

    /**
     * 沉浸式title
     *
     * @param activity
     */
    public static void getImmersion(AppCompatActivity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) return;
        //沉浸式设置
        //获取到了当前界面的DecorView,装饰视图
        View decorView = activity.getWindow().getDecorView();
        //设置系统UI元素的可见性，SYSTEM_UI_FLAG_FULLSCREEN表示全屏的意思
//        decorView.setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        //注意顺序一定不能错,表示会让应用的主体内容占用系统状态栏的空间
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        //将状态栏设置成透明色
        activity.getWindow().setNavigationBarColor(Color.TRANSPARENT);
        activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        //调用ActionBar的hide()方法将ActionBar也进行隐藏。
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();
    }

    /**
     * 获得手机屏幕大小，包含通知栏、title
     *
     * @param activity
     * @return
     */
    public static int getWindowHeight(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) return 0;
        //获得默认显示区域
        Display disp = activity.getWindowManager().getDefaultDisplay();
        //初始化坐标点
        Point outP = new Point();
        //将默认坐标显示在
        disp.getSize(outP);
        //屏幕区域，包含状态栏，title
        return outP.y;
    }

    /**
     * 获得手机屏幕大小，包含通知栏、title
     *
     * @param activity
     * @return
     */
    public static int getWindowWidth(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) return 0;
        //获得默认显示区域
        Display disp = activity.getWindowManager().getDefaultDisplay();
        //初始化坐标点
        Point outP = new Point();
        //将默认坐标显示在
        disp.getSize(outP);
        //屏幕区域，包含状态栏，title
        return outP.x;
    }

    /**
     * 获得app应用的大小，包含title，没有通知栏
     *
     * @param activity
     * @return
     */
    public static int getAPPHeight(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) return 0;
        //初始化坐标显示的矩形区域
        Rect outRect = new Rect();
        //应用区域，包含title；没有状态栏
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
        return outRect.height();
    }

    /**
     * 获得app应用的区域大小，包含title、没有通知栏
     *
     * @param activity
     * @return
     */
    public static int getAppWidth(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) return 0;
        //初始化坐标显示的矩形区域
        Rect outRect = new Rect();
        //应用区域，包含title；没有状态栏
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
        return outRect.width();
    }

    /**
     * 获得title的高度
     *
     * @param activity
     * @return
     */
    public static int getTitleHeigth(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) return 0;
        //初始化坐标显示的矩形区域
        Rect outRect = new Rect();
        //应用区域，包含title；没有状态栏
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
        //view绘制区d域，没有title，状态栏
        return outRect.top;
    }

    /**
     * 获得View绘制区域的大小，不包含标题栏、title
     *
     * @param activity
     * @return
     */
    public static int getViewHeight(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) return 0;
        //初始化坐标显示的矩形区域
        Rect outRect = new Rect();
        //view绘制区域，没有title，状态栏
        activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(outRect);
        return outRect.height();
    }

    /**
     * 获得View绘制区域的大小，不包含标题栏、title
     *
     * @param activity
     * @return
     */
    public static int getViewWidth(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) return 0;
        //初始化坐标显示的矩形区域
        Rect outRect = new Rect();
        //view绘制区域，没有title，状态栏
        activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(outRect);
        return outRect.width();
    }
}
