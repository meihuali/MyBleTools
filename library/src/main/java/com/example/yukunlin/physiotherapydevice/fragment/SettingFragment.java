/*
package com.example.yukunlin.physiotherapydevice.fragment;


import android.bluetooth.BluetoothAdapter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yukunlin.physiotherapydevice.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

*/
/**
 * A simple {@link Fragment} subclass.
 *//*

public class SettingFragment extends DialogFragment {
    @InjectView(R.id.versionName)
    TextView versionName;
    @InjectView(R.id.switchCompat)
    SwitchCompat switchCompat;
    @InjectView(R.id.update)
    RelativeLayout update;
    private BluetoothAdapter mBluetoothAdapter;

    public SettingFragment() {
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
        View root = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.inject(this, root);
        init();
        return root;
    }

    private void init() {
        try {
            PackageInfo packageInfo = getActivity().getPackageManager()
                    .getPackageInfo(getActivity().getPackageName(), 0);
            versionName.setText(packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter.isEnabled()) {
            switchCompat.setChecked(true);
        } else {
            switchCompat.setChecked(false);
        }
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mBluetoothAdapter.enable();
                } else {
                    mBluetoothAdapter.disable();
                }
            }
        });

//        BDAutoUpdateSDK.uiUpdateAction(getContext(), new MyUICheckUpdateCallback());
    }

//    */
/**
//     * 自动更新接口
//     *//*

//    private class MyUICheckUpdateCallback implements UICheckUpdateCallback {
//        @Override
//        public void onCheckComplete() {
//            //检查完成后调用
//        }
//
//    }

    @OnClick(R.id.update)
    void setUpdate() {
        try {
            Thread.sleep(500);
            Toast.makeText(getContext(), "已是最新版本", Toast.LENGTH_SHORT).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @OnClick(R.id.backImageView)
    void setBack() {
        dismiss();
    }
}
*/
