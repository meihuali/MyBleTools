/*
package com.example.yukunlin.physiotherapydevice.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yukunlin.physiotherapydevice.MyApplication;
import com.example.yukunlin.physiotherapydevice.R;
import com.example.yukunlin.physiotherapydevice.adapter.DeviceRecyclerViewAdapter;
import com.example.yukunlin.physiotherapydevice.fragment.AboutFragment;
import com.example.yukunlin.physiotherapydevice.fragment.AddDeviceFragment;
import com.example.yukunlin.physiotherapydevice.fragment.FeedbackFragment;
import com.example.yukunlin.physiotherapydevice.fragment.PersonalInfoFragment;
import com.example.yukunlin.physiotherapydevice.fragment.SettingFragment;
import com.example.yukunlin.physiotherapydevice.fragment.StayHealthyFragment;
import com.example.yukunlin.physiotherapydevice.fragment.UserGuideFragment;
import com.example.yukunlin.physiotherapydevice.module.Device;
import com.example.yukunlin.physiotherapydevice.utils.DeviceDao;
import com.example.yukunlin.physiotherapydevice.utils.DeviceDaoImpl;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.MysearchDivce.MySearchDivce;
import com.inuker.bluetooth.library.search.SearchRequest;
import com.inuker.bluetooth.library.search.SearchResult;
import com.inuker.bluetooth.library.search.response.SearchResponse;
import com.inuker.bluetooth.library.utils.L;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

*
 * 我的设备界面


public class DeviceActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @InjectView(R.id.edit)
    TextView edit;
    @InjectView(R.id.recyclerView)
    RecyclerView recyclerView;
    @InjectView(R.id.drawer_layout)
    DrawerLayout drawer_layout;
    @InjectView(R.id.menuImageView)
    ImageView menuImageView;


    private final static String TAG = "DeviceActivity";
    private String[] items = new String[]{"直接搜索", "扫描二维码"};
    private long exitTime = 0;
    private final static int SCANNIN_GREQUEST_CODE = 1;
    private final static int DELETE_DEVICE_CODE = 2;
    private List<Device> deviceList;
    private boolean editing = false;
    private BluetoothAdapter mBluetoothAdapter;
    private DeviceDao deviceDao;
    private BluetoothClient mClient;
    private DeviceRecyclerViewAdapter adapter;
    private final static int REQUEST_CODE = 100;
    private final static int REQUEST_CODE_BLUETOOTH = 101;
    private final static int REQUEST_CODE_PHONE_STATUS = 102;
    private String imei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        ButterKnife.inject(this);
        initPermission();
        initView();
        initBluetooth();
    }

*
     * 获取手机状态权限


    private void initPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE_PHONE_STATUS);
        }
    }

*
     * 打开蓝牙


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

*
     * 用户同意授权后的操作
     *
     * @param requestCode
     * @param permissions
     * @param grantResults


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //用户同意授权
                    Intent intent = new Intent();
                    intent.setClass(DeviceActivity.this, CaptureActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
                } else {
                    //用户拒绝授权
                }
                break;
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
                }
                break;
            case REQUEST_CODE_PHONE_STATUS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //用户同意授权
                    TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
                    imei = tm.getDeviceId() + "0";
                    if (imei.length() < 16) {
                        imei = imei + "00000000000000000000";
                        imei = imei.substring(0, 16);
                    }
                    MyApplication.getInstance().setImei(imei);
                } else {
                    //用户拒绝授权
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        mClient = new BluetoothClient(DeviceActivity.this);
        super.onResume();
    }

    private void initView() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer_layout, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer_layout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        deviceDao = new DeviceDaoImpl(this);
        try {
            deviceList = new ArrayList<>();
            for (int i = 0; i < deviceDao.getAllDevice().size(); i++) {
                deviceList.add(deviceDao.getAllDevice().get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        adapter = new DeviceRecyclerViewAdapter(DeviceActivity.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(DeviceActivity.this);
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setData(deviceList);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener(new DeviceRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(Device device, int position) {
                if (editing) {
                    Intent intent = new Intent(DeviceActivity.this, EditDeviceActivity.class);
                    intent.putExtra("id", device.getId());
                    intent.putExtra("macAddress", device.getMacAddress());
                    intent.putExtra("name", device.getName());
                    intent.putExtra("position", position);
                    startActivityForResult(intent, DELETE_DEVICE_CODE);
                } else {
                    if (check()) {
                        Intent intent = new Intent(DeviceActivity.this, DeviceControlActivity.class);
                        intent.putExtra("serviceUuid", device.getServiceUuid());
                        intent.putExtra("characUuid", device.getCharacteristicUuid());
                        intent.putExtra("mac", device.getMacAddress());
                        intent.putExtra("id", device.getId());  // uuid
                        startActivity(intent);
                    }

                }
            }
        });
    }

    private boolean check() {
        // 检查当前手机是否支持ble 蓝牙
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, R.string.ble_error, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

*
     * 再按一次退出程序


    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000 && !drawer_layout.isDrawerOpen(GravityCompat.START)) {
            Toast.makeText(this, R.string.exit_comfirm, Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START);
        } else if ((System.currentTimeMillis() - exitTime) < 2000 && !drawer_layout.isDrawerOpen(GravityCompat.START)) {
            finish();
            this.moveTaskToBack(true);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
        deviceDao.closeRealm();
    }

    @OnClick(R.id.menuImageView)
    void setMenuImageView() {
        drawer_layout.openDrawer(GravityCompat.START);
    }

    @OnClick(R.id.addDevice)
    public void addDevice() {
        new AlertDialog.Builder(this)
                .setTitle("添加方式")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        switch (which) {
                            case 0:
                             startSearchDevice("SmartFootbath");
                                //这里搜索设备
                             //   List<Device> shebeijieguo =   MySearchDivce.startSearchDevice(DeviceActivity.this);
                             //   L.e("搜索结果 "+shebeijieguo.size());
                                break;
                            case 1:
                                if (ActivityCompat.checkSelfPermission(DeviceActivity.this, Manifest.permission.CAMERA)
                                        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                                        DeviceActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                    ActivityCompat.requestPermissions(DeviceActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE);
                                } else {
                                    Intent intent = new Intent();
                                    intent.setClass(DeviceActivity.this, CaptureActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
                                }
                                break;
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    String result = bundle.getString("result");
                    startSearchDevice(result);
                }
                break;
            case DELETE_DEVICE_CODE:
                if (resultCode == RESULT_OK) {
                    int position = data.getIntExtra("position", -1);
                    deviceList.remove(position);
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }

*/
/**
     * 搜索设备
     *
     * @param result*//*



    private void startSearchDevice(final String result) {
        final ProgressDialog progressDialog = new ProgressDialog(DeviceActivity.this);
        progressDialog.setMessage("请稍后...");
        progressDialog.show();
        SearchRequest request = new SearchRequest.Builder()
                .searchBluetoothLeDevice(3000, 1)   // 扫BLE设备3次，每次3s
                .searchBluetoothClassicDevice(3000) // 再扫经典蓝牙5s
//                .searchBluetoothLeDevice(2000)      // 再扫BLE设备2s
                .build();
        mClient.search(request, new SearchResponse() {
            @Override
            public void onSearchStarted() {
                deviceList.clear();
            }

            @Override
            public void onDeviceFounded(SearchResult device) {

                //    if (device.getName().equals(result)) {
                Device newDevice = new Device();
                //获取设备名字
                newDevice.setName(device.getName());
                //获取设备 MAC 地址
                newDevice.setMacAddress(device.getAddress());
//                    newDevice.setCharacteristicUuid("0000ffe1-0000-1000-8000-00805f9b34fb");
//                    newDevice.setServiceUuid("0000ffe0-0000-1000-8000-00805f9b34fb");
                newDevice.setId(UUID.randomUUID().toString());
                deviceList.add(newDevice);

              AddDeviceFragment fragment = new AddDeviceFragment();
                fragment.setData(deviceList);
                fragment.setOnSaveListener(new AddDeviceFragment.OnSaveListener() {
                    @Override
                    public void onSave() {
                        deviceList.clear();
                        try {
                            for (int i = 0; i < deviceDao.getAllDevice().size(); i++) {
                                deviceList.add(deviceDao.getAllDevice().get(i));
                            }
                            adapter.notifyDataSetChanged();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                });
                fragment.show(getSupportFragmentManager(), "dialogFragment");
                progressDialog.dismiss();
                mClient.stopSearch();
                //     }
            }

            @Override
            public void onSearchStopped() {
                Log.d(TAG, "onSearchStopped: ");
                if (deviceList.size() == 0) {
                    Toast.makeText(DeviceActivity.this, R.string.can_not_find_device, Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onSearchCanceled() {
                progressDialog.dismiss();
            }
        });
    }

    @OnClick(R.id.edit)
    public void editDevice() {
        if (editing) {
            edit.setText(R.string.edit);
            editing = false;
        } else {
            edit.setText(R.string.done);
            editing = true;
        }
    }

*
     * 侧滑菜单点击时事件
     *
     * @param item
     * @return


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.about) {
            AboutFragment aboutFragment = new AboutFragment();
            aboutFragment.show(getSupportFragmentManager(), "dialogFragment");
        } else if (id == R.id.userGuide) {
            UserGuideFragment userGuideFragment = new UserGuideFragment();
            userGuideFragment.show(getSupportFragmentManager(), "dialogFragment");
        } else if (id == R.id.baojian) {
            StayHealthyFragment stayHealthyFragment = new StayHealthyFragment();
            stayHealthyFragment.show(getSupportFragmentManager(), "dialogFragment");
        } else if (id == R.id.setting) {
            SettingFragment settingFragment = new SettingFragment();
            settingFragment.show(getSupportFragmentManager(), "dialogFragment");
        } else if (id == R.id.feedback) {
            FeedbackFragment feedbackFragment = new FeedbackFragment();
            feedbackFragment.show(getSupportFragmentManager(), "dialogFragment");
        } else if (id == R.id.clearData) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, R.string.clear_success, Toast.LENGTH_SHORT).show();
        } else if (id == R.id.personalInfo) {
            PersonalInfoFragment personalInfoFragment = new PersonalInfoFragment();
            personalInfoFragment.show(getSupportFragmentManager(), "dialogFragment");
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
*/
