package com.example.administrator.mybletools;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.mybletools.adapter.HistoricalAdapter;
import com.example.yukunlin.physiotherapydevice.module.Device;
import com.inuker.bluetooth.library.mysearchdivce.BleConfig;
import com.inuker.bluetooth.library.mysearchdivce.MySearchDivce;
import com.inuker.bluetooth.library.utils.L;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BluetoothAdapter mBluetoothAdapter;
    private final static int REQUEST_CODE_PHONE_STATUS = 102;
    private final static int REQUEST_CODE_BLUETOOTH = 101;
    private RecyclerView mRecyclerView;
    private HistoricalAdapter historicalAdapter;
    private List<Device> mlist;
    private String macaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPermission();
        initBluetooth();
        initAdapter();
        initConnectDivce();

    }

    /*
    *
    *  搜索设备并且 设置adapter
    * */
    private void initAdapter() {
        //这句话是搜索蓝牙设备
        mlist = MySearchDivce.startSearchDevice(this);
        L.e("搜到的设备 "+mlist.size());
        //初始化recycleView
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        //设置 mRecyclerView 的管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        historicalAdapter = new HistoricalAdapter(R.layout.hisyoriccal_item,mlist,getApplicationContext());
        mRecyclerView.setAdapter(historicalAdapter);
//        这一句是开启 item 动画
        historicalAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);

    }

    /*
    *  连接设备
    * */
    private void initConnectDivce() {

        historicalAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                for (int j = 0; j < mlist.size(); j++) {
                    if (j == i) {
                       macaddress =   mlist.get(j).getMacAddress();
                    }
                }
                Toast.makeText(getApplicationContext(),"测试 "+i ,Toast.LENGTH_SHORT).show();
                //设置写入数据的 主服务
                BleConfig.setUuidServiceText("0000ffe0-0000-1000-8000-00805f9b34fb");
                //设置写入数据的 特征
                BleConfig.setUuidCharacteristicText("0000ffe1-0000-1000-8000-00805f9b34fb");

                MySearchDivce.ConnectDivce(getApplicationContext(),macaddress);
            }
        });
    }

    /**
     * 获取手机状态权限
     */
    private void initPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE_PHONE_STATUS);
        }
    }

    /**
     * 打开蓝牙
     */
    private void initBluetooth() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN}, REQUEST_CODE_BLUETOOTH);
        }
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            return;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN}, REQUEST_CODE_BLUETOOTH);
            return;
        }
        if (!mBluetoothAdapter.isEnabled()) {
            if (!mBluetoothAdapter.isEnabled()) {
                mBluetoothAdapter.enable();
            }
        }
    }

    /**
     * 用户同意授权后的操作
     *
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {

            case REQUEST_CODE_BLUETOOTH:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //用户同意授权
                    if (!mBluetoothAdapter.isEnabled()) {
                        if (!mBluetoothAdapter.isEnabled()) {
                            mBluetoothAdapter.enable();
                        }
                    }

                } else {
                    //用户拒绝授权
                    Toast.makeText(getApplicationContext(),"您已经拒绝蓝牙权限请在设置中打开蓝牙权限",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        // mClient = new BluetoothClient(DeviceActivity.this);
        super.onResume();
    }

}
