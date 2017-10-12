/*
package com.example.yukunlin.physiotherapydevice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yukunlin.physiotherapydevice.R;
import com.example.yukunlin.physiotherapydevice.module.Device;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

*/
/**
 * Created by yukunlin on 2016/11/14.
 *//*


public class DeviceRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Device> data;
    private Context context;
    private final int TYPE_UNKNOWN = 0;
    private final int TYPE_NORMAL = 1;
    private final int TYPE_ONE_DEVICE = 2;
    private final int TYPE_TWO_DEVICE = 3;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public DeviceRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public List<Device> getData() {
        return data;
    }

    public void setData(List<Device> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORMAL) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_device, parent, false);
            return new DeviceViewHolder(view);
        } else if (viewType == TYPE_ONE_DEVICE) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_one_device, parent, false);
            return new OneDeviceViewHolder(view);
        } else if (viewType == TYPE_TWO_DEVICE) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_two_device, parent, false);
            return new TwoDeviceViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_unknown, parent, false);
            return new UnknownViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DeviceViewHolder) {
            setDataForDeviceViewHolder(holder, position);
        } else if (holder instanceof OneDeviceViewHolder) {
            setDataForOne(holder, position);
        } else if (holder instanceof TwoDeviceViewHolder) {
            setDataForTwo(holder, position);
        } else if (holder instanceof UnknownViewHolder) {
            setDataForUnknownViewHolder(holder, position);
        }
    }

    private void setDataForTwo(RecyclerView.ViewHolder holder, final int position) {
        TwoDeviceViewHolder viewHolder = (TwoDeviceViewHolder) holder;
        final Device device = data.get(0);
        final Device device2 = data.get(1);
        viewHolder.deviceName.setText(device.getName());
        viewHolder.deviceImageView.setImageResource(R.drawable.test);
        viewHolder.deviceName2.setText(device2.getName());
        viewHolder.deviceImageView2.setImageResource(R.drawable.test);
        viewHolder.deviceImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClickListener(device, position);
            }
        });
        viewHolder.deviceImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClickListener(device2, position);
            }
        });
    }

    private void setDataForOne(RecyclerView.ViewHolder holder, final int position) {
        OneDeviceViewHolder viewHolder = (OneDeviceViewHolder) holder;
        final Device device = data.get(0);
        viewHolder.deviceName.setText(device.getName());
        viewHolder.deviceImageView.setImageResource(R.drawable.test);
        viewHolder.deviceImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClickListener(device, position);
            }
        });
    }

    private void setDataForUnknownViewHolder(RecyclerView.ViewHolder holder, int position) {


    }

    private void setDataForDeviceViewHolder(RecyclerView.ViewHolder holder, final int position) {
        DeviceViewHolder viewHolder = (DeviceViewHolder) holder;
        final Device device = data.get(position);
        viewHolder.deviceName.setText(device.getName());
        viewHolder.deviceImageView.setImageResource(R.drawable.test);
        viewHolder.deviceImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClickListener(device, position);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {

        if (data.size() == 1) {
            return TYPE_ONE_DEVICE;
        } else if (data.size() == 2) {
            return TYPE_TWO_DEVICE;
        } else if (data.size() > 2) {
            return TYPE_NORMAL;
        } else {
            return TYPE_UNKNOWN;
        }
    }

    @Override
    public int getItemCount() {
        if (data == null){
            return 0;
        }else if(data.size() == 2){
            return 1;
        }else {
            return data.size();
        }
//        return data == null ? 0 : data.size();
    }

    class DeviceViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.deviceName)
        TextView deviceName;
        @InjectView(R.id.deviceImageView)
        ImageView deviceImageView;

        public DeviceViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }

    class UnknownViewHolder extends RecyclerView.ViewHolder {
        public UnknownViewHolder(View view) {
            super(view);
        }
    }

    class OneDeviceViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.deviceName)
        TextView deviceName;
        @InjectView(R.id.deviceImageView)
        ImageView deviceImageView;

        public OneDeviceViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }

    class TwoDeviceViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.deviceName)
        TextView deviceName;
        @InjectView(R.id.deviceImageView)
        ImageView deviceImageView;
        @InjectView(R.id.deviceName2)
        TextView deviceName2;
        @InjectView(R.id.deviceImageView2)
        ImageView deviceImageView2;

        public TwoDeviceViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }

    public interface OnItemClickListener {
        void onItemClickListener(Device device, int position);
    }
}
*/
