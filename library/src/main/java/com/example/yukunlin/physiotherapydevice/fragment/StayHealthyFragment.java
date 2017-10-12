/*
package com.example.yukunlin.physiotherapydevice.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.yukunlin.physiotherapydevice.R;
import com.example.yukunlin.physiotherapydevice.adapter.ImageViewAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

*/
/**
 * A simple {@link Fragment} subclass.
 *//*

public class StayHealthyFragment extends DialogFragment {
    @InjectView(R.id.listView)
    ListView listView;
    private int[] images = new int[]{
            R.drawable.qing_0, R.drawable.qing_1, R.drawable.qing_2, R.drawable.qing_3,
            R.drawable.qing_4, R.drawable.qing_5, R.drawable.qing_6, R.drawable.qing_7,
            R.drawable.qing_8, R.drawable.qing_9, R.drawable.qing_10, R.drawable.qing_11,
            R.drawable.qing_12, R.drawable.qing_13, R.drawable.qing_14, R.drawable.qing_15,
            R.drawable.qing_16, R.drawable.qing_17, R.drawable.qing_18, R.drawable.qing_19,
            R.drawable.qing_20, R.drawable.qing_21, R.drawable.qing_22};

    public StayHealthyFragment() {
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
        View root = inflater.inflate(R.layout.fragment_stay_healthy, container, false);
        ButterKnife.inject(this, root);
        init();
        return root;
    }

    private void init() {
        ImageViewAdapter adapter = new ImageViewAdapter(getContext());
        adapter.setImages(images);
        listView.setAdapter(adapter);
    }

}
*/
