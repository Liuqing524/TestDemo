package com.example.dllo.testdemo.Base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dllo on 16/9/19.
 * Activity的积累
 */
public abstract class BaseAty extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());

        initView();
        initData();
    }

    /**
     * 设置布局
     * @return 布局文件的 id
     */
    protected abstract int setLayout();

    /**
     * 初始化 View, 执行 FindViewById 的操作
     */
    protected abstract void initView();

    /**
     *
     * 初始化数据,例如拉取网络数据,或者一些逻辑代码
     */
    protected abstract void initData();

    /**
     * 简化findViewById的操作
     * @param id 组件 Id
     * @param <T>
     * @return
     */
    protected <T extends View> T bindView(int id) {
        return (T) findViewById(id);
    }

}
