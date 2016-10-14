package com.example.dllo.testdemo.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.adapter.EquityAdapter;
import com.example.dllo.testdemo.base.BaseFragment;
import com.example.dllo.testdemo.dialog.EquityGiftActivity;

import java.util.ArrayList;

/**
 * Created by dllo on 16/9/19.
 */
public class EquityFragment extends BaseFragment implements View.OnClickListener{

    private TabLayout tb;
    private ViewPager vp;
    private EquityAdapter equityAdapter;
    private ImageButton gift;

    @Override
    protected int setLayout() {
        return R.layout.fragment_equity;
    }

    @Override
    protected void initView() {
        tb = bindView(R.id.kr_equity_tab);
        vp = bindView(R.id.kr_equity_viewpager);
        gift = bindView(R.id.kr_equity_gift);
        gift.setOnClickListener(this);


        MyBost myBost =new MyBost();
        IntentFilter filter = new IntentFilter();
        filter.addAction("gift");
        getContext().registerReceiver(myBost,filter);




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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.kr_equity_gift:

                gift.setVisibility(View.INVISIBLE);
                Intent intent =new Intent(getActivity() , EquityGiftActivity.class);

                 startActivity(intent);
                break;
        }
    }

    class MyBost extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

            gift.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.mygift);
            gift.startAnimation(animation);
        }
    }
}
