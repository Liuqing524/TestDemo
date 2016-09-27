package com.example.dllo.testdemo.dialog;

import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.base.BaseAty;

/**
 * Created by dllo on 16/9/27.
 */
public class RotateActivity extends BaseAty{
    private WebView wv;
    private String url1;
    @Override
    protected int setLayout() {
        return R.layout.activity_rotate;
    }

    @Override
    protected void initView() {
        Intent intent=getIntent();
        url1 = intent.getStringExtra("URL");
       wv = bindView(R.id.wv);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl(url1);
        wv.getSettings().setUseWideViewPort(true);
        wv.getSettings().setLoadWithOverviewMode(true);
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                wv.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }

    @Override
    protected void initData() {



    }
}
