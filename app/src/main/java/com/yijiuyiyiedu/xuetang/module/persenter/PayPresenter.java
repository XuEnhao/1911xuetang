package com.yijiuyiyiedu.xuetang.module.persenter;

import com.lzy.okgo.model.Response;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.AliPayEntity;
import com.yijiuyiyiedu.xuetang.module.entity.PayEntity;
import com.yijiuyiyiedu.xuetang.module.entity.WXPayEntity;
import com.yijiuyiyiedu.xuetang.module.model.PayModel;
import com.yijiuyiyiedu.xuetang.module.view.PayView;

/**
 * Created by xuenhao on 2018/4/16.
 */

public class PayPresenter {
    PayModel model;
    PayView mView;

    public PayPresenter(PayView mView) {
        this.mView = mView;
        model = new PayModel();
    }

    /**
     * 订单id返回支付信息
     *
     * @param orderId
     */
    public void getWxPayData(String orderId) {
        mView.showLoading();
        model.getWxPay(orderId, new JsonCallback<WXPayEntity>() {
            @Override
            public void onSuccess(Response<WXPayEntity> response) {
                mView.showData(response.body());
                mView.hideLoading();
            }
        });
    }

    /**
     * 获取支付宝信息
     *
     * @param orderId
     */
    public void getAliPayData(String orderId) {
        mView.showLoading();
        model.getAliPay(orderId, new JsonCallback<AliPayEntity>() {
            @Override
            public void onSuccess(Response<AliPayEntity> response) {
                mView.showAliPayData(response.body());
                mView.hideLoading();
            }
        });
    }

    /**
     * 获取订单信息
     * @param orderId
     */
    public void getOrderData(String orderId){
        mView.showLoading();
        model.getOrder(orderId, new JsonCallback<PayEntity>() {
            @Override
            public void onSuccess(Response<PayEntity> response) {
                mView.showOrderData(response.body());
                mView.hideLoading();
            }
        });
    }
}
