package com.example.dllo.testdemo.dialog;

import android.view.View;
import android.widget.ImageButton;

import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.base.BaseAty;

/**
 * Created by dllo on 16/10/8.
 */
public class InformationLikeActivity extends BaseAty implements View.OnClickListener{
    private ImageButton back;
    @Override
    protected int setLayout() {
        return R.layout.infor_like_activity;
    }

    @Override
    protected void initView() {
back = bindView(R.id.infor_like_back);
        back.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.infor_like_back:
                finish();
                break;
        }
    }
}
