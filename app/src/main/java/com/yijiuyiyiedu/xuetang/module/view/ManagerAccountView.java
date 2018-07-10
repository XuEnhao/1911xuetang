package com.yijiuyiyiedu.xuetang.module.view;

import com.yijiuyiyiedu.xuetang.module.base.BaseView;
import com.yijiuyiyiedu.xuetang.module.entity.ManagerAccountEntity;

/**
 * Created by xuenhao on 2018/3/22.
 */

public interface ManagerAccountView extends BaseView<ManagerAccountEntity> {
    @Override
    void showLoading();

    @Override
    void hideLoading();

    @Override
    void showData(ManagerAccountEntity data);

    @Override
    void showFailureMessage(String msg);

    @Override
    void showErrorMessage();
}
