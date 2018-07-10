package com.yijiuyiyiedu.xuetang.module.entity;

import java.util.List;

/**
 * Created by xuenhao on 2018/6/8.
 */

public class PositionEntity {

    /**
     * status : 0
     * msg :
     * data : [{"id":"1","position_name":"程序猿鼓励师","position_target":"鼓励师","target_color":"#FFF"}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * position_name : 程序猿鼓励师
         * position_target : 鼓励师
         * target_color : #FFF
         */

        private String id;
        private String position_name;
        private String position_target;
        private String target_color;
        private boolean check;

        public boolean isCheck() {
            return check;
        }

        public void setCheck(boolean check) {
            this.check = check;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPosition_name() {
            return position_name;
        }

        public void setPosition_name(String position_name) {
            this.position_name = position_name;
        }

        public String getPosition_target() {
            return position_target;
        }

        public void setPosition_target(String position_target) {
            this.position_target = position_target;
        }

        public String getTarget_color() {
            return target_color;
        }

        public void setTarget_color(String target_color) {
            this.target_color = target_color;
        }
    }
}
