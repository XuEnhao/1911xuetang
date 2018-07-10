package com.yijiuyiyiedu.xuetang.module.view;

import com.yijiuyiyiedu.xuetang.module.base.BaseView;
import com.yijiuyiyiedu.xuetang.module.entity.FillRegisterDataEntity;
import com.yijiuyiyiedu.xuetang.module.entity.LoginEntity;

/**
 * Created by xuenhao on 2018/3/15.
 */

public interface FillRegisterDataView extends BaseView<LoginEntity> {

    @Override
    void showData(LoginEntity data);

    void uploadData(FillRegisterDataEntity data);

    @Override
    void showLoading();

    @Override
    void hideLoading();

    @Override
    void showFailureMessage(String msg);

    @Override
    void showErrorMessage();
}
