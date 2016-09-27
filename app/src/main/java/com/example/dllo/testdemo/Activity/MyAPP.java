package com.example.dllo.testdemo.activity;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/9/20.
 * 注意!!!写完Application一定要注册
 */
public class MyAPP extends Application{
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }
}
