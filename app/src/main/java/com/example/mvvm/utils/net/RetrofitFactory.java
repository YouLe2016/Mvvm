package com.example.mvvm.utils.net;

import com.example.mvvm.utils.Logger;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

/**
 * 时间：2018/11/26 13:59
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
public class RetrofitFactory {
    private static Retrofit retrofit;

    static {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new Logger()).setLevel(BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static Retrofit getInstance() {
        return retrofit;
    }
}
