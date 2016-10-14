package com.example.dllo.testdemo.secondAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/8.
 */
public class OrderAdapter extends FragmentPagerAdapter{
    ArrayList<String> titleArr = new ArrayList<>();
    ArrayList<Fragment> fragments;

    public OrderAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
        titleArr.add("全部");
        titleArr.add("待付款");
        titleArr.add("已付款");
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
