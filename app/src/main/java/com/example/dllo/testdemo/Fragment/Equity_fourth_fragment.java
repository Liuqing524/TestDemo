package com.example.dllo.testdemo.fragment;

import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.testdemo.adapter.EquityFourthAdapter;
import com.example.dllo.testdemo.base.BaseFragment;
import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.bean.EquityFourthBean;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/9/19.
 */
public class Equity_fourth_fragment extends BaseFragment{
    private EquityFourthBean fourthBean;
    private ListView listView;
    private EquityFourthAdapter fourthAdapter;
    @Override
    protected int setLayout() {
        return R.layout.fragment_equity_fourth;
    }

    @Override
    protected void initView() {
        listView = bindView(R.id.kr_equity_fourth_listview);
        fourthBean = new EquityFourthBean();
        fourthAdapter = new EquityFourthAdapter(getContext());

    }

    @Override
    protected void initData() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest("https://rong.36kr.com/api/mobi/cf/actions/list?page=1&type=finish&pageSize=20", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                EquityFourthBean bean = gson.fromJson(response, EquityFourthBean.class);
                fourthAdapter.setFourthBean(bean);
                listView.setAdapter(fourthAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);

    }
}
