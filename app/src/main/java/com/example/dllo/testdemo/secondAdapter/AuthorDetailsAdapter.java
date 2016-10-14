package com.example.dllo.testdemo.secondAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.bean.AuthorDetailsBean;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/9/29.
 */
public class AuthorDetailsAdapter extends BaseAdapter{
    AuthorDetailsBean detailsBean;
    Context context;

    public AuthorDetailsAdapter(Context context) {
        this.context = context;
    }

    public void setDetailsBean(AuthorDetailsBean detailsBean) {
        this.detailsBean = detailsBean;
    }

    @Override
    public int getCount() {
        return detailsBean == null ? 0 : detailsBean.getData().getLatestArticle().size();
    }

    @Override
    public Object getItem(int position) {
        return detailsBean.getData().getLatestArticle().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AuthorViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_author_details , parent , false);
            viewHolder = new AuthorViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (AuthorViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(detailsBean.getData().getLatestArticle().get(position).getFeatureImg()).into(viewHolder.imageView);
        viewHolder.title.setText(detailsBean.getData().getLatestArticle().get(position).getTitle());
        return convertView;
    }
    class AuthorViewHolder{
        ImageView imageView;
        TextView title;
        public AuthorViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.item_author_details_iv);
            title = (TextView) view.findViewById(R.id.item_author_details_title);

        }
    }
}
