package com.yijiuyiyiedu.xuetang.module.entity;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/19.
 */

public class VipListEntity {


    /**
     * status : 1
     * msg : 成功
     * data : {"rechargeList":[{"id":"1","recharge_name":"24小时会员","price":"0.01","valid_time":"86400","status":"1","create_time":"1111111"}]}
     */

    private String status;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private String memberInfo;
        private List<RechargeListBean> rechargeList;

        public String getMemberInfo() {
            return memberInfo;
        }

        public void setMemberInfo(String memberInfo) {
            this.memberInfo = memberInfo;
        }

        public List<RechargeListBean> getRechargeList() {
            return rechargeList;
        }

        public void setRechargeList(List<RechargeListBean> rechargeList) {
            this.rechargeList = rechargeList;
        }

        public static class RechargeListBean {
            /**
             * id : 1
             * recharge_name : 24小时会员
             * price : 0.01
             * valid_time : 86400
             * status : 1
             * create_time : 1111111
             */

            private String id;
            private String recharge_name;
            private String price;
            private String valid_time;
            private String status;
            private String create_time;
            private String isCheck;

            public String getIsCheck() {
                return isCheck;
            }

            public void setIsCheck(String isCheck) {
                this.isCheck = isCheck;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRecharge_name() {
                return recharge_name;
            }

            public void setRecharge_name(String recharge_name) {
                this.recharge_name = recharge_name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getValid_time() {
                return valid_time;
            }

            public void setValid_time(String valid_time) {
                this.valid_time = valid_time;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }
    }
}
