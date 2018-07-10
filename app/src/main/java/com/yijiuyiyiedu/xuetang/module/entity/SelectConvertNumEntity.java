package com.yijiuyiyiedu.xuetang.module.entity;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/21.
 */

public class SelectConvertNumEntity {

    /**
     * status : 1
     * msg : 成功
     * data : {"orderRandomList":[{"id":"14","user_id":"1","owner_id":"0","order_id":"533","pay_curriculum_ids":"17","code":"2018041874033486","status":"1","end_time":"2147483647","create_time":"1524054217"}],"total":3}
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
        /**
         * orderRandomList : [{"id":"14","user_id":"1","owner_id":"0","order_id":"533","pay_curriculum_ids":"17","code":"2018041874033486","status":"1","end_time":"2147483647","create_time":"1524054217"}]
         * total : 3
         */

        private int total;
        private List<OrderRandomListBean> orderRandomList;
        private String alltotal;

        public String getAlltotal() {
            return alltotal;
        }

        public void setAlltotal(String alltotal) {
            this.alltotal = alltotal;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<OrderRandomListBean> getOrderRandomList() {
            return orderRandomList;
        }

        public void setOrderRandomList(List<OrderRandomListBean> orderRandomList) {
            this.orderRandomList = orderRandomList;
        }

        public static class OrderRandomListBean {
            /**
             * id : 14
             * user_id : 1
             * owner_id : 0
             * order_id : 533
             * pay_curriculum_ids : 17
             * code : 2018041874033486
             * status : 1
             * end_time : 2147483647
             * create_time : 1524054217
             */

            private String id;
            private String user_id;
            private String owner_id;
            private String order_id;
            private String pay_curriculum_ids;
            private String code;
            private String status;
            private String end_time;
            private String create_time;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getOwner_id() {
                return owner_id;
            }

            public void setOwner_id(String owner_id) {
                this.owner_id = owner_id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getPay_curriculum_ids() {
                return pay_curriculum_ids;
            }

            public void setPay_curriculum_ids(String pay_curriculum_ids) {
                this.pay_curriculum_ids = pay_curriculum_ids;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
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
