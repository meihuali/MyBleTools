/*
package com.example.yukunlin.physiotherapydevice.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yukunlin.physiotherapydevice.MyApplication;
import com.example.yukunlin.physiotherapydevice.R;
import com.example.yukunlin.physiotherapydevice.module.BaseWrap;
import com.example.yukunlin.physiotherapydevice.module.User;
import com.example.yukunlin.physiotherapydevice.network.ApiService;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity {

    @InjectView(R.id.phoneNumberEditText)
    EditText phoneNumber;
    @InjectView(R.id.passwordEditText)
    EditText password;

    private double latitude = 0.0;
    private double longitude = 0.0;
    private ProgressDialog progressDialog;
    private static final int REQUEST_CODE = 100;
    private String imei;
    private static final int REQUEST_CODE_LOCATION = 101;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        progressDialog = new ProgressDialog(this);
        SMSSDK.initSDK(this, "1a19dd3aeb88e", "8db39783e2d51cfbb26f8665c369a07c");
        ButterKnife.inject(this);
        initPermission();
        getCurrentLocation();
        init();

    }

    private void initPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE);
        } else {
            TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
            imei = tm.getDeviceId() + "0";
            if (imei.length() < 16) {
                imei = imei + "00000000000000000000";
                imei = imei.substring(0, 16);
                MyApplication.getInstance().setImei(imei);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //用户同意授权
                    TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
                    imei = tm.getDeviceId() + "0";
                    if (imei.length() < 16) {
                        imei = imei + "00000000000000000000";
                        imei = imei.substring(0, 16);
                        MyApplication.getInstance().setImei(imei);
                    }
                } else {
                    //用户拒绝授权
                }
                break;
            case REQUEST_CODE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //用户同意授权
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                    }
                } else {
                    //用户拒绝授权
                }
                break;
        }
    }

    private void saveUserInfo(){
        SharedPreferences preferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("phoneNumber", phoneNumber.getText().toString().trim());
        editor.putString("location", latitude + ":" + longitude);
        editor.putBoolean("upload", false);
        editor.commit();

        SharedPreferences sharedPreferences = getSharedPreferences("register", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean("first", false);
        edit.commit();
    }
    private void init() {
        EventHandler eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                progressDialog.dismiss();
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功

//                        saveUserInfo();
                        uploadUserData();

                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }
                } else {
                    ((Throwable) data).printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegisterActivity.this,R.string.check_failed,Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        };
        SMSSDK.registerEventHandler(eventHandler); //注册短信回调
    }


    private void getCurrentLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(RegisterActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION);
            } else {
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                }
            }
        } else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            Log.d("TAG", "getCurrentLocation: ");
        }
    }


    @OnClick(R.id.getCodeTextView)
    void getCodeClick() {
        if (phoneNumber.getText().length() == 0) {
            Toast.makeText(this, R.string.input_number, Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(RegisterActivity.this, R.string.get_code_ing, Toast.LENGTH_SHORT).show();
        SMSSDK.getVerificationCode("86", phoneNumber.getText().toString().trim());
    }

    private void uploadUserData() {
        ApiService.Api api = ApiService.createApi();
        api.regPhone(MyApplication.getInstance().getImei()).enqueue(new Callback<BaseWrap>() {
            @Override
            public void onResponse(Call<BaseWrap> call, Response<BaseWrap> response) {
                if (response.body().getStatus().equals("1")) {
                    saveUserInfo();
                    startActivity(new Intent(RegisterActivity.this, DeviceActivity.class));
                    finish();
                } else if (response.body().getMsg().equals("duplicate app reg")) {
                    saveUserInfo();
                    startActivity(new Intent(RegisterActivity.this, DeviceActivity.class));
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, R.string.register_failed, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseWrap> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, R.string.network_error, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    @OnClick(R.id.done)
    void doneClick() {
        progressDialog.setMessage(getResources().getString(R.string.login));
        progressDialog.show();
        if (check()) {
            SMSSDK.submitVerificationCode("86", phoneNumber.getText().toString().trim(),
                    password.getText().toString().trim());
        }
    }

    private boolean check() {
        if (phoneNumber.getText().length() == 0) {
            Toast.makeText(this, R.string.input_number, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.getText().length() == 0) {
            Toast.makeText(this, R.string.input_code, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
        ButterKnife.reset(this);
    }
}
*/
