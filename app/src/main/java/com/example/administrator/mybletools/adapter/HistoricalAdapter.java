package com.example.administrator.mybletools.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.mybletools.R;
import com.example.yukunlin.physiotherapydevice.module.Device;

import java.util.List;

/**
 * 项目名：MyBleTools
 * 包名：com.example.administrator.mybletools.adapter
 * 文件名：HistoricalAdapter
 * 创建者 ：梅华黎
 * 创建时间： 2017/10/12 0012 9:36
 * 描述：TODO
 */
public class HistoricalAdapter extends BaseQuickAdapter<Device,BaseViewHolder> {

    public HistoricalAdapter(int layoutResId, @Nullable List<Device> data, Context context) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Device device) {
        // 设置蓝牙名称
        baseViewHolder.setText(R.id.tv_BleName,device.getName());
        //设置蓝牙MAC 地址
        baseViewHolder.setText(R.id.tv_BleAddress,device.getMacAddress());
        //设置蓝牙 连接状态

    }
}
