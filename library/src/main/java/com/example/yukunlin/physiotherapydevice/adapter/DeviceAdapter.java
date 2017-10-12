/*
package com.example.yukunlin.physiotherapydevice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yukunlin.physiotherapydevice.R;
import com.example.yukunlin.physiotherapydevice.module.Device;

import java.util.List;

*/
/**
 * Created by yukunlin on 2016/11/2.
 *//*


public class DeviceAdapter extends BaseAdapter {
    private Context context;
    private List<Device> deviceList;

    public DeviceAdapter(Context context) {
        this.context = context;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    @Override
    public int getCount() {
        return deviceList == null ? 0 : deviceList.size();
    }

    @Override
    public Object getItem(int position) {
        return deviceList == null ? 0 : deviceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_device, viewGroup,false);
            viewHolder.deviceName = (TextView) view.findViewById(R.id.deviceName);
            viewHolder.deviceImageView = (ImageView) view.findViewById(R.id.deviceImageView);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Device device = deviceList.get(position);
        viewHolder.deviceImageView.setImageResource(R.drawable.test);
        viewHolder.deviceName.setText(device.getName());
        return view;
    }

    class ViewHolder {
        TextView deviceName;
        ImageView deviceImageView;
    }
}
*/
