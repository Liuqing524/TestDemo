package com.example.dllo.testdemo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.base.BaseAty;
import com.example.dllo.testdemo.base.MemoryCache;
import com.example.dllo.testdemo.base.MyImageListener;
import com.example.dllo.testdemo.base.NewsDrawable;
import com.example.dllo.testdemo.bean.NewsDetailsBean;
import com.example.dllo.testdemo.dialog.NewsDetailsDialogActivity;
import com.example.dllo.testdemo.dialog.NewsDetailsShareActivity;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dllo on 16/9/23.
 */
public class NewsListViewDetailsActivity extends BaseAty implements View.OnClickListener{
    private WebView webView;
    private String urlList,feedUrl,urlName,urlAvatar,urlTitle;
    private ImageView imageView;
    private TextView author,title,time,context;
    private Html.ImageGetter imageGetter;
    private Long urlTime;
    private ImageButton back,more,share;

    @Override
    protected int setLayout() {
        return R.layout.newslistviewdetails;
    }

    @Override
    protected void initView() {
        imageView = bindView(R.id.news_details_imageview);
         author = bindView(R.id.news_details_author);
         title = bindView(R.id.news_details_titles);
         time = bindView(R.id.news_details_times);
         context = bindView(R.id.news_details_contents);
        back = bindView(R.id.news_details_icon_back);
         more = bindView(R.id.news_details_more);
        share = bindView(R.id.news_details_share);



    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
       feedUrl = intent.getStringExtra("url");
        urlName = intent.getStringExtra("name");
        urlAvatar = intent.getStringExtra("avatar");
        urlTitle = intent.getStringExtra("title");
        urlTime = intent.getLongExtra("time" , 0);
        Date date = new Date(urlTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
        String finalTime = dateFormat.format(date);

        final RequestQueue queue = VolleySingleton.getInstance().getRequestQueue();
        final ImageLoader imageLoader = new ImageLoader(queue , new MemoryCache());
        imageLoader.get(urlAvatar , new MyImageListener(imageView));
        author.setText(urlName);
        title.setText(urlTitle);
        time.setText(finalTime);

        final StringRequest stringRequest = new StringRequest(feedUrl, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                NewsDetailsBean bean = gson.fromJson(response , NewsDetailsBean.class);
                String details = bean.getData().getContent();

                //设置滚动方法
                context.setMovementMethod(ScrollingMovementMethod.getInstance());
                //设置超链接可以打开网页
                context.setMovementMethod(LinkMovementMethod.getInstance());
                context.setText(Html.fromHtml(details , imageGetter , null));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
        imageGetter = new Html.ImageGetter(){

            @Override
            public Drawable getDrawable(final String source) {
                final NewsDrawable drawable = new NewsDrawable();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Bitmap bitmap = Picasso.with(MyAPP.getContext()).load(source).get();
                            runOnUiThread(new MyRunnable(bitmap){

                                @Override
                                public void run() {
                                    int width;
                                    DisplayMetrics displayMetrics = new DisplayMetrics();
                                    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                                    width = displayMetrics.widthPixels;
                                    float scaleWidth = width / bitmap.getWidth();
                                    // 取得想要缩放的matrix参数
                                    Matrix matrix = new Matrix();
                                    matrix.postScale(scaleWidth, scaleWidth);

                                //        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);


                                    Log.d("NewsListViewDetailsActi", "bitmap.getWidth():" + bitmap.getHeight());
                                    drawable.bitmap = bitmap;
                                    drawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                                    context.invalidate();
                                    context.setText(context.getText()); // 解决图文重叠
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                return drawable;
            }
        };

        back.setOnClickListener(this);
        more.setOnClickListener(this);
        share.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.news_details_icon_back:
                finish();
                break;
            case R.id.news_details_more:
                Intent intent = new Intent(NewsListViewDetailsActivity.this , NewsDetailsDialogActivity.class);
                startActivity(intent);
                break;
            case R.id.news_details_share:
                startActivity(new Intent(NewsListViewDetailsActivity.this, NewsDetailsShareActivity.class));
                break;
        }
    }

    abstract class MyRunnable implements Runnable {
        protected Bitmap bitmap;
        public MyRunnable(Bitmap bitmap) {
            this.bitmap = bitmap;
        }
    }
}
