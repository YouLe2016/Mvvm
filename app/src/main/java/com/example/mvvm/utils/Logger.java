package com.example.mvvm.utils;

import android.support.annotation.NonNull;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 时间：2018/10/11 17:24
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
public class Logger implements HttpLoggingInterceptor.Logger {
    private static final String TAG = "OKHttp";

    @Override
    public void log(@NonNull String message) {
        LogUtils.i(TAG, message);
//        print(message);
    }

    /**
     * 另一种输出方法
     *
     * @param message
     */
    private void print(@NonNull String message) {
        try {
            String text = URLDecoder.decode(message, "utf-8");
            LogUtils.i(TAG, text);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            LogUtils.i(TAG, message);
        }
    }
}
