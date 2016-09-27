package com.example.dllo.testdemo.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dllo.testdemo.R;

/**
 * Created by dllo on 16/9/26.
 */
public class NewsDetailsShareActivity extends Activity implements View.OnClickListener{
    private Button shareBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_news_details_share);
        getWindow().setLayout(ViewPager.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        shareBack = (Button) findViewById(R.id.share_back);
        shareBack.setOnClickListener(this);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_back:
                finish();
                break;
        }

    }
}
