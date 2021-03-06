package com.example.dllo.testdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class InFormationAdapterTwo extends FragmentPagerAdapter{
    ArrayList<String> titleArr = new ArrayList<>();
    ArrayList<Fragment> fragments;
    public InFormationAdapterTwo(FragmentManager fm , ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
        titleArr.add("登录");
        titleArr.add("注册");
    }

    public InFormationAdapterTwo(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleArr.get(position);
    }
}
