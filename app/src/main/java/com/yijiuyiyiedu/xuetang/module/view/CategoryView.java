package com.yijiuyiyiedu.xuetang.module.view;

import com.yijiuyiyiedu.xuetang.module.base.BaseView;
import com.yijiuyiyiedu.xuetang.module.entity.CategoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.NewChildCategoryEntity;

/**
 * Created by xuenhao on 2018/3/21.
 */

public interface CategoryView extends BaseView<CategoryEntity> {
    @Override
    void showLoading();

    @Override
    void hideLoading();

    @Override
    void showData(CategoryEntity data);

    void showChildData(NewChildCategoryEntity data);

    @Override
    void showFailureMessage(String msg);

    @Override
    void showErrorMessage();
}
