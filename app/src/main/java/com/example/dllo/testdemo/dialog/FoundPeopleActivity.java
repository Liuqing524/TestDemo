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
import com.example.dllo.testdemo.base.BaseAty;
import com.example.dllo.testdemo.bean.FoundPeopleBean;
import com.example.dllo.testdemo.secondAdapter.FoundPeopleAdapter;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/9/27.
 */
public class FoundPeopleActivity extends BaseAty{
    private ImageButton foundPeopleBack;
    private ListView listViewFoundPeople;
    private FoundPeopleAdapter foundPeopleAdapter;
    private FoundPeopleBean foundPeopleBean;
    @Override
    protected int setLayout() {
        return R.layout.activity_found_people;
    }

    @Override
    protected void initView() {
        foundPeopleBack = bindView(R.id.found_people_back);
        listViewFoundPeople = bindView(R.id.found_people_listview);
        foundPeopleAdapter = new FoundPeopleAdapter(this);
        foundPeopleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest("https://rong.36kr.com/api/mobi/investor?page=1&pageSize=20", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                foundPeopleBean = gson.fromJson(response , FoundPeopleBean.class);
                foundPeopleAdapter.setFoundPeopleBean(foundPeopleBean);
                listViewFoundPeople.setAdapter(foundPeopleAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
     queue.add(stringRequest);
    }
}
