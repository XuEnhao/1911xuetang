package com.yijiuyiyiedu.xuetang.module.view;

import com.yijiuyiyiedu.xuetang.module.base.BaseView;
import com.yijiuyiyiedu.xuetang.module.entity.StudyEntity;

/**
 * Created by xuenhao on 2018/3/20.
 */

public interface StudyView extends BaseView<StudyEntity> {
    @Override
    void showLoading();

    @Override
    void hideLoading();

    @Override
    void showData(StudyEntity data);

    @Override
    void showFailureMessage(String msg);

    @Override
    void showErrorMessage();
}
