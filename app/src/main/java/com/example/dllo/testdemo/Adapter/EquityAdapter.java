package com.example.dllo.testdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class EquityAdapter extends FragmentPagerAdapter{
    ArrayList<String> titleArr = new ArrayList<>();
    ArrayList<Fragment> fragments;

    public EquityAdapter(FragmentManager fm) {
        super(fm);
    }

    public EquityAdapter(FragmentManager fm , ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
        titleArr.add("全部");
        titleArr.add("募资中");
        titleArr.add("募资完成");
        titleArr.add("融资成功");
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
