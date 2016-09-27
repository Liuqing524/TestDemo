package com.example.dllo.testdemo.fragment;

import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.testdemo.adapter.EquityThridAdapter;
import com.example.dllo.testdemo.base.BaseFragment;
import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.bean.EquityThridBean;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/9/19.
 */
public class Equity_thrid_fragment extends BaseFragment{
    private EquityThridBean thridBean;
    private ListView listView;
    private EquityThridAdapter thridAdapter;
    @Override
    protected int setLayout() {
        return R.layout.fragment_equity_thrid;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.kr_equity_thrid_listview);
        thridBean = new EquityThridBean();
        thridAdapter = new EquityThridAdapter(getContext());

    }

    @Override
    protected void initData() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest("https://rong.36kr.com/api/mobi/cf/actions/list?page=1&type=raise&pageSize=20", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                EquityThridBean bean = gson.fromJson(response , EquityThridBean.class);
                thridAdapter.setThridBean(bean);
                listView.setAdapter(thridAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);

    }
}
