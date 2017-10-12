package com.inuker.bluetooth.library.mysearchdivce;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.yukunlin.physiotherapydevice.R;
import com.example.yukunlin.physiotherapydevice.module.Device;
import com.example.yukunlin.physiotherapydevice.utils.DateUtil;
import com.inuker.bluetooth.library.BluetoothClient;
import com.inuker.bluetooth.library.Constants;
import com.inuker.bluetooth.library.connect.listener.BleConnectStatusListener;
import com.inuker.bluetooth.library.connect.options.BleConnectOptions;
import com.inuker.bluetooth.library.connect.response.BleConnectResponse;
import com.inuker.bluetooth.library.connect.response.BleNotifyResponse;
import com.inuker.bluetooth.library.connect.response.BleWriteResponse;
import com.inuker.bluetooth.library.model.BleGattProfile;
import com.inuker.bluetooth.library.search.SearchRequest;
import com.inuker.bluetooth.library.search.SearchResult;
import com.inuker.bluetooth.library.search.response.SearchResponse;
import com.inuker.bluetooth.library.utils.L;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.inuker.bluetooth.library.Code.REQUEST_SUCCESS;
import static com.inuker.bluetooth.library.Constants.STATUS_CONNECTED;
import static com.inuker.bluetooth.library.Constants.STATUS_DISCONNECTED;

/**
 * 项目名：PhysiotherapyDevice
 * 包名：com.inuker.bluetooth.library.MysearchDivce
 * 文件名：MySearchDivce
 * 作者 ：梅华黎
 * 联系QQ： ：77299007
 * 创建时间： 2017/10/11 0011 15:13
 * 描述：这里是搜索设备
 */
public class MySearchDivce {

    private static BluetoothClient mClient;
    private static List<Device>  startSearchDevice = new ArrayList<>();
    private static BluetoothClient bluetoothClient;
    private static boolean connected;
    private static Activity activity2;
    private static int ble_max = 20;
    private static String sumWirte;
    private static String MacAddrsssa;


    /**
     * 搜索设备
     */
    public static List<Device>  startSearchDevice( final Activity activity) {

        activity2 = activity;
        mClient = new BluetoothClient(activity);
        final ProgressDialog progressDialog = new ProgressDialog( activity);
        progressDialog.setMessage("请稍后...");
        progressDialog.show();
        SearchRequest request = new SearchRequest.Builder()
                .searchBluetoothLeDevice(3000, 3)   // 扫BLE设备3次，每次3s
                .searchBluetoothClassicDevice(3000) // 再扫经典蓝牙5s
//                .searchBluetoothLeDevice(2000)      // 再扫BLE设备2s
                .build();
        mClient.search(request, new SearchResponse() {
            @Override
            public void onSearchStarted() {
                L.e("搜索 "+"搜索中···");
                startSearchDevice.clear();
            }
            @Override
            public void onDeviceFounded(SearchResult device) {
                L.e("搜索 "+"搜索成功···");
                Device newDevice = new Device();
                //获取设备名字
                newDevice.setName(device.getName());
                //获取设备 MAC 地址
                newDevice.setMacAddress(device.getAddress());
                newDevice.setId(UUID.randomUUID().toString());
                progressDialog.dismiss();
                mClient.stopSearch();
                startSearchDevice.add(newDevice);
            }

            @Override
            public void onSearchStopped() {
                L.e("搜索 "+"搜索停止···");
                if (startSearchDevice.size() == 0) {
                    Toast.makeText(activity, R.string.can_not_find_device, Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onSearchCanceled() {
                progressDialog.dismiss();
                L.e("搜索 "+"搜索不知道干嘛···");
            }
        });

        return startSearchDevice;
    }

    /*
    *  这里是连接设备
    * */
    public static void ConnectDivce(Context context,String macaddrsss) {
          MacAddrsssa = macaddrsss;
        bluetoothClient = new BluetoothClient(context);
        BleConnectOptions options = new BleConnectOptions.Builder()
                .setConnectRetry(3)   // 连接如果失败重试3次
                .setConnectTimeout(10000)   // 连接超时10s
                .setServiceDiscoverRetry(3)  // 发现服务如果失败重试3次
                .setServiceDiscoverTimeout(10000)  // 发现服务超时10s
                .build();
        //地址个参数是 MAC 地址
        bluetoothClient.connect(macaddrsss, options, new BleConnectResponse() {
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
    private static void connectStatus() {
        bluetoothClient.registerConnectStatusListener(MacAddrsssa, bleConnectStatusListener);
    }
    /*
    * 连接状态监听
    * */
    private static BleConnectStatusListener bleConnectStatusListener = new BleConnectStatusListener() {
        @Override
        public void onConnectStatusChanged(String mac, int status) {
            //这里是 连接蓝牙成功
            if (status == STATUS_CONNECTED) {
                connected = true;
                Toast.makeText(activity2, R.string.connect_success, Toast.LENGTH_SHORT).show();
                //连接成功后写入数据
                getStatus();


            } else if (status == STATUS_DISCONNECTED) {
                //这里是连接失败
                Toast.makeText(activity2, R.string.connect_error, Toast.LENGTH_SHORT).show();
                connected = false;

                //这里难道是尝试重新连接？
                bluetoothClient.connect(BleConfig.getMacAddress(), new BleConnectResponse() {
                    @Override
                    public void onResponse(int code, BleGattProfile data) {
                        if (code == Constants.REQUEST_SUCCESS) {
//                            deviceOrder.setLength(0);
                            connected = true;
                            receiveDataFromDevice();
                        }
                    }
                });
            }
        }
    };



    /*
    *  这个是写入数据
    * */
    private static void getStatus() {
        StringBuilder strapp = new StringBuilder();
        //获取系统时间
        //    String str2 = DateUtil.getCurrentTime() + "\r\n";
        //  String str = "&&KEY1=4&TIME=";
        //  {\"s\":\"%@\",\"u\":\"%@\",\"b\":\"1\"}   //这里是蓝牙那边给我的 让我以字符串的形式把这条发过去
        //  String str = new Gson().toJson(bluetooth); // {"b":1,"s":"111111111111111105","u":"111111111111111105"}
        //  String str = "{\"s\":\""+stringExtra+"\",\"u\":\""+mimakey+"\",\"b\":\"1\"}";
        String str = "{\"url\":\"/teapot/wifi\",\"ssid\":\"Sunyie\",\"pwd\":\"liujun5885562\"}";

        for (int i = 0; i < str.length(); i+= ble_max) {
            if ((i + ble_max) < str.length()) {
                sumWirte = str.substring(i, i + ble_max);
                write(sumWirte.getBytes());
            } else {
                sumWirte = str.substring(i,str.length());
                write(sumWirte.getBytes());
            }
            strapp.append(sumWirte);
            L.e("写入数据内容s "+sumWirte);

        }
    }

    /**
     * 写入数据
     *
     * @param
     */
    private static void write(final byte[] value) {
        if (!connected) {
            return;
        }
        bluetoothClient.write(MacAddrsssa, BleConfig.getUuidServiceText(), BleConfig.getUuidCharacteristic(), value,
                new BleWriteResponse() {
                    @Override
                    public void onResponse(int code) {
                        if (code == 0) {
                            Toast.makeText(activity2, "数据发送成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(activity2, "数据发送失败", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


    /*
*
     * 接收设备通知
*/
    private static void receiveDataFromDevice() {
        //通知服务 0000ffe0-0000-1000-8000-00805f9b34fb
        //通知特征 0000ffe1-0000-1000-8000-00805f9b34fb
        //第一个参数为 mac 地址 第2个为主服务 第3个为特征
        bluetoothClient.notify(MacAddrsssa, BleConfig.getUuidServiceText(),BleConfig.getUuidCharacteristic(),
                new BleNotifyResponse() {
                    @Override
                    public void onNotify(UUID service, UUID character, byte[] value) {
                        String data = new String(value);
                        Log.e("收到的数据：", data.toString());
                    }
                    @Override
                    public void onResponse(int code) {
                        if (code == Constants.REQUEST_SUCCESS) {
                            L.e("通知打开成功");
                        }
                    }
                });
    }





}
