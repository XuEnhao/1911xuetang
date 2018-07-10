package com.yijiuyiyiedu.xuetang.module.view;

import com.yijiuyiyiedu.xuetang.module.base.BaseView;
import com.yijiuyiyiedu.xuetang.module.entity.AliPayEntity;
import com.yijiuyiyiedu.xuetang.module.entity.PayEntity;
import com.yijiuyiyiedu.xuetang.module.entity.WXPayEntity;

/**
 * Created by xuenhao on 2018/4/16.
 */

public interface PayView extends BaseView<WXPayEntity> {
    @Override
    void showLoading();

    @Override
    void hideLoading();

    /**
     * 显示
     *
     * @param data 数据源
     */
    @Override
    void showData(WXPayEntity data);

    void showAliPayData(AliPayEntity data);

    /**
     * 查询微信支付
     */
    void showWxqueryData();

    /**
     * 查询阿里支付
     */
    void showAliPayqueryData();

    void showOrderData(PayEntity data);

    @Override
    void showFailureMessage(String msg);

    @Override
    void showErrorMessage();
}
