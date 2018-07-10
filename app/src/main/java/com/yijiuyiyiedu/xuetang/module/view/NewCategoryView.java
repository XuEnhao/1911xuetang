package com.yijiuyiyiedu.xuetang.module.view;

import com.yijiuyiyiedu.xuetang.module.base.BaseView;
import com.yijiuyiyiedu.xuetang.module.entity.NewCategoryEntity;

/**
 * Created by xuenhao on 2018/4/3.
 * 新版分类
 */

public interface NewCategoryView extends BaseView<NewCategoryEntity> {
    @Override
    void showLoading();

    @Override
    void hideLoading();

    @Override
    void showData(NewCategoryEntity data);

    @Override
    void showFailureMessage(String msg);

    @Override
    void showErrorMessage();
}
