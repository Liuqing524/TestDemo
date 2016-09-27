package com.example.dllo.testdemo.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.bean.FoundLBean;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/9/21.
 */
public class FoundLBAdapter extends PagerAdapter{
    Context context;
    FoundLBean bean;

    public FoundLBAdapter(Context context) {
        this.context = context;
    }

    public void setBean(FoundLBean bean) {
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.found_lb,container,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.found_lb_iv);
        Picasso.with(context).load(bean.getData().getPics().get(position%bean.getData().getPics().size()).getImgUrl()).into(imageView);
   container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
