package com.example.dllo.testdemo.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.dllo.testdemo.adapter.InFormationAdapterTwo;
import com.example.dllo.testdemo.base.BaseAty;
import com.example.dllo.testdemo.fragment.informationLoginFragment;
import com.example.dllo.testdemo.fragment.informationRegisterFragment;
import com.example.dllo.testdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/9/20.
 */
public class InformationActivity extends BaseAty{
    private ViewPager viewPager;
    private TabLayout tb;
    private InFormationAdapterTwo adapterTwo;
    @Override
    protected int setLayout() {
        return R.layout.fragment_informationtwo;
    }

    @Override
    protected void initView() {
    viewPager = bindView(R.id.kr_information_viewpager);
        tb = bindView(R.id.kr_information_tab);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new informationLoginFragment());
        fragments.add(new informationRegisterFragment());
        adapterTwo = new InFormationAdapterTwo(getSupportFragmentManager(), (ArrayList<Fragment>) fragments);
        viewPager.setAdapter(adapterTwo);
        tb.setupWithViewPager(viewPager);


    }

    @Override
    protected void initData() {
        tb.getTabAt(0).setText("登录");

        tb.getTabAt(1).setText("注册");

    }
}
