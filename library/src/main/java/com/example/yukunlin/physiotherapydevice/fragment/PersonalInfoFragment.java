/*
package com.example.yukunlin.physiotherapydevice.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yukunlin.physiotherapydevice.R;
import com.example.yukunlin.physiotherapydevice.module.Device;
import com.example.yukunlin.physiotherapydevice.utils.DeviceDao;
import com.example.yukunlin.physiotherapydevice.utils.DeviceDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

*/
/**
 * A simple {@link Fragment} subclass.
 *//*

public class PersonalInfoFragment extends DialogFragment {
    @InjectView(R.id.phoneNumberTextView)
    TextView phoneNumberTextView;
    @InjectView(R.id.deviceCount)
    TextView deviceCount;

    public PersonalInfoFragment() {
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
        View root = inflater.inflate(R.layout.fragment_personal_info, container, false);
        ButterKnife.inject(this, root);
        init();
        return root;
    }

    private void init() {
        SharedPreferences preferences = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String phoneNumber = preferences.getString("phoneNumber", null);
        if (phoneNumber != null) {
            phoneNumberTextView.setText(phoneNumber);
        } else {
            phoneNumberTextView.setText("未知");
        }

        DeviceDao deviceDao = new DeviceDaoImpl(getActivity());
        try {
            List<Device> allDevice = deviceDao.getAllDevice();
            if (allDevice != null && allDevice.size() != 0) {
                deviceCount.setText(allDevice.size() + "");
            } else {
                deviceCount.setText("0");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.backImageView)
    void setBack() {
        dismiss();
    }
}
*/
