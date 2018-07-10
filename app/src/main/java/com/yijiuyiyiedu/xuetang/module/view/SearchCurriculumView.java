package com.yijiuyiyiedu.xuetang.module.view;

import com.yijiuyiyiedu.xuetang.module.base.BaseView;
import com.yijiuyiyiedu.xuetang.module.entity.CategoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.SearchCurriCulumEntity;

/**
 * Created by xuenhao on 2018/3/23.
 */

public interface SearchCurriculumView extends BaseView<SearchCurriCulumEntity> {
    @Override
    void showLoading();

    @Override
    void hideLoading();

    @Override
    void showData(SearchCurriCulumEntity data);

    void showRemodData(CategoryEntity data);

    @Override
    void showFailureMessage(String msg);

    @Override
    void showErrorMessage();
}
