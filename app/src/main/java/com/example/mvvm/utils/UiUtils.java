package com.example.mvvm.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mvvm.App;

/**
 * 屏幕操作的工具类
 * Created by YouLe on 2018/3/15
 */

public class UiUtils {
    private static final String TAG = "UiUtils";

    public static int dp2px(float dp) {
        float density = App.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5);
    }

    public static int px2dp(Context context, float px) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }

    public static int sp2px(Context context, float sp) {
        float density = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * density + 0.5);
    }

    public static int px2sp(Context context, float px) {
        float density = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / density + 0.5);
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    /**
     * 设置TextView中drawable的大小
     *
     * @param textView
     * @param witch    0:左 1:上 2:右 3:下
     * @param dp
     */
    public static void setDrawableSize(TextView textView, int witch, int dp) {
        Drawable drawable = textView.getCompoundDrawables()[witch];
        drawable.setBounds(0, 0,
                dp2px(dp),
                dp2px(dp));
        switch (witch) {
            case 1:
                textView.setCompoundDrawables(null, drawable, null, null);
                break;
            case 2:
                textView.setCompoundDrawables(null, null, drawable, null);
                break;
            case 3:
                textView.setCompoundDrawables(null, null, null, drawable);
                break;
            case 0:
            default:
                textView.setCompoundDrawables(drawable, null, null, null);
                break;
        }
    }

    /**
     * log输出View的位置信息
     *
     * @param view
     */
    public static void logLocationInfo(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int[] location1 = new int[2];
        view.getLocationInWindow(location1);
        LogUtils.i(TAG, "onScreen : x = " + location[0] + ", y = " + location[1]);
        LogUtils.i(TAG, "inWindow : x = " + location1[0] + ", y = " + location1[1]);

        LogUtils.i(TAG, "view.getX() = " + view.getX());
        LogUtils.i(TAG, "view.getY() = " + view.getY());
        LogUtils.i(TAG, "view.getTranslationX() = " + view.getTranslationX());
        LogUtils.i(TAG, "view.getTranslationY() = " + view.getTranslationY());

        LogUtils.i(TAG, "view.getZ() = " + view.getZ());
        LogUtils.i(TAG, "view.getTranslationZ() = " + view.getTranslationZ());
        LogUtils.i(TAG, "view.getElevation() = " + view.getElevation());

        LogUtils.i(TAG, "view.getLeft() = " + view.getLeft());
        LogUtils.i(TAG, "view.getTop() = " + view.getTop());
        LogUtils.i(TAG, "view.getRight() = " + view.getRight());
        LogUtils.i(TAG, "view.getBottom() = " + view.getBottom());
        LogUtils.i(TAG, "view.getWidth() = " + view.getWidth());
        LogUtils.i(TAG, "view.getHeight() = " + view.getHeight());

        LogUtils.i(TAG, "view.getScrollX() = " + view.getScrollX());
        LogUtils.i(TAG, "view.getScrollY() = " + view.getScrollY());

        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        LogUtils.i(TAG, "mLayoutParams.leftMargin = " + params.leftMargin);
        LogUtils.i(TAG, "mLayoutParams.topMargin = " + params.topMargin);
        LogUtils.i(TAG, "mLayoutParams.rightMargin = " + params.rightMargin);
        LogUtils.i(TAG, "mLayoutParams.bottomMargin = " + params.bottomMargin);

        // android:layout_width 和 android:layout_height
        LogUtils.i(TAG, "mLayoutParams.width = " + params.width);
        LogUtils.i(TAG, "mLayoutParams.height = " + params.height);
    }

    /**
     * log输出设备屏幕信息
     */
    public static void logMetricsValues() {
        DisplayMetrics metrics = App.getInstance().getResources().getDisplayMetrics();
        LogUtils.i(TAG, "density: " + metrics.density);
        LogUtils.i(TAG, "scaledDensity: " + metrics.scaledDensity);
        LogUtils.i(TAG, "densityDpi: " + metrics.densityDpi);
        LogUtils.i(TAG, "xdpi: " + metrics.xdpi);
        LogUtils.i(TAG, "ydpi: " + metrics.ydpi);
        LogUtils.i(TAG, "widthPixels: " + metrics.widthPixels);
        LogUtils.i(TAG, "heightPixels: " + metrics.heightPixels);
        LogUtils.i(TAG, "widthDp: " + metrics.widthPixels / metrics.density);
        LogUtils.i(TAG, "heightDp: " + metrics.heightPixels / metrics.density);

    }

}
