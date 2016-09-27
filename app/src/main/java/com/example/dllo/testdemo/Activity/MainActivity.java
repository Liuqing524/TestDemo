package com.example.dllo.testdemo.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.dllo.testdemo.adapter.MainAdapter;
import com.example.dllo.testdemo.base.BaseAty;
import com.example.dllo.testdemo.fragment.EquityFragment;
import com.example.dllo.testdemo.fragment.FoundFragment;
import com.example.dllo.testdemo.fragment.InformationFragment;
import com.example.dllo.testdemo.fragment.MyFragment;
import com.example.dllo.testdemo.fragment.NewsFragment;
import com.example.dllo.testdemo.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseAty {


    private ViewPager mainVp;
    private TabLayout mainTb;
    private MainAdapter mainAdapter;

    @Override
    protected int setLayout() {
    return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mainVp = bindView(R.id.main_kr_vp);
        mainTb = bindView(R.id.main_kr_tab);
        List<Fragment> datas = new ArrayList<>();
        datas.add(new NewsFragment());
        datas.add(new EquityFragment());
        datas.add(new FoundFragment());
        datas.add(new InformationFragment());
        datas.add(new MyFragment());
        mainAdapter = new MainAdapter(getSupportFragmentManager(),datas);
        mainVp.setAdapter(mainAdapter);
        mainTb.setupWithViewPager(mainVp);

    }

    @Override
    protected void initData() {
        mainTb.getTabAt(0).setIcon(R.drawable.news);
        mainTb.getTabAt(0).setText("新闻");

        mainTb.getTabAt(1).setIcon(R.drawable.equity);
        mainTb.getTabAt(1).setText("股权投资");

        mainTb.getTabAt(2).setIcon(R.drawable.information);
        mainTb.getTabAt(2).setText("发现");

        mainTb.getTabAt(3).setIcon(R.drawable.message);
        mainTb.getTabAt(3).setText("消息");

        mainTb.getTabAt(4).setIcon(R.drawable.mine);
        mainTb.getTabAt(4).setText("我的");

    }
}
