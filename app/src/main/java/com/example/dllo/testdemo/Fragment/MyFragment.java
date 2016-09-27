package com.example.dllo.testdemo.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.base.BaseFragment;
import com.example.dllo.testdemo.dialog.MineHotLineActivity;

/**
 * Created by dllo on 16/9/19.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener{
    private Button hotLine;
    @Override
    protected int setLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {
        hotLine = bindView(R.id.kr_myhotline);
        hotLine.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.kr_myhotline:
                startActivity(new Intent(getActivity(), MineHotLineActivity.class));
                break;
        }
    }
}
