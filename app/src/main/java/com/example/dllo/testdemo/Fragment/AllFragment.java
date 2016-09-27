package com.example.dllo.testdemo.fragment;

import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.testdemo.adapter.EquityAllAdapter;
import com.example.dllo.testdemo.base.BaseFragment;
import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.bean.EquityAllBean;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/9/19.
 */
public class AllFragment extends BaseFragment{
    private EquityAllBean bean;
    private EquityAllAdapter equityAllAdapter;
    private ListView equityListview;



    @Override
    protected int setLayout() {
        return R.layout.fragment_equity_all;
    }

    @Override
    protected void initView() {
        equityListview = bindView(R.id.kr_equity_all_list);
        bean = new EquityAllBean();
        equityAllAdapter = new EquityAllAdapter(getContext());

    }

    @Override
    protected void initData() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest("https://rong.36kr.com/api/mobi/cf/actions/list?page=1&type=all&pageSize=20", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                EquityAllBean bean = gson.fromJson(response , EquityAllBean.class);
                equityAllAdapter.setBean(bean);
                equityListview.setAdapter(equityAllAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);

    }
}
