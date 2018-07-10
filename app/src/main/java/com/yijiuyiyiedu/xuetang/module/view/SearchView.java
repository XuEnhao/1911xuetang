package com.yijiuyiyiedu.xuetang.module.view;

import com.yijiuyiyiedu.xuetang.module.base.BaseView;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.SearchCurriCulumEntity;
import com.yijiuyiyiedu.xuetang.module.entity.SearchEntity;
import com.yijiuyiyiedu.xuetang.module.entity.SearchNoneEntity;

/**
 * Created by xuenhao on 2018/3/23.
 */

public interface SearchView extends BaseView<SearchEntity> {
    @Override
    void showLoading();

    @Override
    void hideLoading();

    @Override
    void showData(SearchEntity data);

    void showClearData(ClearHistoryEntity data);

    void showCurriculumData(SearchCurriCulumEntity data);

    void showNoneData(SearchNoneEntity data);

    @Override
    void showFailureMessage(String msg);

    @Override
    void showErrorMessage();
}
