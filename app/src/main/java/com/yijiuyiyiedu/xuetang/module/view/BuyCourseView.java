package com.yijiuyiyiedu.xuetang.module.view;

import com.yijiuyiyiedu.xuetang.module.base.BaseView;
import com.yijiuyiyiedu.xuetang.module.entity.BuyCourseEntity;
import com.yijiuyiyiedu.xuetang.module.entity.OrderEntity;
import com.yijiuyiyiedu.xuetang.module.entity.ProjectDetailsEntity;

/**
 * Created by xuenhao on 2018/3/27.
 */

public interface BuyCourseView extends BaseView<BuyCourseEntity> {
    @Override
    void showLoading();

    @Override
    void hideLoading();

    @Override
    void showData(BuyCourseEntity data);

    void showOrderData(OrderEntity data);

    void showProjectData(ProjectDetailsEntity data);

    //    void showH5Pay();
    @Override
    void showFailureMessage(String msg);

    @Override
    void showErrorMessage();
}
