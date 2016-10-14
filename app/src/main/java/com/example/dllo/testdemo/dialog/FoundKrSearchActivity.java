package com.example.dllo.testdemo.dialog;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.adapter.NewsListViewAdapter;
import com.example.dllo.testdemo.base.BaseAty;
import com.example.dllo.testdemo.bean.NewsListViewBean;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/10/9.
 */
public class FoundKrSearchActivity extends BaseAty implements View.OnClickListener{
    private ImageButton backAty;
    private ListView krAtyLV;
    private NewsListViewBean bean;
    private NewsListViewAdapter adapter;
    @Override
    protected int setLayout() {
        return R.layout.found_kr_search_activity;
    }

    @Override
    protected void initView() {
      backAty = bindView(R.id.found_kr_search_aty_back);
        krAtyLV = bindView(R.id.found_kr_search_aty_listview);
        adapter = new NewsListViewAdapter(this);
        backAty.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest("https://rong.36kr.com/api/mobi/news?pageSize=20&columnId=71&pagingAction=up",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                   NewsListViewBean  bean1 = gson.fromJson(response , NewsListViewBean.class);
                        adapter.setBean(bean1);
                        krAtyLV.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.found_kr_search_aty_back:
                finish();
                break;
        }
    }
}
