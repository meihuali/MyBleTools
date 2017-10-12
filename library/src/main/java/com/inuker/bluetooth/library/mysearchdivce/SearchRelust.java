package com.inuker.bluetooth.library.mysearchdivce;

import com.example.yukunlin.physiotherapydevice.module.Device;
import com.inuker.bluetooth.library.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名：MyBleTools
 * 包名：com.inuker.bluetooth.library.mysearchdivce
 * 文件名：SearchRelust
 * 创建者 ：梅华黎
 * 创建时间： 2017/10/12 0012 15:50
 * 描述：TODO
 */
public class SearchRelust {
    private static ArrayList<Device> mlist2 = new ArrayList<>();

    public static List<Device> searchRelustss() {
        //这个接口回调是吧搜索到的设备回调到这个Activity 中来
        MySearchDivce.getSearchDevices(new MySearchDivce.onDivceListenr() {
            @Override
            public void searchRelust(List<Device> mlist) {
                L.e("搜到的设备 "+mlist.size());
                mlist2.addAll(mlist);
            }
        });

        return mlist2;

    }
}
