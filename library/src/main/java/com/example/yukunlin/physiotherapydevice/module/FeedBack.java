package com.example.yukunlin.physiotherapydevice.module;

/**
 * Created by shaohong on 2017-2-17.
 */
/*
{"status":"1","msg":"successs","data":"4"}
 */

public class FeedBack {

    private String status;//成功返回值
    private String msg;//返回消息
    private String data;//

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
