package com.example.dllo.testdemo.Fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.dllo.testdemo.Adapter.InFormationAdapter;
import com.example.dllo.testdemo.Base.BaseFragment;
import com.example.dllo.testdemo.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class InformationFragment extends BaseFragment{
    private TabLayout tb;
    private ViewPager vp;
    @Override
    protected int setLayout() {
        return R.layout.fragment_information;
    }

    @Override
    protected void initView() {
        tb = bindView(R.id.kr_information_tab);
        vp = bindView(R.id.kr_information_viewpager);

    }

    @Override
    protected void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new informationLoginFragment());
        fragments.add(new informationRegisterFragment());


        InFormationAdapter inFormationAdapter = new InFormationAdapter(getChildFragmentManager() , fragments);

        vp.setAdapter(inFormationAdapter);
        tb.setupWithViewPager(vp);
        tb.setTabTextColors(Color.WHITE , Color.GRAY);

    }
}
