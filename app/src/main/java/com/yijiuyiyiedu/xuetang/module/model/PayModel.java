package com.yijiuyiyiedu.xuetang.module.model;

import com.lzy.okgo.OkGo;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.AliPayEntity;
import com.yijiuyiyiedu.xuetang.module.entity.PayEntity;
import com.yijiuyiyiedu.xuetang.module.entity.WXPayEntity;

/**
 * Created by xuenhao on 2018/4/16.
 */

public class PayModel {

    public void getWxPay(String orderId, JsonCallback<WXPayEntity> callback) {
//        http://edu.1911thu.com/Pay/Wechat/appPrePayment?orderId=305
        OkGo.<WXPayEntity>get(Constant.WECHAT_PAY_MENT)
                .tag(this)
                .params("orderId", orderId)
                .execute(callback);
    }

    /**
     * 支付宝支付
     *
     * @param orderId
     * @param callback
     */
    public void getAliPay(String orderId, JsonCallback<AliPayEntity> callback) {
        OkGo.<AliPayEntity>get(Constant.ALI_PAY_MENT)
                .tag(this)
                .params("orderId", orderId)
                .execute(callback);
    }


    /**
     * 获取订单信息
     * @param orderId
     * @param callback
     */
    public void getOrder(String orderId, JsonCallback<PayEntity> callback){
        OkGo.<PayEntity>post(Constant.PAY_MENT)
                .tag(this)
                .params("order_id",orderId)
                .execute(callback);
    }
}
