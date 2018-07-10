package com.yijiuyiyiedu.xuetang.module.view;

import com.yijiuyiyiedu.xuetang.module.base.BaseView;
import com.yijiuyiyiedu.xuetang.module.entity.MineEntity;

/**
 * Created by xuenhao on 2018/3/21.
 */

public interface MineView extends BaseView<MineEntity> {

    @Override
    void showLoading();

    @Override
    void hideLoading();

    @Override
    void showData(MineEntity data);

    @Override
    void showFailureMessage(String msg);

    @Override
    void showErrorMessage();
}
