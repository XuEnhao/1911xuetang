package com.yijiuyiyiedu.xuetang.module.entity;

import java.util.List;

/**
 * Created by xuenhao on 2018/3/22.
 */

public class MsgManagerEntity {


    /**
     * status : 0
     * msg : 成功
     * data : {"msgList":[{"id":"12","type":"1","message":"您有一条新消息3","is_read":"1","send_time":"2018-05-07","messageType":"系统消息"}]}
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
        private List<MsgListBean> msgList;

        public List<MsgListBean> getMsgList() {
            return msgList;
        }

        public void setMsgList(List<MsgListBean> msgList) {
            this.msgList = msgList;
        }

        public static class MsgListBean {
            /**
             * id : 12
             * type : 1
             * message : 您有一条新消息3
             * is_read : 1
             * send_time : 2018-05-07
             * messageType : 系统消息
             */

            private String id;
            private String type;
            private String message;
            private String is_read;
            private String send_time;
            private String messageType;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getIs_read() {
                return is_read;
            }

            public void setIs_read(String is_read) {
                this.is_read = is_read;
            }

            public String getSend_time() {
                return send_time;
            }

            public void setSend_time(String send_time) {
                this.send_time = send_time;
            }

            public String getMessageType() {
                return messageType;
            }

            public void setMessageType(String messageType) {
                this.messageType = messageType;
            }
        }
    }
}
