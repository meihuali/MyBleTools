/*
package com.example.yukunlin.physiotherapydevice.network;

import com.example.yukunlin.physiotherapydevice.module.BaseWrap;
import com.example.yukunlin.physiotherapydevice.module.FeedBack;
import com.example.yukunlin.physiotherapydevice.module.HistoryWrap;
import com.example.yukunlin.physiotherapydevice.module.Statistical;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

*/
/**
 * Created by yukunlin on 2016/12/20.
 *//*


public class ApiService {
    public interface Api {

        // 上传历史记录
        @GET("smartfootbath/update/{machineid}/{starttime}/{stoptime}/{worktime}/{Irworktime}/{cc}/{mode}/{power}/{gear}")
        Call<BaseWrap> uploadHistory(@Path("machineid") String machineid, @Path("starttime") String starttime, @Path("stoptime") String stoptime,
                                     @Path("worktime") String worktime, @Path("Irworktime") String Irworktime, @Path("cc") String cc, @Path("mode") String mode,
                                     @Path("power") String power, @Path("gear") String gear);

        // 注册商品
        @GET("smartfootbath/reg/machine/{machineId}")
        Call<BaseWrap> regDevice(@Path("machineId") String machineId);

        // 注册手机
        @GET("smartfootbath/reg/app/{appid}")
        Call<BaseWrap> regPhone(@Path("appid") String appid);

        // app和商品绑定
        @GET("smartfootbath/bind/{appid}/{machineId}")
        Call<BaseWrap> bind(@Path("appid") String appid, @Path("machineId") String machineId);

        // 下载历史记录
        @GET("smartfootbath/download/machineid/{machineid}/number/{number}/{id}")
        Call<HistoryWrap> downloadHistory(@Path("machineid") String machineid, @Path("number") String number, @Path("id") String id);

        // 下载商品图片
        @GET("smartfootbath/portrait/")
        Call<BaseWrap> getDevicePicture();

        @GET("smartfootbath/record/{appid}/{machineid}")
        Call<BaseWrap> getRecord(@Path("appid") String appid,@Path("machineid") String machineid);

        // 上传用户信息
        @GET("smartfootbath/modify/{machineid}/{phonenumber}/{appId}/{location}")
        Call<BaseWrap> uploadUserInfo(@Path("machineid") String machineid, @Path("phonenumber") String phonenumber,
                                      @Path("appId") String appId, @Path("location") String location);

        //提交反馈信息
        @GET("smartfootbath/feedback/{appid}/{content}")
        Call<FeedBack> confirmFeedBack(@Path("appid") String appId, @Path("content") String content);

        //获取统计信息
        @GET("smartfootbath/record/{appid}/{machineid}")
        Call<Statistical> getStatistical(@Path("appid") String appId, @Path("machineid") String machineid);
    }

    public static Api createApi() {
        //添加日志拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://121.40.70.170/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        return api;
    }
}
*/
