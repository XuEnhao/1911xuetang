package com.yijiuyiyiedu.xuetang.module.view;

import com.yijiuyiyiedu.xuetang.module.base.BaseView;
import com.yijiuyiyiedu.xuetang.module.entity.ProjectCategoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.ProjectListEntity;

/**
 * Created by xuenhao on 2018/4/9.
 */

public interface CustomProjectView extends BaseView<ProjectCategoryEntity> {
    @Override
    void showLoading();

    @Override
    void hideLoading();

    @Override
    void showData(ProjectCategoryEntity data);

    void showProjectListData(ProjectListEntity data);

    @Override
    void showFailureMessage(String msg);

    @Override
    void showErrorMessage();
}
