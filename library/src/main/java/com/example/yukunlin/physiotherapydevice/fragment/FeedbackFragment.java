/*
package com.example.yukunlin.physiotherapydevice.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yukunlin.physiotherapydevice.MyApplication;
import com.example.yukunlin.physiotherapydevice.R;
import com.example.yukunlin.physiotherapydevice.module.FeedBack;
import com.example.yukunlin.physiotherapydevice.network.ApiService;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

*/
/**
 * A simple {@link Fragment} subclass.
 *//*

public class FeedbackFragment extends DialogFragment {
    @InjectView(R.id.feedbackEditText)
    EditText feedbackEditText;


    public FeedbackFragment() {
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
        View root = inflater.inflate(R.layout.fragment_feedback, container, false);
        ButterKnife.inject(this, root);
        return root;
    }


    @OnClick(R.id.commit)
    void setCommit() {
        if (feedbackEditText.getText().toString().trim().length() == 0) {
            Toast.makeText(getContext(), R.string.content_can_not_empty, Toast.LENGTH_SHORT).show();
            return;
        }
        String feedback = feedbackEditText.getText().toString();

        //发送提交反馈请求
        ApiService.Api api = ApiService.createApi();
        api.confirmFeedBack(MyApplication.getInstance().getImei(),feedback).enqueue(new Callback<FeedBack>() {
            @Override
            public void onResponse(Call<FeedBack> call, Response<FeedBack> response) {
                String status = response.body().getStatus();
                if (status.equals("1")) {
                    Log.e("FeedBack", response.body().getMsg() + "--------" +response.body().getData());
                    Toast.makeText(getContext(), R.string.commit_success, Toast.LENGTH_SHORT).show();
                    dismiss();
                }else {
                    Toast.makeText(getContext(), R.string.commit_failure, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FeedBack> call, Throwable t) {
                Toast.makeText(getContext(), R.string.network_error, Toast.LENGTH_SHORT).show();

            }
        });


    }

    @OnClick(R.id.backImageView)
    void setBack() {
        dismiss();
    }
}
*/
