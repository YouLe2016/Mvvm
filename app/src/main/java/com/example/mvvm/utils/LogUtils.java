package com.example.mvvm.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * Log工具类
 *
 * @author YouLe
 */

public class LogUtils {
    private LogUtils() {
    }

    private static boolean isDebug = true;

    private static String TAG = "Look";

    public static void setIsDebug(boolean isDebug, String tag) {
        LogUtils.isDebug = isDebug;
        LogUtils.TAG = TextUtils.isEmpty(tag) ? TAG : tag;
    }

    // 下面四个是默认tag的函数
    public static void v(String msg) {
        if (isDebug) {
            Log.v(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (isDebug) {
            Log.i(TAG, msg);
        }
    }

    public static void w(String msg) {
        if (isDebug) {
            Log.w(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }

    // 下面是传入自定义tag的函数
    public static void v(String tag, String msg) {
        if (isDebug) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isDebug) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }
}
