/*
package com.example.yukunlin.physiotherapydevice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yukunlin.physiotherapydevice.R;
import com.example.yukunlin.physiotherapydevice.module.History;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

*/
/**
 * Created by yukunlin on 2016/11/10.
 *//*


public class HistoryAdapter extends BaseAdapter {
    private Context context;
    private List<History> data;

    public List<History> getData() {
        return data;
    }

    public void setData(List<History> data) {
        this.data = data;
    }

    public HistoryAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_history, viewGroup, false);
            viewHolder = new ViewHolder(view);
            ButterKnife.inject(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (data != null) {
            History history = data.get(position);
            String startTime = history.getStarttime();
            String year = startTime.substring(0, 4);
            String month = startTime.substring(4, 6);
            String day = startTime.substring(6, 8);
            String hour = startTime.substring(8, 10);
            String minute = startTime.substring(10, 12);
            String second = startTime.substring(12, 14);
            viewHolder.date.setText(year + "-" + month + "-" + day + "\r\n" + hour + ":" + minute + ":" + second);
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
//            viewHolder.date.setText(simpleDateFormat.format(history.getStartTime()));
//            viewHolder.date.setText(history.getStartTime());
            viewHolder.workTime.setText(history.getWorktime());
            viewHolder.ifr.setText(history.getIrworktime());
            viewHolder.concentration.setText(history.getCc());
            if (history.getMode().equals("1")) {
                viewHolder.mode.setText("男人");
            } else if (history.getMode().equals("2")) {
                viewHolder.mode.setText("女人");
            } else if (history.getMode().equals("3")) {
                viewHolder.mode.setText("小孩");
            }
//            viewHolder.mode.setText(history.getMode());
            viewHolder.power.setText(history.getPower()+"W");
        }
        return view;
    }

    class ViewHolder {
        @InjectView(R.id.date)
        TextView date;
        @InjectView(R.id.power)
        TextView power;
        @InjectView(R.id.workTime)
        TextView workTime;
        @InjectView(R.id.concentration)
        TextView concentration;
        @InjectView(R.id.mode)
        TextView mode;
        @InjectView(R.id.IFR)
        TextView ifr;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
*/
