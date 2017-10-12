/*
package com.example.yukunlin.physiotherapydevice.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yukunlin.physiotherapydevice.R;
import com.example.yukunlin.physiotherapydevice.module.Device;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

*/
/**
 * Created by yukunlin on 2016/11/25.
 *//*


public class AddDeviceAdapter extends BaseAdapter {
    private Context context;
    private List<Device> data;

    public List<Device> getData() {
        return data;
    }

    public void setData(List<Device> data) {
        this.data = data;
    }

    public AddDeviceAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_device, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        String name = data.get(i).getName();
        viewHolder.deviceName.setTextColor(Color.parseColor("#111111"));
        viewHolder.deviceImageView.setImageResource(R.drawable.test);
        viewHolder.deviceName.setText(R.string.name_liliaoyi);
        return view;
    }

    class ViewHolder {
        @InjectView(R.id.deviceImageView)
        ImageView deviceImageView;
        @InjectView(R.id.deviceName)
        TextView deviceName;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
*/
