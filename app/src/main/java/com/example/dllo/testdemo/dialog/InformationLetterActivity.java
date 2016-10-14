package com.example.dllo.testdemo.dialog;

import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.base.BaseAty;

/**
 * Created by dllo on 16/10/8.
 */
public class InformationLetterActivity extends BaseAty implements View.OnClickListener{
    private ImageButton back;
    private RelativeLayout le;
    @Override
    protected int setLayout() {
        return R.layout.information_letter_activity;
    }

    @Override
    protected void initView() {
        back = bindView(R.id.infor_letter_back);
        le = bindView(R.id.kr_infor_private_letter);
        back.setOnClickListener(this);
        le.setOnClickListener(this);


    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.infor_letter_back:
                finish();
                break;
            case R.id.kr_infor_private_letter:
                break;
        }

    }
}
