package com.example.dllo.testdemo.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.activity.InformationActivity;
import com.example.dllo.testdemo.base.BaseFragment;
import com.example.dllo.testdemo.dialog.MineHotLineActivity;
import com.example.dllo.testdemo.dialog.MyOrderActivity;
import com.example.dllo.testdemo.dialog.MySettingActivity;

/**
 * Created by dllo on 16/9/19.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener{
    private Button hotLine , myOrder;
    private ImageButton setting;
    private ImageView titlePage;

    @Override
    protected int setLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {
        hotLine = bindView(R.id.kr_myhotline);
        myOrder = bindView(R.id.kr_myorder);
        setting = bindView(R.id.kr_my_setting);
        titlePage = bindView(R.id.kr_my_iv);

        hotLine.setOnClickListener(this);
        myOrder.setOnClickListener(this);
        setting.setOnClickListener(this);
        titlePage.setOnClickListener(this);



    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.kr_myhotline:
                startActivity(new Intent(getActivity(), MineHotLineActivity.class));
                break;
            case R.id.kr_myorder:
                   startActivity(new Intent(getActivity() , MyOrderActivity.class));
                break;
            case R.id.kr_my_setting:
                   startActivity(new Intent(getActivity() , MySettingActivity.class));
                break;
            case R.id.kr_my_iv:
                startActivity(new Intent(getActivity() , InformationActivity.class));
                break;

        }
    }
}
