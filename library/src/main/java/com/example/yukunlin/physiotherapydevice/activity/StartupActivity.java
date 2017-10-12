/*
package com.example.yukunlin.physiotherapydevice.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.yukunlin.physiotherapydevice.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

*/
/**
 * 启动界面
 *//*

public class StartupActivity extends BaseActivity {
    @InjectView(R.id.imageView)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        ButterKnife.inject(this);
        imageView.postDelayed(new Runnable() {
            @Override
            public void run() {

                init();
            }
        }, 1500);
    }



    private void init() {
        SharedPreferences sharedPreferences = getSharedPreferences("register", Context.MODE_PRIVATE);
        boolean first = sharedPreferences.getBoolean("first", true);
        if (first) {
            Intent intent = new Intent(StartupActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(StartupActivity.this, DeviceActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
*/
