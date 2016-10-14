package com.example.dllo.testdemo.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.base.BaseFragment;
import com.example.dllo.testdemo.dialog.InformationCommentActivity;
import com.example.dllo.testdemo.dialog.InformationLetterActivity;
import com.example.dllo.testdemo.dialog.InformationLikeActivity;

/**
 * Created by dllo on 16/9/19.
 */
public class InformationFragment extends BaseFragment implements View.OnClickListener{
    private TabLayout tb;
    private ViewPager vp;
  private   RelativeLayout letter,comments,like,service,notification;
    private Context context;
    @Override
    protected int setLayout() {
        return R.layout.fragment_informationone;
    }

    @Override
    protected void initView() {
     letter = bindView(R.id.kr_information_private_letter);
        comments = bindView(R.id.kr_information_comments);
        like = bindView(R.id.kr_information_like);
        service = bindView(R.id.kr_information_service);
        notification = bindView(R.id.kr_information_notification);
        letter.setOnClickListener(this);
        comments.setOnClickListener(this);
        like.setOnClickListener(this);

    }

    @Override
    protected void initData() {


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.kr_information_private_letter:
                Intent intent = new Intent(getActivity(), InformationLetterActivity.class);
                startActivity(intent);
                break;
            case R.id.kr_information_comments:
                 Intent intent1 = new Intent(getActivity() , InformationCommentActivity.class);
                startActivity(intent1);
                break;
            case R.id.kr_information_like:
                  Intent intent2 = new Intent(getActivity() , InformationLikeActivity.class);
                startActivity(intent2);
                break;
        }

    }
}
