package com.yijiuyiyiedu.xuetang.module.view;

import com.yijiuyiyiedu.xuetang.module.base.BaseView;
import com.yijiuyiyiedu.xuetang.module.entity.MsgManagerEntity;

/**
 * Created by xuenhao on 2018/3/22.
 */

public interface MsgManagerView extends BaseView<MsgManagerEntity> {
    @Override
    void showLoading();

    @Override
    void hideLoading();

    @Override
    void showData(MsgManagerEntity data);

    @Override
    void showFailureMessage(String msg);

    @Override
    void showErrorMessage();
}
