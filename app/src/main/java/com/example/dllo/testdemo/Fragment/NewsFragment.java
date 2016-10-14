package com.example.dllo.testdemo.fragment;

import android.content.Intent;
import android.os.AsyncTask;
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
import com.example.dllo.testdemo.activity.VolleySingleton;
import com.example.dllo.testdemo.adapter.NewsListViewAdapter;
import com.example.dllo.testdemo.adapter.NewsShufflingAdapter;
import com.example.dllo.testdemo.base.BaseFragment;
import com.example.dllo.testdemo.base.StringUrl;
import com.example.dllo.testdemo.bean.NewsListViewBean;
import com.example.dllo.testdemo.bean.NewsShufflingBean;
import com.example.dllo.testdemo.dialog.SerachActivity;
import com.example.dllo.testdemo.dialog.VidoViewActivity;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

;

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
    private PullToRefreshListView newsLv;
    private Handler handler;
    private Boolean mFalg = true;
    private Boolean flag = true;
    private ImageButton retu;
    private Button newsAll, newsEarly, newsHeight, newsBig, newsEight, newsPeople, newsFriend, newsSerach ,videoView;
    private TextView newsTitle;
    private ImageView imageView;
    private NewsListViewBean bean1;

    private LinearLayout drawee_ll;

    int id = 20;


    @Override
    protected int setLayout() {


        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {
        drawer = bindView(R.id.kr_news_drawer);

        seracher = bindView(R.id.kr_news_seracher);
        final TextView newsTv = bindView(R.id.kr_news_tv);
        newsLv = bindView(R.id.kr_news_lv);
        drawerll = bindView(R.id.kr_news_ll);
        drawerLayout = bindView(R.id.news_drawer);
        newsTitle = bindView(R.id.kr_news_tv);
        imageView = bindView(R.id.news_details_imageview);
        drawee_ll = bindView(R.id.kr_news_ll);

        retu = bindView(R.id.kr_news_drawer_ib);
        newsAll = bindView(R.id.kr_news_drawer_btn_all);
        newsEarly = bindView(R.id.kr_news_drawer_btn_eraly);
        newsHeight = bindView(R.id.kr_news_drawer_btn_height);
        newsBig = bindView(R.id.kr_news_drawer_btn_big);
        newsEight = bindView(R.id.kr_news_drawer_btn_eight);
        newsPeople = bindView(R.id.kr_news_drawer_btn_people);
        newsFriend = bindView(R.id.kr_news_drawer_btn_friend);
        newsSerach = bindView(R.id.kr_news_drawer_btn_serach);
        videoView = bindView(R.id.kr_news_drawer_btn_open_tv);


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
        videoView.setOnClickListener(this);
        drawee_ll.setOnClickListener(this);

        seracher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SerachActivity.class));
            }
        });

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

        //轮播图
        lbView = LayoutInflater.from(getContext()).inflate(R.layout.newfragmentlb, null);
        viewPager = (ViewPager) lbView.findViewById(R.id.news_fragment_lb);

        adapter = new NewsShufflingAdapter(getContext());
        newsLv.getRefreshableView().addHeaderView(lbView);
        //newsLv.addHeaderView(lbView);
         pullUpData();
        newsLv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
               //下拉刷新
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                listRe();
//                new AsyncTask<Void, Void, Void>() {
//                    @Override
//                    protected Void doInBackground(Void... params) {
//                        try {
//                            Thread.sleep(3000);
//                           // onDestroy();
//                            Thread.currentThread().interrupt();
//
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        return null;
//                    }
//                    @Override
//                    protected void onPostExecute(Void aVoid) {
//                        super.onPostExecute(aVoid);
//                        listRe();
//                        newsLv.onRefreshComplete();//通知完成
//                    }
//
//                }.execute();


            }
             //上拉加载
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                 new AsyncTask<Void , Void , Void>() {
                     @Override
                     protected Void doInBackground(Void... params) {
                         try {
                             Thread.sleep(3000);
                         } catch (InterruptedException e) {
                             e.printStackTrace();
                         }
                         return null;
                     }

                     @Override
                     protected void onPostExecute(Void aVoid) {
                         super.onPostExecute(aVoid);
                       //  RequestQueue queue = Volley.newRequestQueue(getContext());

                         id += 20;

                         String pullUrl = "https://rong.36kr.com/api/mobi/news?pageSize=" + id + "&columnId=all&pagingAction=up";
//                         StringRequest stringRequest = new StringRequest(pullUrl,
//                                 new Response.Listener<String>() {
//
//                                     @Override
//                                     public void onResponse(String response) {
//                                         Gson gsonlist = new Gson();
//                                         NewsListViewBean beanlist = gsonlist.fromJson(response, NewsListViewBean.class);
//                                         newsListViewAdapter.setBean(beanlist);
//                                         newsLv.setAdapter(newsListViewAdapter);
//                                     }
//                                 }, new Response.ErrorListener() {
//                             @Override
//                             public void onErrorResponse(VolleyError error) {
//
//                             }
//                         });
//                         queue.add(stringRequest);

                         VolleySingleton.addRequest(pullUrl, NewsListViewBean.class, new Response.Listener<NewsListViewBean>() {

                             @Override
                             public void onResponse(NewsListViewBean response) {
                                 newsListViewAdapter.setBean(response);
                                 newsLv.setAdapter(newsListViewAdapter);
                             }
                         }, new Response.ErrorListener() {
                             @Override
                             public void onErrorResponse(VolleyError error) {

                             }
                         });
                         newsLv.onRefreshComplete();

                     }

                 }.execute();
            }
        });

    }
    //设置刷新的状态
    private void pullUpData() {
        // 设置PullToRefreshListView的模式
        // pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);

        // 设置PullRefreshListView上提加载时的加载提示
        newsLv.setMode(PullToRefreshBase.Mode.BOTH);
        newsLv.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载...");
        newsLv.getLoadingLayoutProxy(false, true).setRefreshingLabel("正在加载...");
        newsLv.getLoadingLayoutProxy(false, true).setReleaseLabel("松开加载更多...");

        // 设置PullRefreshListView下拉加载时的加载提示
        newsLv.getLoadingLayoutProxy(true, false).setPullLabel("下拉刷新...");
        newsLv.getLoadingLayoutProxy(true, false).setRefreshingLabel("正在加载...");
        newsLv.getLoadingLayoutProxy(true, false).setReleaseLabel("松开加载更多...");
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
//        RequestQueue queuelist = Volley.newRequestQueue(getContext());
//        StringRequest listRequest = new StringRequest("https://rong.36kr.com/api/mobi/news?pageSize=20&columnId=all&pagingAction=up", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Gson gsonlist = new Gson();
//                NewsListViewBean beanlist = gsonlist.fromJson(response, NewsListViewBean.class);
//                newsListViewAdapter.setBean(beanlist);
//                newsLv.setAdapter(newsListViewAdapter);
//                newsLv.onRefreshComplete();
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                newsLv.onRefreshComplete();
//
//            }
//        });
//        queuelist.add(listRequest);
        VolleySingleton.addRequest("https://rong.36kr.com/api/mobi/news?pageSize=20&columnId=all&pagingAction=up", NewsListViewBean.class,
                new Response.Listener<NewsListViewBean>() {

                    @Override
                    public void onResponse(NewsListViewBean response) {
                        newsListViewAdapter.setBean(response);
                newsLv.setAdapter(newsListViewAdapter);
                newsLv.onRefreshComplete();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
    }

    //解析轮播图的方法
    @Override
    protected void initData() {
//        RequestQueue queue = Volley.newRequestQueue(getContext());
//        StringRequest stringRequest = new StringRequest("https://rong.36kr.com/api/mobi/roundpics/v4", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Gson gson = new Gson();
//                NewsShufflingBean bean1 = gson.fromJson(response, NewsShufflingBean.class);
//                adapter.setBeen(bean1);
//                viewPager.setAdapter(adapter);
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        queue.add(stringRequest);

        VolleySingleton.addRequest("https://rong.36kr.com/api/mobi/roundpics/v4", NewsShufflingBean.class,
                new Response.Listener<NewsShufflingBean>() {

                    @Override
                    public void onResponse(NewsShufflingBean response) {
                        adapter.setBeen(response);
                        viewPager.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });


    }

    //抽屉数据方法
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

        newsLv.getRefreshableView().removeHeaderView(lbView);

        switch (v.getId()) {
            case R.id.kr_news_drawer:
                drawerLayout.openDrawer(drawerll);
                break;
            case R.id.kr_news_drawer_ib:
                drawerLayout.closeDrawers();
                break;
            case R.id.kr_news_drawer_btn_all:
                newsTitle.setText("新闻");
                newsLv.getRefreshableView().addHeaderView(lbView);
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
            case R.id.kr_news_drawer_btn_open_tv:
                 startActivity(new Intent(getActivity() , VidoViewActivity.class));
                drawerLayout.closeDrawers();
                break;
            case R.id.kr_news_ll:
                
                break;
        }
    }


}
