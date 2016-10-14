package com.example.dllo.testdemo.dialog;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.activity.NewsListViewDetailsActivity;
import com.example.dllo.testdemo.base.BaseAty;
import com.example.dllo.testdemo.bean.NewsSerachBean;
import com.example.dllo.testdemo.bean.SerachHelper;
import com.example.dllo.testdemo.secondAdapter.NewsSerachAdapter;
import com.google.gson.Gson;

/**
 * Created by dllo on 16/9/29.
 */
public class SerachActivity extends BaseAty implements View.OnClickListener{
    private ListView listViewQian, listViewHou;
    private ImageButton searchAll,searchDetele;
    private TextView imageButtonDe;
    private EditText editText;
    private View lvQian,lvHou , lvQianTall;
    private SimpleCursorAdapter simpleCursorAdapter;
    private SQLiteDatabase db;
    private SerachHelper helper;
    private String stringText = new String();
    private String stringUrl;
    private NewsSerachBean response;
    private NewsSerachAdapter adapter;

    @Override
    protected int setLayout() {
        return R.layout.activity_serach;

    }

    @Override
    protected void initView() {
        listViewHou = bindView(R.id.serach_lv_hou);
        listViewQian = bindView(R.id.serach_lv_qian);
        searchAll = bindView(R.id.serach_ib_ser);
        searchDetele = bindView(R.id.serach_ib_delete);
        imageButtonDe = bindView(R.id.search_tv_quxiao);
        editText = bindView(R.id.serach_et_ser);
        lvQian = LayoutInflater.from(this).inflate(R.layout.serach , null);
        lvHou = LayoutInflater.from(this).inflate(R.layout.search_news , null);
        lvQianTall = LayoutInflater.from(this).inflate(R.layout.search_tall , null);
        listViewQian.addHeaderView(lvHou);

        listViewHou.addHeaderView(lvQian);
        listViewHou.addFooterView(lvQianTall);

        helper = new SerachHelper(this ,"kr.db" , null , 1);
        db = helper.getWritableDatabase();
        lvQianTall.setVisibility(View.INVISIBLE);
        lvQian.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initData() {
        searchAll.setOnClickListener(this);
      //  searchDetele.setOnClickListener(this);
        imageButtonDe.setOnClickListener(this);
        editText.setOnClickListener(this);
        lvQianTall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleData();

                    lvQianTall.setVisibility(View.INVISIBLE);
                    lvQian.setVisibility(View.INVISIBLE);
                qureyData("");



            }
        });
        adapter = new NewsSerachAdapter(this);
        qureyData("");
        listViewQian.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SerachActivity.this , NewsListViewDetailsActivity.class);

                    NewsSerachBean.DataBean.DataBean1 dataBean1 = (NewsSerachBean.DataBean.DataBean1) parent.getItemAtPosition(position);
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
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lvQianTall.setVisibility(View.VISIBLE);
                lvQian.setVisibility(View.VISIBLE);
               if (!TextUtils.isEmpty(s)) {
                  if (searchDetele != null) {
                      searchDetele.setVisibility(View.VISIBLE);
                      searchDetele.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              if (editText != null) {
                                  editText.getText().clear();
                              }
                          }
                      });
                  }
               } else {
                   if (searchDetele.getVisibility() == View.VISIBLE) {
                       searchDetele.setVisibility(View.GONE);
                   }
               }
            }

            @Override
            public void afterTextChanged(Editable s) {

                String tempName = editText.getText().toString();
                qureyData(tempName);

            }
        });

    }

    private void qureyData(String tempName) {
        final Cursor cursor = helper.getReadableDatabase().rawQuery("select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null);
                simpleCursorAdapter = new SimpleCursorAdapter(this , android.R.layout.simple_list_item_1 ,
                cursor , new String[]{"name"} , new int[]{android.R.id.text1} ,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listViewHou.setVisibility(View.VISIBLE);
        listViewHou.setAdapter(simpleCursorAdapter);
        listViewQian.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_tv_quxiao:
                finish();
                break;
            case R.id.serach_et_ser:
                listViewQian.setVisibility(v.GONE);

                boolean hasData = hasData(editText.getText().toString().trim());
                if (!hasData) {
                    insertData(editText.getText().toString().trim());
                    qureyData("");
                }
                stringText = editText.getText().toString();
                stringUrl = "https://rong.36kr.com/api/mobi/news/search?keyword=" + stringText + "&page=1&pageSize=3";
                RequestQueue queue = Volley.newRequestQueue(this);
                StringRequest stringRequest = new StringRequest(stringUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            Gson gson = new Gson();
                            NewsSerachBean bean = gson.fromJson(response , NewsSerachBean.class);
                            adapter.setSerachBean(bean);
                            listViewQian.setVisibility(View.VISIBLE);
                            listViewQian.setAdapter(adapter);
                            listViewHou.setVisibility(View.GONE);
                        } else {

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(stringRequest);
                break;

        }

    }
    private void deleData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
    }
    private void insertData(String tempName) {
        db = helper.getWritableDatabase();
        db.execSQL(" insert into records(name) values('" + tempName + "')");
        db.close();
    }

    private boolean hasData(String trim) {
        Cursor curson = helper.getReadableDatabase().rawQuery("select id as _id,name from records where name =? ", new String[]{trim});
       return curson.moveToNext();
    }
}
