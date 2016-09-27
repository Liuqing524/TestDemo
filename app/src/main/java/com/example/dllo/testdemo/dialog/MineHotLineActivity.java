package com.example.dllo.testdemo.dialog;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dllo.testdemo.R;

/**
 * Created by dllo on 16/9/26.
 */
public class MineHotLineActivity extends Activity implements View.OnClickListener{
    private TextView hotLine,hotBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotline);
        getWindow().setLayout(ViewPager.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        hotLine = (TextView) findViewById(R.id.hotline_num);
        hotBack = (TextView) findViewById(R.id.hotline_back);
        hotBack.setOnClickListener(this);
        hotLine.setOnClickListener(this);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hotline_num:
                Intent intent = new Intent();
                intent.setAction(intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + "400-995-3636");
                intent.setData(data);
                startActivity(intent);
                break;
            case R.id.hotline_back:
                finish();
                break;
        }
    }
}
