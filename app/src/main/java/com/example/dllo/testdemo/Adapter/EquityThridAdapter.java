package com.example.dllo.testdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.bean.EquityThridBean;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/9/21.
 */
public class EquityThridAdapter extends BaseAdapter{
    private EquityThridBean thridBean;
    private Context context;

    public void setThridBean(EquityThridBean thridBean) {
        this.thridBean = thridBean;
    }

    public EquityThridAdapter(Context context) {

        this.context = context;
    }

    @Override
    public int getCount() {
        return thridBean.getData().getData().size();
    }

    @Override
    public Object getItem(int position) {
        return thridBean.getData().getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ThridViewHolder thridViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.equitythridfragment,parent,false);
            thridViewHolder = new ThridViewHolder(convertView);
            convertView.setTag(thridViewHolder);
        } else {
            thridViewHolder = (ThridViewHolder) convertView.getTag();
        }

        Picasso.with(context).load(thridBean.getData().getData().get(position).getCompany_logo()).into(thridViewHolder.logo);
        Picasso.with(context).load(thridBean.getData().getData().get(position).getFile_list_img()).into(thridViewHolder.img);

        thridViewHolder.companyname.setText(thridBean.getData().getData().get(position).getCompany_name());


        thridViewHolder.companybrief.setText(thridBean.getData().getData().get(position).getCompany_brief());

        thridViewHolder.leadname.setText(thridBean.getData().getData().get(position).getLead_name());
        thridViewHolder.adnameone.setText(thridBean.getData().getData().get(position).getCf_advantage().get(0).getAdname());
        thridViewHolder.adcontentone.setText(thridBean.getData().getData().get(position).getCf_advantage().get(0).getAdcontent());
        thridViewHolder.adnametwo.setText(thridBean.getData().getData().get(position).getCf_advantage().get(1).getAdname());
        thridViewHolder.adcontenttwo.setText(thridBean.getData().getData().get(position).getCf_advantage().get(1).getAdcontent());
        thridViewHolder.desc.setText(thridBean.getData().getData().get(position).getFundStatus().getDesc());
        thridViewHolder.offer.setText("已募资" + (int) (thridBean.getData().getData().get(position).getRate() * 100) + "%");
        thridViewHolder.progressBar.setProgress((int) (thridBean.getData().getData().get(position).getRate() * 100));


        return convertView;
    }
    class ThridViewHolder{
        ImageView logo,img;
        TextView companyname,companybrief,leadname,adnameone,adcontentone,adnametwo,adcontenttwo,desc,offer;
        ProgressBar progressBar;
        public ThridViewHolder(View view) {
            logo = (ImageView) view.findViewById(R.id.kr_equity_thrid_logo);
            img = (ImageView) view.findViewById(R.id.kr_equity_thrid_first_img);
            companyname = (TextView) view.findViewById(R.id.kr_equity_thrid_company_name);
            companybrief = (TextView) view.findViewById(R.id.kr_equity_thrid_company_brief);
            leadname = (TextView) view.findViewById(R.id.kr_equity_thrid_lead_name);
            adnameone = (TextView) view.findViewById(R.id.kr_eqyity_thrid_adname_one);
            adcontentone = (TextView) view.findViewById(R.id.kr_equity_thrid_adcontent_one);
            adnametwo = (TextView) view.findViewById(R.id.kr_eqyity_thrid_adname_two);
            adcontenttwo = (TextView) view.findViewById(R.id.kr_equity_thrid_adcontent_two);
            desc = (TextView) view.findViewById(R.id.kr_equity_thrid_desc);
            offer = (TextView) view.findViewById(R.id.kr_equity_thrid_offer);
            progressBar = (ProgressBar) view.findViewById(R.id.kr_equity_thrid_pb);
        }
    }

}
