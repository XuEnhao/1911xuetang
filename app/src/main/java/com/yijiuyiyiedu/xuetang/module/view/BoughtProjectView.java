package com.yijiuyiyiedu.xuetang.module.view;

import com.yijiuyiyiedu.xuetang.module.base.BaseView;
import com.yijiuyiyiedu.xuetang.module.entity.BoughtProjectEntity;

/**
 * Created by xuenhao on 2018/4/20.
 * 已购项目
 */

public interface BoughtProjectView extends BaseView<BoughtProjectEntity>{
    @Override
    void showLoading();

    @Override
    void hideLoading();

    @Override
    void showData(BoughtProjectEntity data);

    @Override
    void showFailureMessage(String msg);

    @Override
    void showErrorMessage();
}
