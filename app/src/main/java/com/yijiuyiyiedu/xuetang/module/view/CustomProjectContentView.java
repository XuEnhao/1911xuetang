package com.yijiuyiyiedu.xuetang.module.view;

import com.yijiuyiyiedu.xuetang.module.base.BaseView;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;

/**
 * Created by xuenhao on 2018/4/11.
 */

public interface CustomProjectContentView extends BaseView<ClearHistoryEntity> {
    @Override
    void showLoading();

    @Override
    void hideLoading();

    @Override
    void showData(ClearHistoryEntity data);

    @Override
    void showFailureMessage(String msg);

    @Override
    void showErrorMessage();
}
