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
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BluetoothAdapter mBluetoothAdapter;
    private final static int REQUEST_CODE_PHONE_STATUS = 102;
    private final static int REQUEST_CODE_BLUETOOTH = 101;
    private static final int REQUEST_CODE_PERMISSION_SD = 100;
    private static final int REQUEST_CODE_SETTING = 300;
    private RecyclerView mRecyclerView;
    private HistoricalAdapter historicalAdapter;
    private List<Device> mlist;
    private String macaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPermission();
        // initBluetooth();
        initAdapter();
        initConnectDivce();

    }

    /*
    *
    *  搜索设备并且 设置adapter
    * */
    private void initAdapter() {
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
     * 获取手机蓝牙权限
     */
    private void initPermission() {
        // 申请单个权限。
        AndPermission.with(this)
                .requestCode(REQUEST_CODE_PERMISSION_SD)
                .permission(Manifest.permission.ACCESS_COARSE_LOCATION)
                .callback(permissionListener)
                // rationale作用是：用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框；
                // 这样避免用户勾选不再提示，导致以后无法申请权限。
                // 你也可以不设置。
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
                        AndPermission.rationaleDialog(MainActivity.this, rationale).
                                show();
                    }
                })
                .start();
    }

    /**
     * 6.0动态授权回调监听。
     */
    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
            switch (requestCode) {
                case REQUEST_CODE_PERMISSION_SD: {
                    Toast.makeText(MainActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
                    mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                    if (mBluetoothAdapter == null) {
                        Toast.makeText(getApplicationContext(),"本地蓝牙不可用",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (mBluetoothAdapter.isEnabled()) {
                        //表示蓝牙开启
                        //这句话是搜索蓝牙设备
                        //这里不对     如果用户没有授权或者还没有打开蓝牙  这个方法是扫不到的   要在打开蓝牙并授权之后去扫描
                        mlist = MySearchDivce.startSearchDevice(MainActivity.this);
                        L.e("搜到的设备 "+mlist.size());
                    } else {
                        mBluetoothAdapter.enable();  //打开蓝牙，
                        mlist = MySearchDivce.startSearchDevice(MainActivity.this);
                        L.e("搜到的设备 "+mlist.size());
                    }


                    break;
                }
            }
        }

        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            switch (requestCode) {
                case REQUEST_CODE_PERMISSION_SD: {
                    Toast.makeText(MainActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
                    break;
                }
            }

            // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
            if (AndPermission.hasAlwaysDeniedPermission(MainActivity.this, deniedPermissions)) {
                // 第一种：用默认的提示语。
                AndPermission.defaultSettingDialog(MainActivity.this, REQUEST_CODE_SETTING).show();


            }

        }
    };

    /**
     * 打开蓝牙
     */
/*    private void initBluetooth() {
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
    }*/

    /**
     * 用户同意授权后的操作
     *
     */
/*    @Override
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
    }*/

    @Override
    protected void onResume() {
        // mClient = new BluetoothClient(DeviceActivity.this);
        super.onResume();
    }

}
