package com.example.dllo.testdemo.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.base.BaseAty;
import com.example.dllo.testdemo.base.MemoryCache;
import com.example.dllo.testdemo.base.MyImageListener;
import com.example.dllo.testdemo.base.NewsDrawable;
import com.example.dllo.testdemo.bean.AuthorDetailsBean;
import com.example.dllo.testdemo.bean.NewsDetailsBean;
import com.example.dllo.testdemo.dialog.NewsDetailsDialogActivity;
import com.example.dllo.testdemo.secondAdapter.AuthorDetailsAdapter;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by dllo on 16/9/23.
 */
public class NewsListViewDetailsActivity extends BaseAty implements View.OnClickListener {
    private WebView webView;
    private String urlList, feedUrl, urlName, urlAvatar, urlTitle, feedID;
    private ImageView imageView;
    private TextView author, title, time, context, briefAu, authorCount, authorTotal;
    private Html.ImageGetter imageGetter;
    private Long urlTime;
    private ImageButton back, more, share, popDown;
    private AuthorDetailsBean authorBean;
    private AuthorDetailsAdapter adapter;
    private ListView listView;

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
        popDown = bindView(R.id.news_details_down_jian);
        briefAu = bindView(R.id.news_details_berif);
        ShareSDK.initSDK(this,"sharesdk的appkey");

        adapter = new AuthorDetailsAdapter(this);


    }

    //接收数据 解析内容
    @Override
    protected void initData() {
        Intent intent = getIntent();
        feedUrl = intent.getStringExtra("url");
        feedID = intent.getStringExtra("ID");
        urlName = intent.getStringExtra("name");
        urlAvatar = intent.getStringExtra("avatar");
        urlTitle = intent.getStringExtra("title");
        urlTime = intent.getLongExtra("time", 0);
        Date date = new Date(urlTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
        String finalTime = dateFormat.format(date);

        final RequestQueue queue = VolleySingleton.getVolleySingleton().getRequestQueue();
        final ImageLoader imageLoader = new ImageLoader(queue, new MemoryCache());
        imageLoader.get(urlAvatar, new MyImageListener(imageView));
        author.setText(urlName);
        title.setText(urlTitle);
        time.setText(finalTime);

        final StringRequest stringRequest = new StringRequest(feedUrl, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                NewsDetailsBean bean = gson.fromJson(response, NewsDetailsBean.class);
                String details = bean.getData().getContent();


                //设置滚动方法
                context.setMovementMethod(ScrollingMovementMethod.getInstance());
                //设置超链接可以打开网页
                context.setMovementMethod(LinkMovementMethod.getInstance());
                context.setText(Html.fromHtml(details, imageGetter, null));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
        imageGetter = new Html.ImageGetter() {

            @Override
            public Drawable getDrawable(final String source) {
                final NewsDrawable drawable = new NewsDrawable();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Bitmap bitmap = Picasso.with(NewsListViewDetailsActivity.this).load(source).get();
                            runOnUiThread(new MyRunnable(bitmap) {

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
                                    //       bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

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
        popDown.setOnClickListener(this);
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.news_details_icon_back:
                finish();
                break;
            case R.id.news_details_more:
                Intent intent = new Intent(NewsListViewDetailsActivity.this, NewsDetailsDialogActivity.class);
                startActivity(intent);
                break;
            case R.id.news_details_share:
                showShare();
                break;
            case R.id.news_details_down_jian:
                popupWindowAuthorDetails(v);
                AuthorQueue();
                break;
        }
    }

    //pop
    private void popupWindowAuthorDetails(View v) {
        View authorView = LayoutInflater.from(this).inflate(R.layout.pop_window_author, null);
        listView = (ListView) authorView.findViewById(R.id.pop_window_author_listview);
        authorCount = (TextView) authorView.findViewById(R.id.pop_window_author_count);
        authorTotal = (TextView) authorView.findViewById(R.id.pop_window_author_total);
        final PopupWindow popupWindow = new PopupWindow(authorView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return false;
            }
        });

        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape));

        popupWindow.showAsDropDown(v);

    }

    abstract class MyRunnable implements Runnable {
        protected Bitmap bitmap;

        public MyRunnable(Bitmap bitmap) {
            this.bitmap = bitmap;
        }
    }

    // pop 数据获取
    private void AuthorQueue() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest("https://rong.36kr.com/api/mobi/news/" + feedID + "/author-region",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        AuthorDetailsBean authorDetailsBean = gson.fromJson(response, AuthorDetailsBean.class);
                        String brief = authorDetailsBean.getData().getBrief();
                        int count = authorDetailsBean.getData().getTotalCount();

                        int total = authorDetailsBean.getData().getTotalView();

                        briefAu.setText(brief);
                        authorCount.setText(count + "篇");
                        authorTotal.setText(total / 10000 + "万");
                        adapter.setDetailsBean(authorDetailsBean);
                        listView.setAdapter(adapter);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }
    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
//关闭sso授权
        oks.disableSSOWhenAuthorize();

// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
// titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl("http://sharesdk.cn");
// text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
// url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
// comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
// site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
// siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");
       //添加自定义模块
        Bitmap enableLogo = BitmapFactory.decodeResource(this.getResources() , R.mipmap.common_icon_share_link);
        String label = "复制链接";
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager copy = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                copy.setText(feedUrl);
                Toast.makeText(NewsListViewDetailsActivity.this, "复制成功", Toast.LENGTH_LONG).show();
            }
        };
        oks.setCustomerLogo(enableLogo , label , listener);

// 启动分享GUI
        oks.show(this);
    }


}
