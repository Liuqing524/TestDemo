package com.example.dllo.testdemo.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dllo.testdemo.Bean.NewsShufflingBean;
import com.example.dllo.testdemo.R;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/9/19.
 */
public class NewsShufflingAdapter extends PagerAdapter{
    Context context;
    NewsShufflingBean been;

    public void setBeen(NewsShufflingBean been) {
        this.been = been;
    }

    public NewsShufflingAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return been == null ? 0 : been.getData().getPics().size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.newslb,container,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.news_lb_iv);
        Picasso.with(context).load(been.getData().getPics().get(position).getLocation()).into(imageView);
        container.addView(view);
return view;
    }
}
