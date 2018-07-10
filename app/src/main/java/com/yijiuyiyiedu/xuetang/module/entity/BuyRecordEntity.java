package com.yijiuyiyiedu.xuetang.module.entity;

import java.util.List;

/**
 * Created by xuenhao on 2018/3/23.
 */

public class BuyRecordEntity {


    /**
     * status : 1
     * msg : 成功
     * data : {"orderList":[{"id":"594","curriculum_id":"18","user_id":"1","pay_curriculum_ids":"18","pay_number":"1","pay_type":"1","pay_status":"1","order_sn":"RCM2018041950450528","discount":"1.00","goods_amount":"0.01","order_amount":"0.01","pay_amount":"0.00","pay_order_sn":"","trade_type":"","payment_sn":"","payment_type":"","pay_time":"1524131022","payment_method":"","refund_order_sn":"","refund_sn":"","status":"1","create_time":"2018-04-19 17:43","title":"知识管理：零基础搭建高效知识体系","picture":"/uploads/Curriculum/2018/04/12/1523503226.png","price":"0.01"}]}
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
        private List<OrderListBean> orderList;

        public List<OrderListBean> getOrderList() {
            return orderList;
        }

        public void setOrderList(List<OrderListBean> orderList) {
            this.orderList = orderList;
        }

        public static class OrderListBean {
            /**
             * id : 594
             * curriculum_id : 18
             * user_id : 1
             * pay_curriculum_ids : 18
             * pay_number : 1
             * pay_type : 1
             * pay_status : 1
             * order_sn : RCM2018041950450528
             * discount : 1.00
             * goods_amount : 0.01
             * order_amount : 0.01
             * pay_amount : 0.00
             * pay_order_sn :
             * trade_type :
             * payment_sn :
             * payment_type :
             * pay_time : 1524131022
             * payment_method :
             * refund_order_sn :
             * refund_sn :
             * status : 1
             * create_time : 2018-04-19 17:43
             * title : 知识管理：零基础搭建高效知识体系
             * picture : /uploads/Curriculum/2018/04/12/1523503226.png
             * price : 0.01
             */

            private String id;
            private String curriculum_id;
            private String user_id;
            private String pay_curriculum_ids;
            private String pay_number;
            private String pay_type;
            private String pay_status;
            private String order_sn;
            private String discount;
            private String goods_amount;
            private String order_amount;
            private String pay_amount;
            private String pay_order_sn;
            private String trade_type;
            private String payment_sn;
            private String payment_type;
            private String pay_time;
            private String payment_method;
            private String refund_order_sn;
            private String refund_sn;
            private String status;
            private String create_time;
            private String title;
            private String picture;
            private String price;
            private String lecturer;
            private String totalCurriculum;
            private String curriculum_time;

            public String getTotalCurriculum() {
                return totalCurriculum;
            }

            public void setTotalCurriculum(String totalCurriculum) {
                this.totalCurriculum = totalCurriculum;
            }

            public String getCurriculum_time() {
                return curriculum_time;
            }

            public void setCurriculum_time(String curriculum_time) {
                this.curriculum_time = curriculum_time;
            }

            public String getLecturer() {
                return lecturer;
            }

            public void setLecturer(String lecturer) {
                this.lecturer = lecturer;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCurriculum_id() {
                return curriculum_id;
            }

            public void setCurriculum_id(String curriculum_id) {
                this.curriculum_id = curriculum_id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getPay_curriculum_ids() {
                return pay_curriculum_ids;
            }

            public void setPay_curriculum_ids(String pay_curriculum_ids) {
                this.pay_curriculum_ids = pay_curriculum_ids;
            }

            public String getPay_number() {
                return pay_number;
            }

            public void setPay_number(String pay_number) {
                this.pay_number = pay_number;
            }

            public String getPay_type() {
                return pay_type;
            }

            public void setPay_type(String pay_type) {
                this.pay_type = pay_type;
            }

            public String getPay_status() {
                return pay_status;
            }

            public void setPay_status(String pay_status) {
                this.pay_status = pay_status;
            }

            public String getOrder_sn() {
                return order_sn;
            }

            public void setOrder_sn(String order_sn) {
                this.order_sn = order_sn;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public String getGoods_amount() {
                return goods_amount;
            }

            public void setGoods_amount(String goods_amount) {
                this.goods_amount = goods_amount;
            }

            public String getOrder_amount() {
                return order_amount;
            }

            public void setOrder_amount(String order_amount) {
                this.order_amount = order_amount;
            }

            public String getPay_amount() {
                return pay_amount;
            }

            public void setPay_amount(String pay_amount) {
                this.pay_amount = pay_amount;
            }

            public String getPay_order_sn() {
                return pay_order_sn;
            }

            public void setPay_order_sn(String pay_order_sn) {
                this.pay_order_sn = pay_order_sn;
            }

            public String getTrade_type() {
                return trade_type;
            }

            public void setTrade_type(String trade_type) {
                this.trade_type = trade_type;
            }

            public String getPayment_sn() {
                return payment_sn;
            }

            public void setPayment_sn(String payment_sn) {
                this.payment_sn = payment_sn;
            }

            public String getPayment_type() {
                return payment_type;
            }

            public void setPayment_type(String payment_type) {
                this.payment_type = payment_type;
            }

            public String getPay_time() {
                return pay_time;
            }

            public void setPay_time(String pay_time) {
                this.pay_time = pay_time;
            }

            public String getPayment_method() {
                return payment_method;
            }

            public void setPayment_method(String payment_method) {
                this.payment_method = payment_method;
            }

            public String getRefund_order_sn() {
                return refund_order_sn;
            }

            public void setRefund_order_sn(String refund_order_sn) {
                this.refund_order_sn = refund_order_sn;
            }

            public String getRefund_sn() {
                return refund_sn;
            }

            public void setRefund_sn(String refund_sn) {
                this.refund_sn = refund_sn;
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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }
    }
}
