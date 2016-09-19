package com.example.dllo.testdemo.Fragment;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.testdemo.Adapter.NewsShufflingAdapter;
import com.example.dllo.testdemo.Base.BaseFragment;
import com.example.dllo.testdemo.Bean.NewsShufflingBean;
import com.example.dllo.testdemo.R;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/9/19.
 */
public class NewsFragment extends BaseFragment{

    private View lbView;
    private NewsShufflingBean bean;
    private NewsShufflingAdapter adapter;
    private ViewPager viewPager;

    @Override
    protected int setLayout() {
        lbView = LayoutInflater.from(getContext()).inflate(R.layout.newfragmentlb,null);

        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {
        ImageButton drawer = bindView(R.id.kr_news_drawer);
        ImageButton seracher = bindView(R.id.kr_news_seracher);
        TextView newsTv = bindView(R.id.kr_news_tv);
        ListView newsLv = bindView(R.id.kr_news_lv);
        viewPager = bindView(R.id.news_fragment_lb);
        adapter = new NewsShufflingAdapter(getContext());
        newsLv.addHeaderView(lbView);


    }

    @Override
    protected void initData() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest("https://rong.36kr.com/api/mobi/roundpics/v4", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                NewsShufflingBean bean1 = gson.fromJson(response,NewsShufflingBean.class);
                adapter.setBeen(bean1);
                viewPager.setAdapter(adapter);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
queue.add(stringRequest);


    }
}
