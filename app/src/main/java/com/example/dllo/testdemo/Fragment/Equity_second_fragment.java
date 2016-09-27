package com.example.dllo.testdemo.fragment;

import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.testdemo.adapter.EquitySecondAdapter;
import com.example.dllo.testdemo.base.BaseFragment;
import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.bean.EquitySecondBean;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/9/19.
 */
public class Equity_second_fragment extends BaseFragment{
    private EquitySecondBean secondBean;
    private EquitySecondAdapter secondAdapter;
    private ListView secondListView;


    @Override
    protected int setLayout() {
        return R.layout.fragment_equity_second;
    }

    @Override
    protected void initView() {
        secondListView = bindView(R.id.kr_equity_second_listview);
        secondBean = new EquitySecondBean();
        secondAdapter = new EquitySecondAdapter(getContext());

    }

    @Override
    protected void initData() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest("https://rong.36kr.com/api/mobi/cf/actions/list?page=1&type=underway&pageSize=20", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                  EquitySecondBean bean = gson.fromJson(response , EquitySecondBean.class);
                secondAdapter.setSecondBean(bean);
                secondListView.setAdapter(secondAdapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);

    }
}
