package com.example.dllo.testdemo.dialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.ViewGroup;

import com.example.dllo.testdemo.R;

/**
 * Created by dllo on 16/10/9.
 */
public class EquityGiftActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equity_gift_activity);
        getWindow().setLayout(ViewPager.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Intent intent = new Intent("gift");
        sendBroadcast(intent);

        finish();
        return true;
    }
}
