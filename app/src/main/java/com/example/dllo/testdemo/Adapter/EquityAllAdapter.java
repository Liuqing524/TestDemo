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
import com.example.dllo.testdemo.bean.EquityAllBean;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/9/21.
 */
public class EquityAllAdapter extends BaseAdapter{
  private   EquityAllBean bean;
    private Context context;

    public EquityAllAdapter(Context context) {
        this.context = context;
    }

    public void setBean(EquityAllBean bean) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.equityallfragment,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(bean.getData().getData().get(position).getCompany_logo()).into(viewHolder.logo);
        Picasso.with(context).load(bean.getData().getData().get(position).getFile_list_img()).into(viewHolder.img);

        viewHolder.companyname.setText(bean.getData().getData().get(position).getCompany_name());


        viewHolder.companybrief.setText(bean.getData().getData().get(position).getCompany_brief());

        viewHolder.leadname.setText(bean.getData().getData().get(position).getLead_name());
        viewHolder.adnameone.setText(bean.getData().getData().get(position).getCf_advantage().get(0).getAdname());
        viewHolder.adcontentone.setText(bean.getData().getData().get(position).getCf_advantage().get(0).getAdcontent());
        viewHolder.adnametwo.setText(bean.getData().getData().get(position).getCf_advantage().get(1).getAdname());
        viewHolder.adcontenttwo.setText(bean.getData().getData().get(position).getCf_advantage().get(1).getAdcontent());
        viewHolder.desc.setText(bean.getData().getData().get(position).getFundStatus().getDesc());

        viewHolder.offer.setText("已募资" + (int) (bean.getData().getData().get(position).getRate() * 100) + "%");
        viewHolder.progressBar.setProgress((int) (bean.getData().getData().get(position).getRate() * 100));
        return convertView;
    }
    class ViewHolder{
        ImageView logo,img;
        TextView companyname,companybrief,leadname,adnameone,adcontentone,adnametwo,adcontenttwo,desc,offer;
        ProgressBar progressBar;
        public ViewHolder(View view) {
            logo = (ImageView) view.findViewById(R.id.kr_equity_all_logo);
            img = (ImageView) view.findViewById(R.id.kr_equity_all_first_img);
            companyname = (TextView) view.findViewById(R.id.kr_equity_all_company_name);
            companybrief = (TextView) view.findViewById(R.id.kr_equity_all_company_brief);
            leadname = (TextView) view.findViewById(R.id.kr_equity_all_lead_name);
            adnameone = (TextView) view.findViewById(R.id.kr_eqyity_all_adname_one);
            adcontentone = (TextView) view.findViewById(R.id.kr_equity_all_adcontent_one);
            adnametwo = (TextView) view.findViewById(R.id.kr_eqyity_all_adname_two);
            adcontenttwo = (TextView) view.findViewById(R.id.kr_equity_all_adcontent_two);
            desc = (TextView) view.findViewById(R.id.kr_equity_all_desc);
            offer = (TextView) view.findViewById(R.id.kr_equity_all_offer);
            progressBar = (ProgressBar) view.findViewById(R.id.kr_equity_all_pb);

        }
    }
}
