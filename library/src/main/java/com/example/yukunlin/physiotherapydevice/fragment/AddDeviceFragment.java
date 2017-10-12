/*
package com.example.yukunlin.physiotherapydevice.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.yukunlin.physiotherapydevice.R;
import com.example.yukunlin.physiotherapydevice.adapter.AddDeviceAdapter;
import com.example.yukunlin.physiotherapydevice.module.Device;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

*/
/**
 * A simple {@link Fragment} subclass.
 *//*


public class AddDeviceFragment extends DialogFragment {
    @InjectView(R.id.gridView)
    GridView gridView;
    private List<Device> data;
    private OnSaveListener onSaveListener;

    public void setOnSaveListener(OnSaveListener onSaveListener) {
        this.onSaveListener = onSaveListener;
    }

    public void setData(List<Device> data) {
        this.data = data;
    }

    public AddDeviceFragment() {
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
        View root = inflater.inflate(R.layout.fragment_add_device, container, false);
        ButterKnife.inject(this, root);
        init();
        return root;
    }

    private void init() {
        final AddDeviceAdapter adapter = new AddDeviceAdapter(getContext());
        adapter.setData(data);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                EditDeviceFragment fragment = new EditDeviceFragment();
                fragment.setData(adapter.getData().get(i));
                fragment.setOnSaveListener(new EditDeviceFragment.OnSaveListener() {
                    @Override
                    public void onSave() {
                        onSaveListener.onSave();
                    }
                });
                fragment.show(getActivity().getSupportFragmentManager(), "dialogFragment");
                dismiss();

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.backImageView)
    void backClick() {
        dismiss();
    }

    public interface OnSaveListener {
        void onSave();
    }
}
*/
