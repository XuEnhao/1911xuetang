package com.yijiuyiyiedu.xuetang.module.view;

import com.yijiuyiyiedu.xuetang.module.base.BaseView;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.LoginEntity;

/**
 * Created by xuenhao on 2018/3/15.
 */

public interface LoginView extends BaseView<LoginEntity> {

    @Override
    void showData(LoginEntity data);

    @Override
    void showLoading();

    @Override
    void hideLoading();

    @Override
    void showFailureMessage(String msg);

    void showPhoneData(ClearHistoryEntity data);

    @Override
    void showErrorMessage();
}
