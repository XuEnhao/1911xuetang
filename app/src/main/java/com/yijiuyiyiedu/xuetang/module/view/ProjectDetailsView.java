package com.yijiuyiyiedu.xuetang.module.view;

import com.yijiuyiyiedu.xuetang.module.base.BaseView;
import com.yijiuyiyiedu.xuetang.module.entity.ProjectDetailsEntity;

/**
 * Created by xuenhao on 2018/4/9.
 */

public interface ProjectDetailsView extends BaseView<ProjectDetailsEntity> {
    @Override
    void showLoading();

    @Override
    void hideLoading();

    @Override
    void showData(ProjectDetailsEntity data);

    void showBoughtProject(ProjectDetailsEntity data);

    @Override
    void showFailureMessage(String msg);

    @Override
    void showErrorMessage();
}
