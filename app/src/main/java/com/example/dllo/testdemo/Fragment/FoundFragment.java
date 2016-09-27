package com.example.dllo.testdemo.fragment;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.adapter.FoundLBAdapter;
import com.example.dllo.testdemo.base.BaseFragment;
import com.example.dllo.testdemo.bean.FoundLBean;
import com.example.dllo.testdemo.dialog.FoundEarlyActivity;
import com.example.dllo.testdemo.dialog.FoundEntrepreneursActivity;
import com.example.dllo.testdemo.dialog.FoundEquityActivity;
import com.example.dllo.testdemo.dialog.FoundPeopleActivity;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/9/19.
 */
public class FoundFragment extends BaseFragment implements View.OnClickListener{
    private View viewLB;
    private FoundLBean bean;
    private FoundLBAdapter adapter;
    private ViewPager viewPager;
    private LinearLayout foundPeople;
    private Button entrepreneurs,equityer,foundEarly;

    @Override
    protected int setLayout() {
        return R.layout.fragment_found;
    }


    @Override
    protected void initView() {

        viewPager = bindView(R.id.kr_found_viewpager);
        foundPeople = bindView(R.id.found_llayout_find_people);
        entrepreneurs = bindView(R.id.found_btn_entrepreneurs);
        equityer = bindView(R.id.found_btn_equityer);
        foundEarly = bindView(R.id.kr_found_item_first);

       foundPeople.setOnClickListener(this);
        entrepreneurs.setOnClickListener(this);
        equityer.setOnClickListener(this);
        foundEarly.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        adapter = new FoundLBAdapter(getContext());
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest("https://rong.36kr.com/api/mobi/roundpics/v4", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                FoundLBean foundLBean = gson.fromJson(response , FoundLBean.class);
                adapter.setBean(foundLBean);
                viewPager.setAdapter(adapter);

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
            case R.id.found_llayout_find_people:
                Intent intent = new Intent(getContext() , FoundPeopleActivity.class);
                startActivity(intent);
                break;
            case R.id.found_btn_entrepreneurs:
                startActivity(new Intent(getContext() , FoundEntrepreneursActivity.class));
                break;
            case R.id.found_btn_equityer:
                startActivity(new Intent(getContext() , FoundEquityActivity.class));
                break;
            case R.id.kr_found_item_first:
               startActivity(new Intent(getContext() , FoundEarlyActivity.class));
                break;
        }
    }
}
