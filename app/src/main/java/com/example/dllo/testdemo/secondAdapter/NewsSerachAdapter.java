package com.example.dllo.testdemo.secondAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.bean.NewsSerachBean;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dllo on 16/9/30.
 */
public class NewsSerachAdapter extends BaseAdapter{
    NewsSerachBean serachBean;
    Context context;
    Date date = new Date(System.currentTimeMillis());
    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

    public NewsSerachAdapter(Context context) {
        this.context = context;
    }

    public void setSerachBean(NewsSerachBean serachBean) {
        this.serachBean = serachBean;
    }

    @Override
    public int getCount() {
        return serachBean.getData().getData().size();
    }

    @Override
    public Object getItem(int position) {
        return serachBean.getData().getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SerachViewHolder serachViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_search_lv , parent , false);
            serachViewHolder = new SerachViewHolder(convertView);
            convertView.setTag(serachViewHolder);
        } else {
            serachViewHolder = (SerachViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(serachBean.getData().getData().get(position).getFeatureImg()).into(serachViewHolder.image);
        serachViewHolder.title.setText(serachBean.getData().getData().get(position).getTitle());
        serachViewHolder.name.setText(serachBean.getData().getData().get(position).getUser().getName());
        serachViewHolder.columnname.setText(serachBean.getData().getData().get(position).getColumnName());

        serachViewHolder.time.setText(format.format(date));



        return convertView;
    }
    class SerachViewHolder{
        ImageView image;
        TextView title,time,name,columnname;
        public SerachViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.item_serach_image);
            title = (TextView) view.findViewById(R.id.item_serach_title);
            time = (TextView) view.findViewById(R.id.item_serach_lisrview_time);
            name = (TextView) view.findViewById(R.id.item_serach_name);
            columnname = (TextView) view.findViewById(R.id.item_serach_lisrview_columnname);

        }
    }
}
