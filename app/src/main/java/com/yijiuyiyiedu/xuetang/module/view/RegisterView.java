package com.yijiuyiyiedu.xuetang.module.view;

import com.yijiuyiyiedu.xuetang.module.base.BaseResult;
import com.yijiuyiyiedu.xuetang.module.base.BaseView;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.LoginEntity;

/**
 * Created by xuenhao on 2018/3/15.
 */

public interface RegisterView extends BaseView<BaseResult> {

    @Override
    void showData(BaseResult data);

    void showRegisterData(LoginEntity data);

    @Override
    void showLoading();

    void showPhoneData(ClearHistoryEntity data);

    @Override
    void hideLoading();

    @Override
    void showFailureMessage(String msg);

    @Override
    void showErrorMessage();
}
