package com.example.dllo.testdemo.fragment;

import android.view.View;
import android.widget.ImageButton;

import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.base.BaseFragment;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by dllo on 16/9/19.
 */
public class informationLoginFragment extends BaseFragment implements View.OnClickListener{
    private ImageButton weibo,qqLogin;
    @Override
    protected int setLayout() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView() {
        weibo = bindView(R.id.kr_information_login_weibo);
        qqLogin = bindView(R.id.kr_information_login_qq);

    }

    @Override
    protected void initData() {
        weibo.setOnClickListener(this);
        qqLogin.setOnClickListener(this);
        ShareSDK.initSDK(getContext(),"sharesdk的appkey");

    }

    @Override
    public void onClick(View v) {
        PlatformActionListener paListener = null;
        switch (v.getId()) {
            case R.id.kr_information_login_weibo:
                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);

                weibo.setPlatformActionListener(paListener);
//authorize与showUser单独调用一个即可
                weibo.authorize();//单独授权,OnComplete返回的hashmap是空的
                weibo.showUser(null);//授权并获取用户信息
                break;
            case R.id.kr_information_login_qq:
                Platform qq = ShareSDK.getPlatform(QQ.NAME);

                qq.setPlatformActionListener(paListener);
//authorize与showUser单独调用一个即可
                qq.authorize();//单独授权,OnComplete返回的hashmap是空的
                qq.showUser(null);//授权并获取用户信息
                break;
        }
    }
}
