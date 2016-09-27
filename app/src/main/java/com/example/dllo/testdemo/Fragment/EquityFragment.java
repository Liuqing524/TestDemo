package com.example.dllo.testdemo.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.dllo.testdemo.adapter.EquityAdapter;
import com.example.dllo.testdemo.base.BaseFragment;
import com.example.dllo.testdemo.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class EquityFragment extends BaseFragment{

    private TabLayout tb;
    private ViewPager vp;
    private EquityAdapter equityAdapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_equity;
    }

    @Override
    protected void initView() {
        tb = bindView(R.id.kr_equity_tab);
        vp = bindView(R.id.kr_equity_viewpager);



    }

    @Override
    protected void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new AllFragment());
        fragments.add(new Equity_second_fragment());
        fragments.add(new Equity_thrid_fragment());
        fragments.add(new Equity_fourth_fragment());

        equityAdapter = new EquityAdapter(getChildFragmentManager() , fragments);
        vp.setAdapter(equityAdapter);
        tb.setupWithViewPager(vp);
        tb.setTabTextColors(Color.GRAY , Color.BLUE);
    }
}
