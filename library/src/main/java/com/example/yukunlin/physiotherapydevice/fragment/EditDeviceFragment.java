/*
package com.example.yukunlin.physiotherapydevice.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yukunlin.physiotherapydevice.R;
import com.example.yukunlin.physiotherapydevice.module.Device;
import com.example.yukunlin.physiotherapydevice.utils.DeviceDao;
import com.example.yukunlin.physiotherapydevice.utils.DeviceDaoImpl;

import java.sql.SQLException;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

*/
/**
 * 编辑设备界面
 * A simple {@link Fragment} subclass.
 *//*

public class EditDeviceFragment extends DialogFragment {

    @InjectView(R.id.macAddress)
    TextView macAddress;
    @InjectView(R.id.deviceName)
    EditText deviceName;
    private Device data;
    private DeviceDao deviceDao;
    private OnSaveListener onSaveListener;

    public void setOnSaveListener(OnSaveListener onSaveListener) {
        this.onSaveListener = onSaveListener;
    }

    public EditDeviceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_edit_device, container, false);
        ButterKnife.inject(this, root);
        init();
        return root;
    }

    private void init() {
        deviceDao = new DeviceDaoImpl(getContext());
        macAddress.setText(data.getMacAddress());
//        deviceName.setText(data.getName());
        deviceName.setText(R.string.device_name);

    }

    public void setData(Device data) {
        this.data = data;
    }

    @OnClick(R.id.addDevice)
    void addClick() {

        data.setName(deviceName.getText().toString().trim());
        try {
            List<Device> allDevice = deviceDao.getAllDevice();
            for (int i = 0; i < allDevice.size(); i++) {
                if (allDevice.get(i).getMacAddress().equals(data.getMacAddress())) {
                    Toast.makeText(getContext(), R.string.already_add, Toast.LENGTH_SHORT).show();
                    getDialog().cancel();
                    return;
                }
            }
            deviceDao.insert(data);
            Toast.makeText(getContext(), R.string.add_success, Toast.LENGTH_SHORT).show();
            onSaveListener.onSave();
            dismiss();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
        deviceDao.closeRealm();
    }

    @OnClick(R.id.back)
    void setBack() {
        dismiss();
    }

    public interface OnSaveListener {
        void onSave();
    }
}
*/
