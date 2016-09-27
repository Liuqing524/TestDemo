package com.example.dllo.testdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.bean.FoundEarlyBean;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/9/27.
 */
public class FoundEarlyAdapter extends BaseAdapter{
    private Context context;
    private FoundEarlyBean foundEarlyBean;

    public FoundEarlyAdapter(Context context) {
        this.context = context;
    }

    public void setFoundEarlyBean(FoundEarlyBean foundEarlyBean) {
        this.foundEarlyBean = foundEarlyBean;
    }

    @Override
    public int getCount() {
        return foundEarlyBean.getData().getData().size();
    }

    @Override
    public Object getItem(int position) {
        return foundEarlyBean.getData().getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EarlyViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_found_early , parent , false);
            viewHolder = new EarlyViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (EarlyViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(foundEarlyBean.getData().getData().get(position).getActivityImg()).into(viewHolder.image);
        viewHolder.status.setText(foundEarlyBean.getData().getData().get(position).getActivityStatus());
        viewHolder.city.setText(foundEarlyBean.getData().getData().get(position).getActivityCity());
        viewHolder.desc.setText(foundEarlyBean.getData().getData().get(position).getActivityDesc());
        viewHolder.name.setText(foundEarlyBean.getData().getData().get(position).getActivityName());
        viewHolder.time.setText(foundEarlyBean.getData().getData().get(position).getActivityTime());
        return convertView;
    }
    class EarlyViewHolder {
        ImageView image ;
        TextView name,time,desc,city,status;
        public EarlyViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.item_found_early_image);
            name = (TextView) view.findViewById(R.id.item_found_early_name);
            time = (TextView) view.findViewById(R.id.item_found_early_time);
            desc = (TextView) view.findViewById(R.id.item_found_early_desc);
            city = (TextView) view.findViewById(R.id.item_found_early_city);
            status = (TextView) view.findViewById(R.id.item_found_early_status);
        }
    }
}
