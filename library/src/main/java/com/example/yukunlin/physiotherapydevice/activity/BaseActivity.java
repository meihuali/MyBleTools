package com.example.yukunlin.physiotherapydevice.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.yukunlin.physiotherapydevice.R;

public class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
}
