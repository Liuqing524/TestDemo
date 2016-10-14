package com.example.dllo.testdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.bean.NewsListViewBean;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dllo on 16/9/20.
 */
public class NewsListViewAdapter extends BaseAdapter{
  private  NewsListViewBean bean;
    private Context context;
    Date date = new Date(System.currentTimeMillis());
    long time1 =System.currentTimeMillis();

    SimpleDateFormat format = new SimpleDateFormat("HH:mm");

    public NewsListViewAdapter(Context context) {
        this.context = context;
    }

    public void setBean(NewsListViewBean bean) {
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean.getData().getData().size();
    }

    @Override
    public Object getItem(int position) {
        return bean.getData().getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.news_listview,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(bean.getData().getData().get(position).getFeatureImg()).into(viewHolder.imageView);
        viewHolder.title.setText(bean.getData().getData().get(position).getTitle());

        viewHolder.name.setText(bean.getData().getData().get(position).getUser().getName());
        viewHolder.columnname.setText(bean.getData().getData().get(position).getColumnName());




        long str =bean.getData().getData().get(position).getPublishTime();

      //  long finaltime = time1 - str;

        viewHolder.time.setText(format.format(str));
        return convertView;
    }
    class ViewHolder{
        ImageView imageView;
        TextView title,name,time,columnname;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.kr_news_lisrview_img);
            title = (TextView) view.findViewById(R.id.kr_news_lisrview_title);
            name = (TextView) view.findViewById(R.id.kr_news_lisrview_name);
            columnname = (TextView) view.findViewById(R.id.kr_news_lisrview_columnname);
            time = (TextView) view.findViewById(R.id.kr_news_lisrview_time);


        }
    }

}
