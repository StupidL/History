package me.stupideme.history;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by StupidL on 2016/8/17.
 */

public class ListViewAdapter extends BaseAdapter {
    private List<Event> mList;
    private Context mContext;

    ListViewAdapter(List<Event> list, Context context) {
        mList = list;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_event_list, null);
            viewHolder.cardView = (CardView) convertView.findViewById(R.id.event_card);
            viewHolder.title = (TextView) convertView.findViewById(R.id.event_title);
            viewHolder.date = (TextView) convertView.findViewById(R.id.event_date);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Event event = mList.get(position);
        viewHolder.title.setText(event.getTitle());
        String time = event.getYear()+"年"+event.getMonth()+"月"+event.getDay()+"日";
        viewHolder.date.setText(time);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,EventDetailActivity.class);
                intent.putExtra("id",event.getId());
                System.out.println("++++++"+event.getId()+"+++++");
                intent.putExtra("picUrl",event.getPicUrl());
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

     private class ViewHolder {
         CardView cardView;
        TextView title;
        TextView date;
    }
}
