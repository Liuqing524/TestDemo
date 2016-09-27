package com.example.dllo.testdemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.bean.NewsShufflingBean;
import com.example.dllo.testdemo.dialog.RotateActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/9/19.
 */
public class NewsShufflingAdapter extends PagerAdapter{
    Context context;
    NewsShufflingBean been;
    private ImageView imageView;

    public void setBeen(NewsShufflingBean been) {
        this.been = been;
    }

    public NewsShufflingAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return been == null ? 0 :Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.newslb, container, false);
        imageView = (ImageView) view.findViewById(R.id.news_lb_iv);
        Picasso.with(context).load(been.getData().getPics().get(position%been.getData().getPics().size()).getImgUrl()).into(imageView);


        container.addView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , RotateActivity.class);
                intent.putExtra("URL" , been.getData().getPics().get(position%been.getData().getPics().size()).getLocation());
                context.startActivity(intent);
            }
        });

        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
