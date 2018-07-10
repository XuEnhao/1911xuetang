package com.yijiuyiyiedu.xuetang.module.entity;

import java.io.Serializable;

/**
 * Created by xuenhao on 2018/3/15.
 */

public class LoginEntity implements Serializable {

    /**
     * status : 1
     * msg : u8d26u53f7u4e0du80fdu4e3au7a7a
     * data : {"user_id":"1"}
     */

    private int status;
    private String msg;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * token : 1
         */

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
