package com.yijiuyiyiedu.xuetang.module.entity;

/**
 * Created by xuenhao on 2018/4/19.
 * //生成订单
 */

public class PayEntity {

    /**
     * status : 1
     * msg : 成功
     * data : {"id":"订单id","curriculum_id":"课程id","user_id":"用户id","type":" 1 订单类型 1 个人订单  2 企业订单","pay_status":"2 支付状态 1 待支付 2已支付 3取消 4退款","order_sn":"rcm2131231232 订单号","discount":"1.00 折扣信息  ","goods_amount":"45.00 商品总价","order_amount":"56.00 订单总计","payment_number":"12 三方平台支付流水号","pay_time":"2131231231 支付时间","payment_method":"1312 支付方式","refund_order_sn":"退款单号","refund_sn":"第三方退款单号","status":"1","create_time":"创建时间","title":"课程名称"}
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
         * id : 订单id
         * curriculum_id : 课程id
         * user_id : 用户id
         * type :  1 订单类型 1 个人订单  2 企业订单
         * pay_status : 2 支付状态 1 待支付 2已支付 3取消 4退款
         * order_sn : rcm2131231232 订单号
         * discount : 1.00 折扣信息
         * goods_amount : 45.00 商品总价
         * order_amount : 56.00 订单总计
         * payment_number : 12 三方平台支付流水号
         * pay_time : 2131231231 支付时间
         * payment_method : 1312 支付方式
         * refund_order_sn : 退款单号
         * refund_sn : 第三方退款单号
         * status : 1
         * create_time : 创建时间
         * title : 课程名称
         */

        private String id;
        private String curriculum_id;
        private String user_id;
        private String type;
        private String pay_status;
        private String order_sn;
        private String discount;
        private String goods_amount;
        private String order_amount;
        private String payment_number;
        private String pay_time;
        private String payment_method;
        private String refund_order_sn;
        private String refund_sn;
        private String status;
        private String create_time;
        private String title;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public String getPayment_number() {
            return payment_number;
        }

        public void setPayment_number(String payment_number) {
            this.payment_number = payment_number;
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
    }
}
