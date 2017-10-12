/*
package com.example.yukunlin.physiotherapydevice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yukunlin.physiotherapydevice.R;
import com.example.yukunlin.physiotherapydevice.utils.DeviceDao;
import com.example.yukunlin.physiotherapydevice.utils.DeviceDaoImpl;

import java.sql.SQLException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class EditDeviceActivity extends BaseActivity {
    private final static String TAG = "EditDeviceActivity";
    private String id;
    private int position;
    private DeviceDao deviceDao;
    private String name;
    private String macAddress;
    @InjectView(R.id.deviceId)
    TextView deviceId;
    @InjectView(R.id.deviceName)
    EditText deviceName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_device);
        ButterKnife.inject(this);
        init();
    }

    private void init() {
        deviceDao = new DeviceDaoImpl(this);
        id = getIntent().getStringExtra("id");
        macAddress = getIntent().getStringExtra("macAddress");
        name = getIntent().getStringExtra("name");
        position = getIntent().getIntExtra("position", -1);
        deviceId.setText(macAddress);
        deviceName.setText(name);
        deviceName.setSelection(name.length());

    }

    @OnClick(R.id.back)
    public void backClick() {
        finish();
    }

    @OnClick(R.id.save)
    void saveClick() {
        try {
            deviceDao.updateMachineName(id, deviceName.getText().toString().trim());
            Toast.makeText(this, R.string.save_success, Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.deleteDevice)
    void deleteClick() {
        try {
            deviceDao.deleteDevice(id);
            Intent intent = new Intent(EditDeviceActivity.this, DeviceActivity.class);
            intent.putExtra("position", position);
            setResult(RESULT_OK, intent);
            Toast.makeText(this, R.string.delete_success, Toast.LENGTH_SHORT).show();
            finish();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        deviceDao.closeRealm();
        ButterKnife.reset(this);
    }
}
*/
