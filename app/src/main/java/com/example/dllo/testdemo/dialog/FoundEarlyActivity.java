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
import com.example.dllo.testdemo.adapter.FoundEarlyAdapter;
import com.example.dllo.testdemo.base.BaseAty;
import com.example.dllo.testdemo.bean.FoundEarlyBean;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/9/27.
 */
public class FoundEarlyActivity extends BaseAty implements View.OnClickListener{
    private ImageButton backEarly;
    private ListView earlyListview;
    private FoundEarlyBean foundEarlyBean;
    private FoundEarlyAdapter adapter;
    @Override
    protected int setLayout() {
        return R.layout.activity_found_early;
    }

    @Override
    protected void initView() {
  backEarly = bindView(R.id.found_early_imagebtn_back);
        earlyListview = bindView(R.id.found_early_listview);
        backEarly.setOnClickListener(this);
        adapter = new FoundEarlyAdapter(this);
    }

    @Override
    protected void initData() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest("https://rong.36kr.com/api/mobi/activity/list?page=1", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                FoundEarlyBean bean = gson.fromJson(response , FoundEarlyBean.class);
                adapter.setFoundEarlyBean(bean);
                earlyListview.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.found_early_imagebtn_back:
                finish();
                break;
        }
    }
}
