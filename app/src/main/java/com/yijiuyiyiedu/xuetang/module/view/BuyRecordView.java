package com.yijiuyiyiedu.xuetang.module.view;

import com.yijiuyiyiedu.xuetang.module.base.BaseView;
import com.yijiuyiyiedu.xuetang.module.entity.BuyRecordEntity;

/**
 * Created by xuenhao on 2018/3/23.
 */

public interface BuyRecordView extends BaseView<BuyRecordEntity> {
    @Override
    void showLoading();

    @Override
    void hideLoading();

    @Override
    void showData(BuyRecordEntity data);

    @Override
    void showFailureMessage(String msg);

    @Override
    void showErrorMessage();
}
