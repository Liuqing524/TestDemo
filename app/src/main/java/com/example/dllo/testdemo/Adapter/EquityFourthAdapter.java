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
import com.example.dllo.testdemo.bean.EquityFourthBean;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/9/21.
 */
public class EquityFourthAdapter extends BaseAdapter{
    private EquityFourthBean fourthBean;
    private Context context;

    public void setFourthBean(EquityFourthBean fourthBean) {
        this.fourthBean = fourthBean;
    }

    public EquityFourthAdapter(Context context) {

        this.context = context;
    }

    @Override
    public int getCount() {
        return fourthBean.getData().getData().size();
    }

    @Override
    public Object getItem(int position) {
        return fourthBean.getData().getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FourthViewHolder fourthViewHolder = null;
        if (convertView == null) {
           convertView = LayoutInflater.from(context).inflate(R.layout.equityfourthfragment,parent,false);
            fourthViewHolder = new FourthViewHolder(convertView);
            convertView.setTag(fourthViewHolder);
        } else {
            fourthViewHolder = (FourthViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(fourthBean.getData().getData().get(position).getCompany_logo()).into(fourthViewHolder.logo);
        Picasso.with(context).load(fourthBean.getData().getData().get(position).getFile_list_img()).into(fourthViewHolder.img);

        fourthViewHolder.companyname.setText(fourthBean.getData().getData().get(position).getCompany_name());


        fourthViewHolder.companybrief.setText(fourthBean.getData().getData().get(position).getCompany_brief());

        fourthViewHolder.leadname.setText(fourthBean.getData().getData().get(position).getLead_name());
        fourthViewHolder.adnameone.setText(fourthBean.getData().getData().get(position).getCf_advantage().get(0).getAdname());
        fourthViewHolder.adcontentone.setText(fourthBean.getData().getData().get(position).getCf_advantage().get(0).getAdcontent());
        fourthViewHolder.adnametwo.setText(fourthBean.getData().getData().get(position).getCf_advantage().get(1).getAdname());
        fourthViewHolder.adcontenttwo.setText(fourthBean.getData().getData().get(position).getCf_advantage().get(1).getAdcontent());
        fourthViewHolder.desc.setText(fourthBean.getData().getData().get(position).getFundStatus().getDesc());

        fourthViewHolder.offer.setText("已募资" + (int) (fourthBean.getData().getData().get(position).getRate() * 100) + "%");
        fourthViewHolder.progressBar.setProgress((int) (fourthBean.getData().getData().get(position).getRate() * 100));
        return convertView;
    }
    class FourthViewHolder{
        ImageView logo,img;
        ProgressBar progressBar;
        TextView companyname,companybrief,leadname,adnameone,adcontentone,adnametwo,adcontenttwo,desc,offer;
        public FourthViewHolder(View view) {
            logo = (ImageView) view.findViewById(R.id.kr_equity_fourth_logo);
            img = (ImageView) view.findViewById(R.id.kr_equity_fourth_first_img);
            companyname = (TextView) view.findViewById(R.id.kr_equity_fourth_company_name);
            companybrief = (TextView) view.findViewById(R.id.kr_equity_fourth_company_brief);
            leadname = (TextView) view.findViewById(R.id.kr_equity_fourth_lead_name);
            adnameone = (TextView) view.findViewById(R.id.kr_eqyity_fourth_adname_one);
            adcontentone = (TextView) view.findViewById(R.id.kr_equity_fourth_adcontent_one);
            adnametwo = (TextView) view.findViewById(R.id.kr_eqyity_fourth_adname_two);
            adcontenttwo = (TextView) view.findViewById(R.id.kr_equity_fourth_adcontent_two);
            desc = (TextView) view.findViewById(R.id.kr_equity_fourth_desc);
            offer = (TextView) view.findViewById(R.id.kr_equity_fourth_offer);
            progressBar = (ProgressBar) view.findViewById(R.id.kr_equity_fourth_pb);
        }
    }
}
