package com.yijiuyiyiedu.xuetang.module.base;

/**
 * Created by ${二星} on 2017/4/21 0021.
 */

public class BaseResult<T> {
    private int code;
    private String msg;
    T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
