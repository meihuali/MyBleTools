package com.inuker.bluetooth.library.mysearchdivce;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.text.TextUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.UUID;

import static com.inuker.bluetooth.library.mysearchdivce.MySearchDivce.REQUEST_ENABLE_BT;

/**
 * 项目名：MyBleTools
 * 包名：com.inuker.bluetooth.library.mysearchdivce
 * 文件名：BleConfig
 * 创建者 ：梅华黎
 * 创建时间： 2017/10/12 0012 10:11
 * 描述：配置工具类
 */
public class BleConfig {


    private static String MAC_ADDRESS = null;
    private static UUID UUID_SERVICE = null;
    private static UUID UUID_CHARACTERISTIC;

    //设置 MAC地址
    public static void setMacAddress(String MacAddress) {
        if (TextUtils.isEmpty(MacAddress)) {
            return;
        }
        MAC_ADDRESS = MacAddress;
    }

    //获取MAC 地址
    public static String getMacAddress() {
        return MAC_ADDRESS;
    }

    //设置蓝牙写入数据的 主服务
    public static void setUuidServiceText(String uuidServiceText) {
        if (TextUtils.isEmpty(uuidServiceText)) {
            return;
        }
        UUID_SERVICE = UUID.fromString(uuidServiceText); // UUID_SERVICE_TEXT
    }

    //获取蓝牙写入数据的 主服务
    public static UUID getUuidServiceText(){
        return UUID_SERVICE;
    }
    /*
    *  设置蓝牙数据的 特征
    * */
    public static void setUuidCharacteristicText(String uuidCharacteristicText) {
        if (TextUtils.isEmpty(uuidCharacteristicText)) {
            return;
        }
        UUID_CHARACTERISTIC = UUID.fromString(uuidCharacteristicText);
    }
    /*
    * 获取蓝牙数据的 特征
    * */
    public static UUID getUuidCharacteristic(){
        return UUID_CHARACTERISTIC;
    }





}
