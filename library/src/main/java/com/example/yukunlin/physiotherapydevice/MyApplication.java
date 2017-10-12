package com.example.yukunlin.physiotherapydevice;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import com.example.yukunlin.physiotherapydevice.module.User;

/**
 * Created by yukunlin on 2016/12/20.
 */

public class MyApplication extends Application {
    private String imei;
    private String machineid;
    private User user;
    private static final int REQUEST_CODE = 100;

    public String getMachineid() {
        return machineid;
    }

    public void setMachineid(String machineid) {
        this.machineid = machineid;
    }

    private static MyApplication instance;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions((Activity) getApplicationContext(), new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE);
        }else {
            TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
            imei = tm.getDeviceId() + "0";
            if (imei.length() < 16) {
                imei = imei + "00000000000000000000";
                imei = imei.substring(0, 16);
            }
        }
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
