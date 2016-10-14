package com.example.dllo.testdemo.dialog;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.adapter.FoundEarlyAdapter;
import com.example.dllo.testdemo.base.BaseAty;
import com.example.dllo.testdemo.base.StringUrl;
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
    private Button earlyType, earlyTime;
    @Override
    protected int setLayout() {
        return R.layout.activity_found_early;
    }

    @Override
    protected void initView() {
  backEarly = bindView(R.id.found_early_imagebtn_back);
        earlyListview = bindView(R.id.found_early_listview);
        earlyType = bindView(R.id.found_btn_early_type);
        earlyTime = bindView(R.id.found_btn_early_time);
        earlyType.setOnClickListener(this);
        earlyTime.setOnClickListener(this);
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
// 点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.found_early_imagebtn_back:
                finish();
                break;
            case R.id.found_btn_early_type:
                showTypePopupWindow(v);

                break;
            case R.id.found_btn_early_time:
                showTimePopupWindow(v);
                break;
        }
    }
// 时间的 pop
    private void showTimePopupWindow(View v) {
        View timeView = LayoutInflater.from(this).inflate(R.layout.pop_window_early_time , null);
        final PopupWindow timePop = new PopupWindow(timeView , ViewGroup.LayoutParams.WRAP_CONTENT ,
                ViewGroup.LayoutParams.WRAP_CONTENT,true);
        timePop.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        timePop.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape));

        timePop.showAsDropDown(v);

    }

//类型的 pop
    private void showTypePopupWindow(View v) {
        View typeView = LayoutInflater.from(this).inflate(R.layout.pop_window_early_type , null);
        final TextView earlyAll = (TextView) typeView.findViewById(R.id.pop_early_tv_all);
        TextView earlyDemo = (TextView) typeView.findViewById(R.id.pop_early_tv_demo);
        TextView earlyKr = (TextView) typeView.findViewById(R.id.pop_early_tv_kr);
        TextView earlyEquity = (TextView) typeView.findViewById(R.id.pop_early_tv_equity);
        TextView earlyService = (TextView) typeView.findViewById(R.id.pop_early_tv_service);
        TextView earlyQuick = (TextView) typeView.findViewById(R.id.pop_early_tv_quick);
        final PopupWindow popupWindow = new PopupWindow(typeView , ViewGroup.LayoutParams.WRAP_CONTENT ,
                ViewGroup.LayoutParams.WRAP_CONTENT , true);
        earlyAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               EarlyQueue(StringUrl.earlyAllUrl);
                earlyType.setText("全部");
                earlyType.setTextColor(Color.BLUE);
                popupWindow.dismiss();
            }
        });
        earlyDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EarlyQueue(StringUrl.earlyDemoUrl);
                earlyType.setText("Demo Day");

                earlyType.setTextColor(Color.BLUE);
                popupWindow.dismiss();
            }
        });
        earlyKr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EarlyQueue(StringUrl.earlyKrUrl);
                earlyType.setText("氪空间");
                earlyType.setTextColor(Color.BLUE);
                popupWindow.dismiss();
            }
        });
        earlyEquity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EarlyQueue(StringUrl.earlyEquityUrl);
                earlyType.setText("股权投资");
                earlyType.setTextColor(Color.BLUE);
                popupWindow.dismiss();
            }
        });
        earlyService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EarlyQueue(StringUrl.earlyServiceUrl);
                earlyType.setText("企业服务");
                earlyType.setTextColor(Color.BLUE);
                popupWindow.dismiss();
            }
        });
        earlyQuick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EarlyQueue(StringUrl.earlyQuickUrl);
                earlyType.setText("极速融资");
                earlyType.setTextColor(Color.BLUE);
                popupWindow.dismiss();
            }
        });

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("FoundEarlyActivity", "onTouch : ");
                return false;
            }
        });

        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape));

        popupWindow.showAsDropDown(v);


    }
//解析点击事件的方法
    private void EarlyQueue(String url) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
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


}
