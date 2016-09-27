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
import com.example.dllo.testdemo.bean.EquitySecondBean;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/9/21.
 */
public class EquitySecondAdapter extends BaseAdapter{
    private EquitySecondBean secondBean;
    private Context context;

    public void setSecondBean(EquitySecondBean secondBean) {
        this.secondBean = secondBean;
    }

    public EquitySecondAdapter(Context context) {

        this.context = context;
    }

    @Override
    public int getCount() {
        return secondBean.getData().getData().size();
    }

    @Override
    public Object getItem(int position) {
        return secondBean.getData().getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderSecond viewHolderSecond = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.equitysecondfragment,parent,false);
            viewHolderSecond = new ViewHolderSecond(convertView);
            convertView.setTag(viewHolderSecond);
        } else {
            viewHolderSecond = (ViewHolderSecond) convertView.getTag();
        }
        Picasso.with(context).load(secondBean.getData().getData().get(position).getCompany_logo()).into(viewHolderSecond.logo);
        Picasso.with(context).load(secondBean.getData().getData().get(position).getFile_list_img()).into(viewHolderSecond.img);

        viewHolderSecond.companyname.setText(secondBean.getData().getData().get(position).getCompany_name());


        viewHolderSecond.companybrief.setText(secondBean.getData().getData().get(position).getCompany_brief());

        viewHolderSecond.leadname.setText(secondBean.getData().getData().get(position).getLead_name());
        viewHolderSecond.adnameone.setText(secondBean.getData().getData().get(position).getCf_advantage().get(0).getAdname());
        viewHolderSecond.adcontentone.setText(secondBean.getData().getData().get(position).getCf_advantage().get(0).getAdcontent());
        viewHolderSecond.adnametwo.setText(secondBean.getData().getData().get(position).getCf_advantage().get(1).getAdname());
        viewHolderSecond.adcontenttwo.setText(secondBean.getData().getData().get(position).getCf_advantage().get(1).getAdcontent());
        viewHolderSecond.desc.setText(secondBean.getData().getData().get(position).getFundStatus().getDesc());
        viewHolderSecond.offer.setText("已募资" + (int) (secondBean.getData().getData().get(position).getRate() * 100) + "%");
        viewHolderSecond.progressBar.setProgress((int) (secondBean.getData().getData().get(position).getRate() * 100));

        return convertView;
    }
    class ViewHolderSecond{
        ImageView logo,img;
        TextView companyname,companybrief,leadname,adnameone,adcontentone,adnametwo,adcontenttwo,desc,offer;
        ProgressBar progressBar;
        public ViewHolderSecond(View view) {
            logo = (ImageView) view.findViewById(R.id.kr_equity_second_logo);
            img = (ImageView) view.findViewById(R.id.kr_equity_second_first_img);
            companyname = (TextView) view.findViewById(R.id.kr_equity_second_company_name);
            companybrief = (TextView) view.findViewById(R.id.kr_equity_second_company_brief);
            leadname = (TextView) view.findViewById(R.id.kr_equity_second_lead_name);
            adnameone = (TextView) view.findViewById(R.id.kr_eqyity_second_adname_one);
            adcontentone = (TextView) view.findViewById(R.id.kr_equity_second_adcontent_one);
            adnametwo = (TextView) view.findViewById(R.id.kr_eqyity_second_adname_two);
            adcontenttwo = (TextView) view.findViewById(R.id.kr_equity_second_adcontent_two);
            desc = (TextView) view.findViewById(R.id.kr_equity_second_desc);
            offer = (TextView) view.findViewById(R.id.kr_equity_second_offer);
            progressBar = (ProgressBar) view.findViewById(R.id.kr_equity_second_pb);
        }
    }

}
