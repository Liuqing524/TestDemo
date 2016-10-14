package com.example.dllo.testdemo.dialog;

import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.base.BaseAty;
import com.example.dllo.testdemo.base.DataCleanManager;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by dllo on 16/10/8.
 */
public class MySettingActivity extends BaseAty{
    private Button clearData, data;
    private Switch aSwitch;
    @Override
    protected int setLayout() {
        return R.layout.my_setting_activity;
    }

    @Override
    protected void initView() {
        clearData = bindView(R.id.my_setting_clear);
        data = bindView(R.id.my_setting_get);
        aSwitch = bindView(R.id.switch_btn);


    }

    @Override
    protected void initData() {
        try {
            data.setText(DataCleanManager.getTotalCacheSize(MySettingActivity.this));
            clearData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MySettingActivity.this, "qing", Toast.LENGTH_SHORT).show();
                    DataCleanManager.clearAllCache(MySettingActivity.this);
                    try {
                        data.setText(DataCleanManager.getTotalCacheSize(MySettingActivity.this));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    JPushInterface.resumePush(getApplicationContext());
                } else {
                    JPushInterface.stopPush(getApplicationContext());
                }

            }
        });

    }


}
