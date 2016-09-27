package com.example.dllo.testdemo.activity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by dllo on 16/9/20.
 */
public class VolleySingleton {
    private static VolleySingleton mVolleySingleton;
    // 把请求队列放到单利中,这样整个项目就只有一个RequestQueue
    private RequestQueue mRequestQueue;


    private VolleySingleton(){
        mRequestQueue = Volley.newRequestQueue(MyAPP.getContext());
    }

    public static VolleySingleton getInstance() {
        if (mVolleySingleton == null) {
        synchronized (VolleySingleton.class){
        if (mVolleySingleton == null) {
            mVolleySingleton = new VolleySingleton();
        }
        }
        }
        return new VolleySingleton();
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    /**
     * 把请求加到请求队列里
     * @param request 各种网络请求
     */
    public void addRequest(Request request) {
        mRequestQueue.add(request);
    }
}
