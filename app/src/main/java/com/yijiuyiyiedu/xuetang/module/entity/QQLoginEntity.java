package com.yijiuyiyiedu.xuetang.module.entity;

/**
 * Created by xuenhao on 2018/4/28.
 */

public class QQLoginEntity {


    /**
     * status : 0
     * msg : 登录成功
     * data : {"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiNTIifQ.sYHSsmA40ad6bCxF4CLtxrB6DCJWiAL_w-ApyPzr73U"}
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
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiNTIifQ.sYHSsmA40ad6bCxF4CLtxrB6DCJWiAL_w-ApyPzr73U
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
