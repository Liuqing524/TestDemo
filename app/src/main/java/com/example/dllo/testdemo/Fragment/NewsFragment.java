package com.example.dllo.testdemo.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.activity.NewsListViewDetailsActivity;
import com.example.dllo.testdemo.adapter.NewsListViewAdapter;
import com.example.dllo.testdemo.adapter.NewsShufflingAdapter;
import com.example.dllo.testdemo.base.BaseFragment;
import com.example.dllo.testdemo.base.StringUrl;
import com.example.dllo.testdemo.bean.NewsListViewBean;
import com.example.dllo.testdemo.bean.NewsShufflingBean;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/9/19.
 */
public class NewsFragment extends BaseFragment implements View.OnClickListener {

    private View lbView;
    private NewsShufflingBean bean;
    private NewsShufflingAdapter adapter;
    private ViewPager viewPager;
    private DrawerLayout drawerLayout;
    private ImageButton seracher;
    private LinearLayout drawerll;
    private ImageButton drawer;

    private NewsListViewAdapter newsListViewAdapter;
    private ListView newsLv;
    private Handler handler;
    private Boolean mFalg = true;
    private Boolean flag = true;
    private ImageButton retu;
    private Button newsAll, newsEarly, newsHeight, newsBig, newsEight, newsPeople, newsFriend, newsSerach;
    private TextView newsTitle;
    private ImageView imageView;
    private NewsListViewBean bean1;


    @Override
    protected int setLayout() {


        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {
        drawer = bindView(R.id.kr_news_drawer);

        seracher = bindView(R.id.kr_news_seracher);
        TextView newsTv = bindView(R.id.kr_news_tv);
        newsLv = bindView(R.id.kr_news_lv);
        drawerll = bindView(R.id.kr_news_ll);
        drawerLayout = bindView(R.id.news_drawer);
        newsTitle = bindView(R.id.kr_news_tv);
        imageView = bindView(R.id.news_details_imageview);

        retu = bindView(R.id.kr_news_drawer_ib);
        newsAll = bindView(R.id.kr_news_drawer_btn_all);
        newsEarly = bindView(R.id.kr_news_drawer_btn_eraly);
        newsHeight = bindView(R.id.kr_news_drawer_btn_height);
        newsBig = bindView(R.id.kr_news_drawer_btn_big);
        newsEight = bindView(R.id.kr_news_drawer_btn_eight);
        newsPeople = bindView(R.id.kr_news_drawer_btn_people);
        newsFriend = bindView(R.id.kr_news_drawer_btn_friend);
        newsSerach = bindView(R.id.kr_news_drawer_btn_serach);


        drawer.setOnClickListener(this);
        retu.setOnClickListener(this);
        newsAll.setOnClickListener(this);
        newsEarly.setOnClickListener(this);
        newsHeight.setOnClickListener(this);
        newsBig.setOnClickListener(this);
        newsEight.setOnClickListener(this);
        newsPeople.setOnClickListener(this);
        newsFriend.setOnClickListener(this);
        newsSerach.setOnClickListener(this);
        newsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                NewsListViewBean.DataBean.DataBean1 dataBean1 = (NewsListViewBean.DataBean.DataBean1) parent.getItemAtPosition(position);


                Intent intent = new Intent(getContext(), NewsListViewDetailsActivity.class);
                //网址
                String feedId = dataBean1.getFeedId();
                intent.putExtra("ID", feedId);
                String feedUrl = "https://rong.36kr.com/api/mobi/news/" + feedId;
                intent.putExtra("url", feedUrl);
                //姓名
                String name = dataBean1.getUser().getName();
                intent.putExtra("name", name);

                //头像
                String avatar = dataBean1.getUser().getAvatar();
                intent.putExtra("avatar", avatar);
                Log.d("NewsFragment", dataBean1.getUser().getAvatar());

                // 题目
                String title = dataBean1.getTitle();
                intent.putExtra("title", title);
                //时间
                long time = dataBean1.getPublishTime();
                intent.putExtra("time", time);

                startActivity(intent);


            }
        });

        //解析 listView
        newsListViewAdapter = new NewsListViewAdapter(getContext());
        listRe();
        sleepLB();


        lbView = LayoutInflater.from(getContext()).inflate(R.layout.newfragmentlb, null);
        viewPager = (ViewPager) lbView.findViewById(R.id.news_fragment_lb);


        adapter = new NewsShufflingAdapter(getContext());
        newsLv.addHeaderView(lbView);
    }

    //设置轮播图的时间
    private void sleepLB() {

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                return false;
            }
        });
        if (flag) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (mFalg) {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (mFalg) {
                            handler.sendEmptyMessage(0);
                        }
                    }
                }
            }).start();
            flag = false;
        }
    }

    //解析 listview 的方法
    private void listRe() {
        RequestQueue queuelist = Volley.newRequestQueue(getContext());
        StringRequest listRequest = new StringRequest("https://rong.36kr.com/api/mobi/news?pageSize=20&columnId=all&pagingAction=up", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gsonlist = new Gson();
                NewsListViewBean beanlist = gsonlist.fromJson(response, NewsListViewBean.class);
                newsListViewAdapter.setBean(beanlist);
                newsLv.setAdapter(newsListViewAdapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queuelist.add(listRequest);

    }

    //解析轮播图的方法
    @Override
    protected void initData() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest("https://rong.36kr.com/api/mobi/roundpics/v4", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                NewsShufflingBean bean1 = gson.fromJson(response, NewsShufflingBean.class);
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

    //抽屉方法
    private void drawerQueue(String url) {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                bean1 = gson.fromJson(response, NewsListViewBean.class);
                newsListViewAdapter.setBean(bean1);
                newsLv.setAdapter(newsListViewAdapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);

    }

    //抽屉的点击事件
    @Override
    public void onClick(View v) {

        newsLv.removeHeaderView(lbView);

        switch (v.getId()) {
            case R.id.kr_news_drawer:
                drawerLayout.openDrawer(drawerll);
                break;
            case R.id.kr_news_drawer_ib:
                drawerLayout.closeDrawers();
                break;
            case R.id.kr_news_drawer_btn_all:
                newsTitle.setText("新闻");
                newsLv.addHeaderView(lbView);
                drawerQueue(StringUrl.newsAllUrl);
                drawerLayout.closeDrawers();
                break;
            case R.id.kr_news_drawer_btn_eraly:
                newsTitle.setText("早期项目");
                drawerQueue(StringUrl.newsEarlyUrl);

                drawerLayout.closeDrawers();
                break;
            case R.id.kr_news_drawer_btn_height:
                newsTitle.setText("深度");
                drawerQueue(StringUrl.newsHeightUrl);
                drawerLayout.closeDrawers();
                break;
            case R.id.kr_news_drawer_btn_big:
                newsTitle.setText("大公司");
                drawerQueue(StringUrl.newsBigUrl);
                drawerLayout.closeDrawers();
                break;
            case R.id.kr_news_drawer_btn_eight:
                newsTitle.setText("8点1氪");
                drawerQueue(StringUrl.newsEightUrl);
                drawerLayout.closeDrawers();
                break;
            case R.id.kr_news_drawer_btn_people:
                newsTitle.setText("人物");
                drawerQueue(StringUrl.newsPeopleUrl);
                drawerLayout.closeDrawers();
                break;
            case R.id.kr_news_drawer_btn_friend:
                drawerQueue(StringUrl.newsFriendUrl);
                newsTitle.setText("氪友");
                drawerLayout.closeDrawers();
                break;
            case R.id.kr_news_drawer_btn_serach:
                newsTitle.setText("研究");
                drawerQueue(StringUrl.newsResachUrl);
                drawerLayout.closeDrawers();
                break;
        }
    }

}
