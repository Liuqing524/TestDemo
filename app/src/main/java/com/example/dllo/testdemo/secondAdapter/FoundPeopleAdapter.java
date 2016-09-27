package com.example.dllo.testdemo.secondAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.testdemo.R;
import com.example.dllo.testdemo.bean.FoundPeopleBean;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/9/27.
 */
public class FoundPeopleAdapter extends BaseAdapter{
    private FoundPeopleBean foundPeopleBean;
    private Context context;

    public FoundPeopleAdapter(Context context) {
        this.context = context;
    }

    public void setFoundPeopleBean(FoundPeopleBean foundPeopleBean) {
        this.foundPeopleBean = foundPeopleBean;
    }

    @Override
    public int getCount() {
        return foundPeopleBean==null?0:foundPeopleBean.getData().getData().size();
    }

    @Override
    public Object getItem(int position) {
        return foundPeopleBean.getData().getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FoundPeopleViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_found_people , parent , false);
            viewHolder = new FoundPeopleViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (FoundPeopleViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(foundPeopleBean.getData().getData().get(position).getUser().getAvatar()).into(viewHolder.imageView);
        viewHolder.name.setText(foundPeopleBean.getData().getData().get(position).getUser().getName());

        if (foundPeopleBean.getData().getData().get(position).getFocusIndustry().isEmpty()) {
            viewHolder.field.setText("未披露");
        } else {
            if (foundPeopleBean.getData().getData().get(position).getFocusIndustry().size() == 1) {
                viewHolder.field.setText(foundPeopleBean.
                        getData().getData().get(position).getFocusIndustry().get(0));
            } else if (foundPeopleBean.getData().getData().get(position).getFocusIndustry().size() == 3){

            viewHolder.field.setText(foundPeopleBean.
                    getData().getData().get(position).getFocusIndustry().get(0) + " " +
                    foundPeopleBean.getData().getData().get(position).getFocusIndustry().get(1)+ " " +
                    foundPeopleBean.getData().getData().get(position).getFocusIndustry().get(2));
            } else if (foundPeopleBean.getData().getData().get(position).getFocusIndustry().size() == 2){
                viewHolder.field.setText(foundPeopleBean.
                        getData().getData().get(position).getFocusIndustry().get(0) + " " +
                        foundPeopleBean.getData().getData().get(position).getFocusIndustry().get(1));
            }
        }
        if (foundPeopleBean.getData().getData().get(position).getInvestPhases().isEmpty()) {
            viewHolder.equity.setText("未披露");
        } else {
            if (foundPeopleBean.getData().getData().get(position).getInvestPhases().size() == 1) {
                viewHolder.equity.setText(foundPeopleBean.
                        getData().getData().get(position).getInvestPhases().get(0));
            } else if (foundPeopleBean.getData().getData().get(position).getInvestPhases().size() == 3){

                viewHolder.equity.setText(foundPeopleBean.
                        getData().getData().get(position).getInvestPhases().get(0) + " " +
                        foundPeopleBean.getData().getData().get(position).getInvestPhases().get(1)+ " " +
                        foundPeopleBean.getData().getData().get(position).getInvestPhases().get(2));
            } else if (foundPeopleBean.getData().getData().get(position).getInvestPhases().size() == 2){
                viewHolder.equity.setText(foundPeopleBean.
                        getData().getData().get(position).getInvestPhases().get(0) + " " +
                        foundPeopleBean.getData().getData().get(position).getInvestPhases().get(1));
            }
        }



        return convertView;
    }
    class FoundPeopleViewHolder{
        ImageView imageView;
        TextView name,field,equity;
        public FoundPeopleViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.item_found_people_image);
            name = (TextView) view.findViewById(R.id.item_found_people_name);
            field = (TextView) view.findViewById(R.id.item_found_people_field);
            equity = (TextView) view.findViewById(R.id.item_found_people_equity);
        }
    }
}
