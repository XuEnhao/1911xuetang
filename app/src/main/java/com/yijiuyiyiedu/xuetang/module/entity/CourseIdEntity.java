package com.yijiuyiyiedu.xuetang.module.entity;

import java.util.List;

/**
 * Created by xuenhao on 2018/6/13.
 */

public class CourseIdEntity {


    /**
     * status : 0
     * msg :
     * data : {"bindList":[{"invitation_code":"1911the7o"}]}
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
        private List<BindListBean> bindList;

        public List<BindListBean> getBindList() {
            return bindList;
        }

        public void setBindList(List<BindListBean> bindList) {
            this.bindList = bindList;
        }

        public static class BindListBean {
            /**
             * invitation_code : 1911the7o
             */

            private String invitation_code;

            public String getInvitation_code() {
                return invitation_code;
            }

            public void setInvitation_code(String invitation_code) {
                this.invitation_code = invitation_code;
            }
        }
    }
}
