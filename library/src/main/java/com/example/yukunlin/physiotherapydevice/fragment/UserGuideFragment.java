/*
package com.example.yukunlin.physiotherapydevice.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.yukunlin.physiotherapydevice.R;
import com.example.yukunlin.physiotherapydevice.adapter.ImageViewAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

*/
/**
 * A simple {@link Fragment} subclass.
 *//*

public class UserGuideFragment extends DialogFragment {
    @InjectView(R.id.listView)
    ListView listView;
    private int[] images = new int[]{
            R.drawable.guide_0, R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3,
            R.drawable.guide_4, R.drawable.guide_5, R.drawable.guide_6, R.drawable.guide_7,
            R.drawable.guide_8, R.drawable.guide_9, R.drawable.guide_10, R.drawable.guide_11,
            R.drawable.guide_12, R.drawable.guide_13, R.drawable.guide_14, R.drawable.guide_15,
            R.drawable.guide_16, R.drawable.guide_17, R.drawable.guide_18, R.drawable.guide_19,
            R.drawable.guide_20, R.drawable.guide_21};


    public UserGuideFragment() {
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
        View root = inflater.inflate(R.layout.fragment_user_guide, container, false);
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
