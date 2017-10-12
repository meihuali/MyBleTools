/*
package com.example.yukunlin.physiotherapydevice.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yukunlin.physiotherapydevice.MyApplication;
import com.example.yukunlin.physiotherapydevice.R;
import com.example.yukunlin.physiotherapydevice.adapter.HistoryAdapter;
import com.example.yukunlin.physiotherapydevice.module.History;
import com.example.yukunlin.physiotherapydevice.module.HistoryWrap;
import com.example.yukunlin.physiotherapydevice.module.Statistical;
import com.example.yukunlin.physiotherapydevice.module.StatisticalData;
import com.example.yukunlin.physiotherapydevice.network.ApiService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

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

public class HistoryFragment extends DialogFragment {
    @InjectView(R.id.listView)
    ListView listView;

    @InjectView(R.id.usage_monthly)
    TextView mUsageMonthly;
    @InjectView(R.id.usage_total)
    TextView mUsageTotal;
    @InjectView(R.id.usage_average)
    TextView mUsageAverage;
    @InjectView(R.id.popmode)
    TextView mPopmode;
    @InjectView(R.id.power)
    TextView mPower;
    @InjectView(R.id.usage_days)
    TextView mUsageDays;
    @InjectView(R.id.date)
    TextView mDate;

    private HistoryAdapter adapter;
    private List<History> data = new ArrayList<>();
    private String macAddress;
    //    private DeviceDao deviceDao;
    private final String TAG = "HistoryFragment";

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.deviceControlTheme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.inject(this, root);
        init();
        return root;
    }

    private void init() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(macAddress, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        data = gson.fromJson(sharedPreferences.getString("history", "[]"), new TypeToken<ArrayList<History>>() {
        }.getType());
        adapter = new HistoryAdapter(getContext());
        adapter.setData(data);
        listView.setAdapter(adapter);
        //获取历史记录
        ApiService.Api api = ApiService.createApi();
        Log.e("histoy", MyApplication.getInstance().getMachineid() + "++20" + "++0");
        api.downloadHistory(MyApplication.getInstance().getMachineid(), "20", "0").enqueue(new Callback<HistoryWrap>() {
            @Override
            public void onResponse(Call<HistoryWrap> call, Response<HistoryWrap> response) {
                if (getActivity() == null)
                    return;
                if (response.body().getStatus().equals("1")) {
                    Log.d(TAG, "onResponse: ");
//                    data = response.body().getData();
                    if (response.body().getData().size() > 20) {
                        data.clear();
                        for (int i = 0; i < 20; i++) {
                            data.add(response.body().getData().get(i));
                        }
                    } else {
                        data = response.body().getData();
                    }
                    adapter.setData(data);
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getContext(), "获取历史记录失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HistoryWrap> call, Throwable t) {
                if (getActivity() == null)
                    return;
                Log.d(TAG, "onFailure: 获取历史数据失败");
            }
        });
        //获取统计数据信息
        ApiService.Api api1 = ApiService.createApi();
//        final String imei = MyApplication.getInstance().getImei();
//        final String machineid = MyApplication.getInstance().getMachineid();
//        Log.e("imei", imei + "..............." + machineid);
        api1.getStatistical(MyApplication.getInstance().getImei(),MyApplication.getInstance().getMachineid())
                .enqueue(new Callback<Statistical>() {
            @Override
            public void onResponse(Call<Statistical> call, Response<Statistical> response) {
                if (response.body().getStatus().equals("1")) {
                    //成功获取数据
                    StatisticalData statisticalData = response.body().getData();
                    mUsageTotal.setText( statisticalData.getUsageTotal()+ "次");
                    String date = statisticalData.getDate();
                    String year = date.substring(0, 4);
                    String month = date.substring(4, 6);
                    String day = date.substring(6, date.length());
                    mDate.setText(year + "-" + month + "-" + day);
                    mUsageMonthly.setText(statisticalData.getUsageMonthly() + "次");
                    mUsageAverage.setText( statisticalData.getUsageAverage() + "次");
                    if (statisticalData.getPopmode().equals("1")) {
                        mPopmode.setText("男人");
                    }else {
                        mPopmode.setText("女人");
                    }

                    mPower.setText(statisticalData.getPower());
                    mUsageDays.setText(statisticalData.getUsageDays() + "次");
                }else {
                    Toast.makeText(getContext(), "获取数据失败", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Statistical> call, Throwable t) {
                Toast.makeText(getContext(), "请求数据失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.back)
    public void backClick() {
        dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        deviceDao.closeRealm();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(macAddress, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("history", gson.toJson(data));
        editor.commit();
        ButterKnife.reset(this);
    }


    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}
*/
