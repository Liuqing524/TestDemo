package com.example.dllo.testdemo.dialog;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;

import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.base.BaseAty;
import com.example.dllo.testdemo.fragment.OrderAllFragment;
import com.example.dllo.testdemo.fragment.OrderDaiFragment;
import com.example.dllo.testdemo.fragment.OrderYiFragment;
import com.example.dllo.testdemo.secondAdapter.OrderAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/8.
 */
public class MyOrderActivity extends BaseAty implements View.OnClickListener{
    private TabLayout orderTab;
    private ViewPager orderPager;
    private ImageButton back;
    private OrderAdapter adapter;
    @Override
    protected int setLayout() {
        return R.layout.my_order_activity;
    }

    @Override
    protected void initView() {
        orderPager = bindView(R.id.my_order_pager);
        orderTab = bindView(R.id.my_order_tab);
        back = bindView(R.id.my_order_back);
        back.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new OrderAllFragment());
        fragments.add(new OrderDaiFragment());
        fragments.add(new OrderYiFragment());
        adapter = new OrderAdapter(getSupportFragmentManager() ,fragments);
        orderPager.setAdapter(adapter);
        orderTab.setupWithViewPager(orderPager);
        orderTab.setTabTextColors(Color.GRAY , Color.BLUE);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_order_back:
                finish();
                break;
        }
    }
}
