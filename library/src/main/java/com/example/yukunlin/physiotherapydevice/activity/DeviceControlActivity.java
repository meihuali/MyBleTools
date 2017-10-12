/*
package com.example.yukunlin.physiotherapydevice.activity;

import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yukunlin.physiotherapydevice.MyApplication;
import com.example.yukunlin.physiotherapydevice.R;
import com.example.yukunlin.physiotherapydevice.fragment.HistoryFragment;
import com.example.yukunlin.physiotherapydevice.module.BaseWrap;
import com.example.yukunlin.physiotherapydevice.module.History;
import com.example.yukunlin.physiotherapydevice.network.ApiService;
import com.example.yukunlin.physiotherapydevice.utils.DateUtil;
import com.example.yukunlin.physiotherapydevice.utils.DeviceDao;
import com.example.yukunlin.physiotherapydevice.utils.DeviceDaoImpl;
import com.example.yukunlin.physiotherapydevice.utils.Utils;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.connect.listener.BleConnectStatusListener;
import com.inuker.bluetooth.library.connect.options.BleConnectOptions;
import com.inuker.bluetooth.library.connect.response.BleConnectResponse;
import com.inuker.bluetooth.library.connect.response.BleNotifyResponse;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.inuker.bluetooth.library.model.BleGattProfile;
import com.inuker.bluetooth.library.mysearchdivce.BleConfig;

import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.inuker.bluetooth.library.Constants.REQUEST_SUCCESS;
import static com.inuker.bluetooth.library.Constants.STATUS_CONNECTED;
import static com.inuker.bluetooth.library.Constants.STATUS_DISCONNECTED;

*
 * 设备控制界面


public class DeviceControlActivity extends BaseActivity
        implements View.OnClickListener {

    @InjectView(R.id.concn_btn)
    ImageView concn_btn;
    @InjectView(R.id.choose_btn)
    ImageView choose_btn;
    @InjectView(R.id.light_btn)
    ImageView light_btn;
    @InjectView(R.id.switch_btn)
    ImageView switch_btn;
    @InjectView(R.id.footbath)
    ImageView footbathImageView;
    @InjectView(R.id.man)
    ImageView manImageView;
    @InjectView(R.id.children)
    ImageView childrenImageView;
    @InjectView(R.id.woman)
    ImageView womanImageView;
    @InjectView(R.id.titleTextView)
    TextView titleTextView;
    @InjectView(R.id.contentTextView)
    TextView contentTextView;
    @InjectView(R.id.resultLayout)
    LinearLayout resultLayout;
    @InjectView(R.id.timeTitle)
    TextView timeTitle;
    @InjectView(R.id.time)
    TextView time;
    @InjectView(R.id.concentration)
    TextView concentrationTextView;
    @InjectView(R.id.chooseMode)
    TextView chooseMode;
    @InjectView(R.id.warningLayout)
    LinearLayout warningLayout;
    @InjectView(R.id.hintTextView)
    TextView hintTextView;
    @InjectView(R.id.hintEN)
    TextView hintENTextView;

    private String charUuid;
    private String serUuid;
    private String mac;
    private boolean connected;
    private BluetoothClient bluetoothClient;
    private final static String TAG = "DeviceControlActivity";
    private String concentration = "0.0%";
    private String mode = "1";  // 男人 女人 小孩
    private String IR = "0";    // 红远外
    private String run = "0";   // 工作状态
    private String remingTime = "30:00"; // 剩余时间
    private int gear = 1;
    private int count = 1800;
    private String menu = "0";   // 菜单选中情况
    private String deviceId;  // 设备id
    private String statusOrder;
    private Timer timer;
    private StringBuilder deviceOrder = new StringBuilder();
    private StringBuilder historyOrder = new StringBuilder();
    private String id;
    private DeviceDao deviceDao;
    private Timer countTimer;
    private ApiService.Api api;
    private BluetoothSocket mSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_control);
        charUuid = getIntent().getStringExtra("characUuid");
        serUuid = getIntent().getStringExtra("serviceUuid");
        mac = getIntent().getStringExtra("mac");
        id = getIntent().getStringExtra("id");
        Log.e("getData", mac + "....");
        ButterKnife.inject(this);
        api = ApiService.createApi();
        initView();
        initBluetooth();

    }
*/
/*    * 写入数据
    * *//*


    private void initBluetooth() {
        bluetoothClient = new BluetoothClient(this);
        BleConnectOptions options = new BleConnectOptions.Builder()
                .setConnectRetry(3)   // 连接如果失败重试3次
                .setConnectTimeout(10000)   // 连接超时10s
                .setServiceDiscoverRetry(3)  // 发现服务如果失败重试3次
                .setServiceDiscoverTimeout(10000)  // 发现服务超时10s
                .build();

        bluetoothClient.connect(mac, options, new BleConnectResponse() {
            @Override
            public void onResponse(int code, BleGattProfile data) {
                if (code == REQUEST_SUCCESS) {
                    connected = true;
                }
            }
        });
        connectStatus();
        receiveDataFromDevice();
    }

    private void getStatus() {
        String str = "&&KEY1=4&TIME=";
        String str2 = DateUtil.getCurrentTime() + "\r\n";
        Log.e("date", str2);

        write(str.getBytes());
        write(str2.getBytes());
        Log.e(TAG, "getStatus: " +"发送数据:  "+ str + str2);
    }

*/
/**
     * 写入数据
     *
     * @param value*//*



    private void write(final byte[] value) {
        if (!connected) {
            return;
        }
        bluetoothClient.write(mac, UUID.fromString("0000fee0-0000-1000-8000-00805f9b34fb"), UUID.fromString("0000ffe1-0000-1000-8000-00805f9b34fb"), value,
                new BleWriteResponse() {
                    @Override
                    public void onResponse(int code) {
                        Log.e(TAG, "写入数据: " + "数据写入成功");
                    }
                });
    }

    private void disconnect() {
        bluetoothClient.disconnect(BleConfig.getMacAddress());
    }

    private void connectStatus() {
        bluetoothClient.registerConnectStatusListener(BleConfig.getMacAddress(), bleConnectStatusListener);
    }

*/
/**
     * 连接状态监听*//*



    private BleConnectStatusListener bleConnectStatusListener = new BleConnectStatusListener() {
        @Override
        public void onConnectStatusChanged(String mac, int status) {
            if (status == STATUS_CONNECTED) {
                connected = true;
                choose_btn.setEnabled(true);
                concn_btn.setEnabled(true);
                light_btn.setEnabled(true);
                switch_btn.setEnabled(true);
                Toast.makeText(DeviceControlActivity.this, R.string.connect_success, Toast.LENGTH_SHORT).show();
                getStatus();
            } else if (status == STATUS_DISCONNECTED) {
                //连接失败
                Toast.makeText(DeviceControlActivity.this, R.string.connect_error, Toast.LENGTH_SHORT).show();
                connected = false;
                choose_btn.setEnabled(false);
                concn_btn.setEnabled(false);
                light_btn.setEnabled(false);
                switch_btn.setEnabled(false);
                deviceOrder.setLength(0);
                historyOrder.setLength(0);
                //这里难道是尝试重新连接？
                bluetoothClient.connect(BleConfig.getMacAddress(), new BleConnectResponse() {
                    @Override
                    public void onResponse(int code, BleGattProfile data) {
                        if (code == REQUEST_SUCCESS) {
//                            deviceOrder.setLength(0);
                            connected = true;
                            receiveDataFromDevice();
                        }
                    }
                });
            }
        }
    };

*/
/*
*
     * 接收设备通知
*//*



    private void receiveDataFromDevice() {
        //通知服务 0000ffe0-0000-1000-8000-00805f9b34fb
        //通知特征 0000ffe1-0000-1000-8000-00805f9b34fb
        bluetoothClient.notify(mac, UUID.fromString("0000ffe0-0000-1000-8000-00805f9b34fb"), UUID.fromString("0000ffe1-0000-1000-8000-00805f9b34fb"),
                new BleNotifyResponse() {
                    @Override
                    public void onNotify(UUID service, UUID character, byte[] value) {
                        String data = new String(value);
                        Log.e("收到的数据：", data.toString());


                        if (data.contains("&&KEY1=1") || deviceOrder.toString().contains("&&KEY1=1")) {
                            deviceOrder.append(data);
                            Log.e("bb", deviceOrder.toString());
                        } else {
                            historyOrder.append(data);
                        }
                        if (deviceOrder.length() < 89 && deviceOrder.length() > 84) {
                            setDeviceStatus(deviceOrder.toString());
                            Log.e(TAG, "onNotify: " + deviceOrder.length() + "  " + deviceOrder.toString());
                            deviceOrder.setLength(0);
                        } else if (historyOrder.length() > 116 && historyOrder.length() < 121 && historyOrder.toString().contains("&&KEY1=3")) {
                            Log.e(TAG, "onNotify: " + historyOrder.length() + "  " + historyOrder.toString());
                            saveRecord(historyOrder.toString());
                            historyOrder.setLength(0);
                        }
                    }
                    @Override
                    public void onResponse(int code) {
                        if (code == REQUEST_SUCCESS) {
                            Log.e(TAG, "onResponse: " + "通知打开成功");
                        }
                    }
                });
    }

*/
/*
*
     * 解析历史记录
     * @param record
*//*



    private void saveRecord(String record) {
        StringBuilder stringBuilder = new StringBuilder(record);
        String substring = stringBuilder.substring(2, record.length());
        String[] historyStr = substring.split("&");
        String startTime = historyStr[1].substring(3);
        String endTime = historyStr[2].substring(3);
        String workTime = historyStr[3].substring(3);
        String irTime = historyStr[4].substring(4);
        String cc = historyStr[5].substring(3);
        String mode = historyStr[6].substring(5);
        String gear = historyStr[7].substring(5);
        String power = historyStr[8].substring(3);
        String id = historyStr[9].substring(3);
        History history = new History(startTime, workTime, endTime, irTime,
                cc, mode, power, id.substring(0, 20), gear, mac, UUID.randomUUID().toString());
        if (deviceDao == null) {
            deviceDao = new DeviceDaoImpl(this);
        }
        try {
            deviceDao.insertHistory(history);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        uploadRecord(history);
    }

*
     * 上传历史记录
     *
     * @param history


    private void uploadRecord(History history) {
        api.uploadHistory(history.getMachineid().substring(0, 20), history.getStarttime(), history.getStoptime(), history.getWorktime(),
                history.getIrworktime(), history.getCc(), history.getMode(), history.getPower(), history.getGear())
                .enqueue(new Callback<BaseWrap>() {
                    @Override
                    public void onResponse(Call<BaseWrap> call, Response<BaseWrap> response) {
                        if (response.body().getStatus().equals("1")) {
                            Log.e(TAG, "onResponse: " + "历史记录上传成功");
                        } else {
                            Toast.makeText(DeviceControlActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseWrap> call, Throwable t) {
                        Toast.makeText(DeviceControlActivity.this, "访问网络出错", Toast.LENGTH_SHORT).show();
                    }
                });
    }

*
     * 设置按钮状态
     *
     * @param data


    private void setDeviceStatus(String data) {
        deviceDao = new DeviceDaoImpl(this);
        StringBuilder stringBuilder = new StringBuilder(data);
        String substring = stringBuilder.substring(2, data.length());
        String[] order = substring.split("&");
        concentration = order[1].substring(3);
        mode = order[2].substring(5);
        gear = Integer.parseInt(order[3].substring(5));
        IR = order[4].substring(3);
        run = order[5].substring(4);
        remingTime = order[6].substring(5);
        menu = order[7].substring(5);
        deviceId = order[8].substring(3);
        MyApplication.getInstance().setMachineid(deviceId.substring(0, 20));
        Log.e(TAG , "Machineid..."+MyApplication.getInstance().getMachineid());

        if (!remingTime.equals("00:00")) {
            count = Utils.time2Sec(remingTime) - 1;
        } else {
            count = 1800;
        }
        try {
            String machineId = deviceDao.getMachineId(id);
            if (machineId == null || machineId.equals("")) {
                register(deviceId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        boolean upload = sharedPreferences.getBoolean("upload", false);
        if (!upload) {
            String phoneNumber = sharedPreferences.getString("phoneNumber", null);
            String location = sharedPreferences.getString("location", null);
            uploadUserInfo(phoneNumber, location);
        }
        //App获取终端状态
        statusOrder = "&&KEY1=5&&ID=" + deviceId;
        try {
            deviceDao.updateMachineId(id, deviceId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 将状态设置到相应的view上
        // 浓度
        titleTextView.setText(R.string.concentration);
        contentTextView.setText(concentration);
        contentTextView.setTextSize(35f);
        concentrationTextView.setText(concentration);
        concn_btn.setEnabled(false);
        if (mode.equals("1")) {
            manImageView.setSelected(true);
            manImageView.setImageResource(R.drawable.man_selected);
            womanImageView.setImageResource(R.drawable.woman_un_select);
            childrenImageView.setImageResource(R.drawable.children_un_select);
            chooseMode.setText(R.string.man);

        } else if (mode.equals("2")) {
            womanImageView.setSelected(true);
            manImageView.setImageResource(R.drawable.man_un_select);
            womanImageView.setImageResource(R.drawable.woman_selected);
            childrenImageView.setImageResource(R.drawable.children_un_select);
            chooseMode.setText(R.string.woman);
        } else {
            childrenImageView.setSelected(true);
            manImageView.setImageResource(R.drawable.man_un_select);
            womanImageView.setImageResource(R.drawable.woman_un_select);
            childrenImageView.setImageResource(R.drawable.children_selected);
            chooseMode.setText(R.string.children);
        }
        // 红远外
        if (IR.equals("0")) {
            light_btn.setSelected(false);
            light_btn.setImageResource(R.drawable.light_un_select);
        } else {
            light_btn.setSelected(true);
            light_btn.setImageResource(R.drawable.light_selected);
        }

        // 菜单选中情况
        if (menu.equals("1")) {
            warningLayout.setVisibility(View.GONE);
            concn_btn.setSelected(true);
            choose_btn.setSelected(false);
            concn_btn.setImageResource(R.drawable.concn_selected);
            choose_btn.setImageResource(R.drawable.choose_un_select);

            switch_btn.setSelected(false);
            footbathImageView.setVisibility(View.GONE);
            manImageView.setVisibility(View.GONE);
            womanImageView.setVisibility(View.GONE);
            childrenImageView.setVisibility(View.GONE);
            titleTextView.setVisibility(View.VISIBLE);
            contentTextView.setVisibility(View.VISIBLE);
            resultLayout.setVisibility(View.GONE);

        } else if (menu.equals("2")) {
            warningLayout.setVisibility(View.GONE);
            if (timer != null) {
                timer.cancel();
            }
            switch_btn.setSelected(false);
            choose_btn.setSelected(true);
            concn_btn.setSelected(false);
            concn_btn.setImageResource(R.drawable.concn_un_select);
            choose_btn.setImageResource(R.drawable.choose_selected);

            concn_btn.setEnabled(true);
            footbathImageView.setVisibility(View.GONE);
            manImageView.setVisibility(View.VISIBLE);
            womanImageView.setVisibility(View.VISIBLE);
            childrenImageView.setVisibility(View.VISIBLE);
            titleTextView.setVisibility(View.GONE);
            contentTextView.setVisibility(View.GONE);
            resultLayout.setVisibility(View.GONE);
        } else if (menu.equals("3")) {
            warningLayout.setVisibility(View.GONE);
            footbathImageView.setVisibility(View.GONE);

            concn_btn.setSelected(false);
            concn_btn.setEnabled(true);
            choose_btn.setEnabled(true);
            switch_btn.setSelected(false);
            light_btn.setSelected(false);

            manImageView.setVisibility(View.GONE);
            womanImageView.setVisibility(View.GONE);
            childrenImageView.setVisibility(View.GONE);

            light_btn.setImageResource(R.drawable.light_un_select);
            switch_btn.setImageResource(R.drawable.switch_un_select);
            concn_btn.setImageResource(R.drawable.concn_un_select);
            choose_btn.setImageResource(R.drawable.choose_un_select);
            timeTitle.setText(R.string.work_stop);
            time.setTextSize(20f);
            if (countTimer != null) {
                countTimer.cancel();
            }
            time.setText(R.string.work_stop_en);
            if (timer != null) {
                timer.cancel();
            }
            count = 1800;
            resultLayout.setVisibility(View.VISIBLE);
        } else if (menu.equals("4")) {
            warningLayout.setVisibility(View.GONE);
            footbathImageView.setVisibility(View.GONE);
            titleTextView.setVisibility(View.GONE);
            contentTextView.setVisibility(View.GONE);
            switch_btn.setSelected(true);
            switch_btn.setImageResource(R.drawable.switch_selected);
            timeTitle.setText(R.string.remaining);
            time.setTextSize(30f);
            time.setText(remingTime);
            concn_btn.setSelected(false);
            choose_btn.setSelected(false);
            manImageView.setVisibility(View.GONE);
            womanImageView.setVisibility(View.GONE);
            childrenImageView.setVisibility(View.GONE);
            concn_btn.setImageResource(R.drawable.concn_un_select);
            choose_btn.setImageResource(R.drawable.mode1);
            if (gear == 1) {
                choose_btn.setImageResource(R.drawable.mode1);
            } else if (gear == 2) {
                choose_btn.setImageResource(R.drawable.mode2);
            } else if (gear == 3) {
                choose_btn.setImageResource(R.drawable.mode3);
            } else if (gear == 4) {
                choose_btn.setImageResource(R.drawable.mode4);
            } else if (gear == 5) {
                choose_btn.setImageResource(R.drawable.mode5);
            }
            concn_btn.setEnabled(false);
            choose_btn.setEnabled(true);

            initCount();
            resultLayout.setVisibility(View.VISIBLE);
        }
        if (menu.equals("5")) {
            if (countTimer != null) {
                countTimer.cancel();
            }
            choose_btn.setSelected(false);
            concn_btn.setSelected(false);
            light_btn.setSelected(false);
            switch_btn.setSelected(false);

            choose_btn.setImageResource(R.drawable.choose_un_select);
            concn_btn.setImageResource(R.drawable.concn_un_select);
            light_btn.setImageResource(R.drawable.light_un_select);
            switch_btn.setImageResource(R.drawable.switch_un_select);

            resultLayout.setVisibility(View.GONE);
            warningLayout.setVisibility(View.VISIBLE);
            manImageView.setVisibility(View.GONE);
            womanImageView.setVisibility(View.GONE);
            childrenImageView.setVisibility(View.GONE);
            footbathImageView.setVisibility(View.GONE);

            hintTextView.setText(R.string.warning_water);
            hintENTextView.setText(R.string.warning_water_en);
            concn_btn.setEnabled(true);
        } else if (menu.equals("6")) {
            if (countTimer != null) {
                countTimer.cancel();
            }
            choose_btn.setSelected(false);
            concn_btn.setSelected(false);
            light_btn.setSelected(false);
            switch_btn.setSelected(false);

            choose_btn.setImageResource(R.drawable.choose_un_select);
            concn_btn.setImageResource(R.drawable.concn_un_select);
            light_btn.setImageResource(R.drawable.light_un_select);
            switch_btn.setImageResource(R.drawable.switch_un_select);

            resultLayout.setVisibility(View.GONE);
            warningLayout.setVisibility(View.VISIBLE);
            manImageView.setVisibility(View.GONE);
            womanImageView.setVisibility(View.GONE);
            childrenImageView.setVisibility(View.GONE);
            footbathImageView.setVisibility(View.GONE);

            hintTextView.setText(R.string.warning);
            hintENTextView.setText(R.string.warning_en);
            concn_btn.setEnabled(true);
        } else if (menu.equals("0")) {
            choose_btn.setImageResource(R.drawable.choose_un_select);
            switch_btn.setImageResource(R.drawable.switch_un_select);
            choose_btn.setSelected(false);
            switch_btn.setSelected(false);
            resultLayout.setVisibility(View.GONE);
            warningLayout.setVisibility(View.GONE);
            footbathImageView.setVisibility(View.VISIBLE);
            concn_btn.setEnabled(true);
            concn_btn.setImageResource(R.drawable.concn_un_select);
            concn_btn.setSelected(false);
            titleTextView.setVisibility(View.GONE);
            contentTextView.setVisibility(View.GONE);

        }
        if (concn_btn.isSelected()) {
            loopOrder(statusOrder);
        } else if (switch_btn.isSelected()) {
            loopOrder(statusOrder);
        } else {
            if (timer != null) {
                timer.cancel();
            }
        }

    }

*
     * 上传用户信息
     *
     * @param phoneNumber
     * @param location


    private void uploadUserInfo(String phoneNumber, String location) {
        if (phoneNumber == null || location == null)
            return;
        api.uploadUserInfo(deviceId.substring(0, 20), phoneNumber,
                MyApplication.getInstance().getImei(), location).enqueue(new Callback<BaseWrap>() {
            @Override
            public void onResponse(Call<BaseWrap> call, Response<BaseWrap> response) {
                if (response.body().getStatus().equals("1")) {
                    SharedPreferences preferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = preferences.edit();
                    edit.putBoolean("upload", true);
                    edit.commit();
                }
            }

            @Override
            public void onFailure(Call<BaseWrap> call, Throwable t) {
                Toast.makeText(DeviceControlActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
            }
        });
    }

*
     * 注册设备
     *
     * @param machineId


    private void register(final String machineId) {
        api.regDevice(machineId.substring(0, 20)).enqueue(new Callback<BaseWrap>() {
            @Override
            public void onResponse(Call<BaseWrap> call, Response<BaseWrap> response) {
                if (response.body().getStatus().equals("1") || response.body().getStatus().equals("2")) {
                    api.bind(MyApplication.getInstance().getImei(), machineId.substring(0, 20)).enqueue(new Callback<BaseWrap>() {
                        @Override
                        public void onResponse(Call<BaseWrap> call, Response<BaseWrap> response) {
                            if (response.body().getStatus().equals("1")) {
                                Log.e(TAG, "onResponse: " + "register success");
                            } else {
                                Log.e(TAG, "onResponse: " + "register failed");
                            }
                        }

                        @Override
                        public void onFailure(Call<BaseWrap> call, Throwable t) {
                            Toast.makeText(DeviceControlActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<BaseWrap> call, Throwable t) {
                Toast.makeText(DeviceControlActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initCount() {
        if (countTimer != null) {
            countTimer.cancel();
        }
        countTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {      // UI thread
                    @Override
                    public void run() {
                        count--;
                        if (time != null) {
                            time.setText(Utils.secToTime(count));
                        }
                        if (count < 0) {
                            countTimer.cancel();
                        }
                    }
                });
            }
        };
        countTimer.schedule(task, 1000, 1000);
    }

    private void initView() {
        concn_btn.setOnClickListener(this);
        choose_btn.setOnClickListener(this);
        light_btn.setOnClickListener(this);
        switch_btn.setOnClickListener(this);
        manImageView.setOnClickListener(this);
        womanImageView.setOnClickListener(this);
        childrenImageView.setOnClickListener(this);
        choose_btn.setEnabled(false);
        concn_btn.setEnabled(false);
        light_btn.setEnabled(false);
        switch_btn.setEnabled(false);
    }

    @OnClick(R.id.backImageView)
    public void backClick() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
        disconnect();
        bluetoothClient.unregisterConnectStatusListener(mac, bleConnectStatusListener);
        if (timer != null) {
            timer.cancel();
        }
        if (deviceDao != null) {
            deviceDao.closeRealm();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.concn_btn:
                warningLayout.setVisibility(View.GONE);
                footbathImageView.setVisibility(View.INVISIBLE);
                setBtnStatus(R.id.concn_btn);
                titleTextView.setText(R.string.concentration);
                contentTextView.setText(concentration);
                contentTextView.setTextSize(35f);
                loopOrder(statusOrder);
                concn_btn.setEnabled(false);
                break;
            case R.id.choose_btn:
                warningLayout.setVisibility(View.GONE);
                footbathImageView.setVisibility(View.INVISIBLE);
                if (timer != null) {
                    timer.cancel();
                }
                if (switch_btn.isSelected() && run.equals("1")) {
                    if (gear == 5) {
                        gear = 0;
                    }
                    if (gear == 0) {
                        choose_btn.setImageResource(R.drawable.mode1);
                    } else if (gear == 1) {
                        choose_btn.setImageResource(R.drawable.mode2);
                    } else if (gear == 2) {
                        choose_btn.setImageResource(R.drawable.mode3);
                    } else if (gear == 3) {
                        choose_btn.setImageResource(R.drawable.mode4);
                    } else if (gear == 4) {
                        choose_btn.setImageResource(R.drawable.mode5);
                    }
                    gear++;
                } else if (run.equals("0")) {
                    setBtnStatus(R.id.choose_btn);
                }
                break;
            case R.id.light_btn:
                warningLayout.setVisibility(View.GONE);
                setBtnStatus(R.id.light_btn);
                break;
            case R.id.switch_btn:
                warningLayout.setVisibility(View.GONE);
                footbathImageView.setVisibility(View.INVISIBLE);
                setBtnStatus(R.id.switch_btn);
                break;
            case R.id.man:
                mode = "1";
                manImageView.setImageResource(R.drawable.man_selected);
                womanImageView.setImageResource(R.drawable.woman_un_select);
                childrenImageView.setImageResource(R.drawable.children_un_select);
                chooseMode.setText(R.string.man);
                break;
            case R.id.woman:
                mode = "2";
                manImageView.setImageResource(R.drawable.man_un_select);
                womanImageView.setImageResource(R.drawable.woman_selected);
                childrenImageView.setImageResource(R.drawable.children_un_select);
                chooseMode.setText(R.string.children);
                break;
            case R.id.children:
                mode = "3";
                manImageView.setImageResource(R.drawable.man_un_select);
                womanImageView.setImageResource(R.drawable.woman_un_select);
                childrenImageView.setImageResource(R.drawable.children_selected);
                chooseMode.setText(R.string.woman);
                break;
        }
        sendOrder();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setBtnStatus(int viewId) {
        if (viewId == R.id.concn_btn) {
            footbathImageView.setVisibility(View.GONE);
            manImageView.setVisibility(View.GONE);
            womanImageView.setVisibility(View.GONE);
            childrenImageView.setVisibility(View.GONE);
            titleTextView.setVisibility(View.VISIBLE);
            contentTextView.setVisibility(View.VISIBLE);
            concn_btn.setImageResource(R.drawable.concn_selected);
            choose_btn.setImageResource(R.drawable.choose_un_select);
            resultLayout.setVisibility(View.GONE);
            menu = "1";
        } else if (viewId == R.id.choose_btn) {
            menu = "2";
            concn_btn.setEnabled(true);
            footbathImageView.setVisibility(View.GONE);
            manImageView.setVisibility(View.VISIBLE);
            womanImageView.setVisibility(View.VISIBLE);
            childrenImageView.setVisibility(View.VISIBLE);
            titleTextView.setVisibility(View.GONE);
            contentTextView.setVisibility(View.GONE);
            concn_btn.setImageResource(R.drawable.concn_un_select);
            choose_btn.setImageResource(R.drawable.choose_selected);
            resultLayout.setVisibility(View.GONE);
        } else if (viewId == R.id.light_btn) {
            if (light_btn.isSelected()) {
                light_btn.setSelected(false);
                light_btn.setImageResource(R.drawable.light_un_select);
                IR = "0";
            } else {
                light_btn.setSelected(true);
                light_btn.setImageResource(R.drawable.light_selected);
                IR = "1";
            }
            sendOrder();
        } else if (viewId == R.id.switch_btn) {
            footbathImageView.setVisibility(View.GONE);
            manImageView.setVisibility(View.GONE);
            womanImageView.setVisibility(View.GONE);
            childrenImageView.setVisibility(View.GONE);
            titleTextView.setVisibility(View.GONE);
            contentTextView.setVisibility(View.GONE);
            if (switch_btn.isSelected()) {
                concn_btn.setEnabled(true);
                choose_btn.setEnabled(true);
                switch_btn.setSelected(false);
                run = "0";
                IR = "0";
                light_btn.setSelected(false);
                light_btn.setImageResource(R.drawable.light_un_select);
                switch_btn.setImageResource(R.drawable.switch_un_select);
                concn_btn.setImageResource(R.drawable.concn_un_select);
                choose_btn.setImageResource(R.drawable.choose_un_select);
                if (countTimer != null) {
                    countTimer.cancel();
                }
                time.setTextSize(20f);
                timeTitle.setText(R.string.work_stop);
                if (countTimer != null) {
                    countTimer.cancel();
                }
                time.setText(R.string.work_stop_en);
                remingTime = "30:00";
                count = 1800;
                menu = "3";
                if (timer != null) {
                    timer.cancel();
                }
            } else {
                switch_btn.setSelected(true);
                run = "1";
                switch_btn.setImageResource(R.drawable.switch_selected);
                time.setText(remingTime);
                time.setTextSize(30f);
                timeTitle.setText(R.string.remaining);
                concn_btn.setSelected(false);
                choose_btn.setSelected(false);
                concn_btn.setImageResource(R.drawable.concn_un_select);
                if (gear == 1) {
                    choose_btn.setImageResource(R.drawable.mode1);
                } else if (gear == 2) {
                    choose_btn.setImageResource(R.drawable.mode2);
                } else if (gear == 3) {
                    choose_btn.setImageResource(R.drawable.mode3);
                } else if (gear == 4) {
                    choose_btn.setImageResource(R.drawable.mode4);
                } else if (gear == 5) {
                    choose_btn.setImageResource(R.drawable.mode5);
                }
                concn_btn.setEnabled(false);
                choose_btn.setEnabled(true);
                menu = "4";
                initCount();
                loopOrder(statusOrder);
            }
            resultLayout.setVisibility(View.VISIBLE);
        }
    }

*
     * 发送指令


    private void sendOrder() {
        String order = ("&&KEY1=2" + "&MODE=" + mode + "&GEAR=" + gear
                + "&IR=" + IR + "&RUN=" + run + "&MENU=" + menu + "\r\n");
        String order1 = order.substring(0, 20);
        String order2 = order.substring(20, 40);
        String order3 = order.substring(40);
        write(order1.getBytes());
        write(order2.getBytes());
        write(order3.getBytes());
        Log.e(TAG, "sendOrder: " + order1 + order2 + order3);


    }

*
     * 循环发送指令
     *
     * @param order


    private void loopOrder(final String order) {
        if (order == null || order.length() < 21) {
            return;
        }
        String order1 = null;
        String order2 = null;
        order1 = order.substring(0, 19);
        order2 = order.substring(19);
        final String finalOrder1 = order1;
        final String finalOrder2 = order2;
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                write(finalOrder1.getBytes());
                write(finalOrder2.getBytes());
                Log.d(TAG, "run: " + finalOrder1 + finalOrder2);
            }
        };
        timer.schedule(task, 10 * 1000, 10 * 1000);

    }

    @OnClick(R.id.history)
    public void showHistory() {
        HistoryFragment historyFragment = new HistoryFragment();
        historyFragment.setMacAddress(mac);
        historyFragment.show(getSupportFragmentManager(), "historyDialogFragment");
    }
}

*/
