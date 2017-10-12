package com.example.yukunlin.physiotherapydevice.ble;

import android.annotation.SuppressLint;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.yukunlin.physiotherapydevice.utils.DateUtil;
import com.example.yukunlin.physiotherapydevice.utils.Utils;

import java.util.List;

public class BleService extends Service {

    private final static String TAG = "BleService";

    public BluetoothManager mBluetoothManager;
    public BluetoothAdapter mBluetoothAdapter;
    public BluetoothGatt mBluetoothGatt;
    private final IBinder mBinder = new LocalBinder();

    private String mbluetoothDeviceAddress;
    public int mConnectionState = STATE_DISCONNECTED;

    private static final int STATE_DISCONNECTED = 0;
    private static final int STATE_CONNECTING = 1;
    private static final int STATE_CONNECTED = 2;

    // 为了传送状态响应状态，要有几条ACTION
    public final static String ACTION_GATT_CONNECTED = "com.example.yukunlin.physiotherapydevice.ble.ACTION_GATT_CONNECTED";
    public final static String ACTION_GATT_DISCONNECTED = "com.example.yukunlin.physiotherapydevice.ble.ACTION_GATT_DISCONNECTED";
    public final static String ACTION_GATT_SERVICES_DISCOVERED = "com.example.yukunlin.physiotherapydevice.ble.ACTION_GATT_SERVICES_DISCOVERED";
    public final static String ACTION_DATA_AVAILABLE = "com.example.yukunlin.physiotherapydevice.ble.ACTION_DATA_AVAILABLE";
    public final static String ACTION_CHAR_READED = "com.example.yukunlin.physiotherapydevice.ble.ACTION_CHAR_READED";
    public final static String BATTERY_LEVEL_AVAILABLE = "com.example.yukunlin.physiotherapydevice.ble.BATTERY_LEVEL_AVAILABLE";
    public final static String EXTRA_DATA = "com.example.yukunlin.physiotherapydevice.ble.EXTRA_DATA";
    public final static String EXTRA_STRING_DATA = "com.example.yukunlin.physiotherapydevice.ble.EXTRA_STRING_DATA";
    public final static String EXTRA_DATA_LENGTH = "com.example.yukunlin.physiotherapydevice.ble.EXTRA_DATA_LENGTH";
    public final static String ACTION_GATT_RSSI = "com.example.yukunlin.physiotherapydevice.ble.ACTION_GATT_RSSI";
    public final static String EXTRA_DATA_RSSI = "com.example.yukunlin.physiotherapydevice.ble.ACTION_GATT_RSSI";

    public BleService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @SuppressLint("NewApi")
    public BluetoothGattCallback bluetoothGattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            String intentAction;
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                intentAction = ACTION_GATT_CONNECTED;
                mConnectionState = STATE_CONNECTED;
                broadcastUpdate(intentAction);
                gatt.discoverServices();
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                intentAction = ACTION_GATT_DISCONNECTED;
                mConnectionState = STATE_DISCONNECTED;
                broadcastUpdate(intentAction);
            }
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                broadcastUpdate(ACTION_GATT_SERVICES_DISCOVERED);
            } else {
                Log.d(TAG, "service is null");
            }
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                getChartacteristicValue(characteristic);
            } else {
                Log.d(TAG, " BluetoothGatt Read Failed!");
            }
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicWrite(gatt, characteristic, status);
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            broadcastUpdate(ACTION_DATA_AVAILABLE, characteristic);
        }

    };

    private void broadcastUpdate(String action) {
        Intent mIntent = new Intent(action);
        sendBroadcast(mIntent);
    }
//    @SuppressLint("NewApi")
//    public boolean init() {
//        IntentFilter bleServiceFilter = new IntentFilter();
//        bleServiceFilter.addAction(DeviceActivity.FIND_DEVICE_ALARM_ON);
//        bleServiceFilter.addAction(DeviceActivity.CANCEL_DEVICE_ALARM);
//        if (mBluetoothManager == null) {
//            mBluetoothManager = (BluetoothManager) this.getSystemService(Context.BLUETOOTH_SERVICE);
//            if (mBluetoothManager == null) {
//                Log.d(TAG, "Unable to initialize BluetoothManager.");
//                return false;
//            }
//            mBluetoothAdapter = mBluetoothManager.getAdapter();
//        }
//        if (mBluetoothAdapter == null) {
//            Log.d(TAG, "Unable to obtain a BluetoothAdapter.");
//            return false;
//        }
//        return true;
//    }
    @SuppressLint("NewApi")
    private void broadcastUpdate(String action, BluetoothGattCharacteristic characteristic) {
        final Intent intent = new Intent();
        intent.setAction(action);
        final byte[] data = characteristic.getValue();
        final String stringData = characteristic.getStringValue(0);
        if (data != null && data.length > 0) {
            final StringBuilder stringBuilder = new StringBuilder(data.length);
            for (byte byteChar : data) {
                stringBuilder.append(String.format("%X", byteChar));
            }
            if (stringData != null) {
                intent.putExtra(EXTRA_STRING_DATA, stringData);
            } else {
                Log.d(TAG, "characteristic.getStringValue is null");
            }
//            notify_result = stringBuilder.toString();
//            notify_string_result = stringData;
//            notify_result_length = data.length;
            intent.putExtra(EXTRA_DATA, stringBuilder.toString());
            intent.putExtra(EXTRA_DATA_LENGTH, data.length);
        }
        sendBroadcast(intent);
    }

    @SuppressLint("NewApi")
    private void getChartacteristicValue(BluetoothGattCharacteristic characteristic) {
        List<BluetoothGattDescriptor> des = characteristic.getDescriptors();
        Intent mIntent = new Intent(ACTION_CHAR_READED);
        if (des.size() != 0) {
            mIntent.putExtra("desriptor1", des.get(0).getUuid().toString());
            mIntent.putExtra("desriptor2", des.get(1).getUuid().toString());
        }
        mIntent.putExtra("StringValue", characteristic.getStringValue(0));
        String hexValue = Utils.bytesToHex(characteristic.getValue());
        mIntent.putExtra("HexValue", hexValue.toString());
        mIntent.putExtra("time", DateUtil.getCurrentDatatime());
        sendBroadcast(mIntent);
    }

    @SuppressLint("NewApi")
    public boolean connect(String bleAddress) {
        if (mBluetoothAdapter == null || bleAddress == null) {
            Log.d(TAG, "BluetoothAdapter not initialized or unspecified address.");
            return false;
        }
        if (mbluetoothDeviceAddress != null
                && bleAddress.equals(mbluetoothDeviceAddress)
                && mBluetoothGatt != null) {
            if (mBluetoothGatt.connect()) {
                mConnectionState = STATE_CONNECTING;
                return true;
            } else {
                return false;
            }
        }
        final BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(bleAddress);
        if (device == null) {
            Log.d(TAG, "Device not found.  Unable to connect.");
            return false;
        }
        mBluetoothGatt = device.connectGatt(this, false, bluetoothGattCallback);
        mbluetoothDeviceAddress = bleAddress;
        mConnectionState = STATE_CONNECTING;
        return true;
    }

    @SuppressLint("NewApi")
    public void disconnect() {
        if (mBluetoothAdapter == null || mBluetoothGatt == null) {
            Log.d(TAG, "BluetoothAdapter not initialized");
            return;
        }
        mBluetoothGatt.disconnect();
    }

    @SuppressLint("NewApi")
    public void close(BluetoothGatt gatt) {
        gatt.disconnect();
        gatt.close();
        if (mBluetoothAdapter != null) {
            mBluetoothAdapter.cancelDiscovery();
            mBluetoothAdapter = null;
        }
    }

    public class LocalBinder extends Binder {
        public BleService getService() {
            return BleService.this;
        }
    }
}
