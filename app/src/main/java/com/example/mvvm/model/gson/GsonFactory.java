package com.example.mvvm.model.gson;

import com.google.gson.Gson;

/**
 * 时间：2018/12/1 9:35
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 *
 * @author WangYoule
 * @qq 270628297
 */
public class GsonFactory {
    private static Gson gson = new Gson();

    public static Gson getGson() {
        return gson;
    }
}
