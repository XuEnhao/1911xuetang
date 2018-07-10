package com.yijiuyiyiedu.xuetang.module.view;

import com.yijiuyiyiedu.xuetang.module.base.BaseView;
import com.yijiuyiyiedu.xuetang.module.entity.CourseEntity;
import com.yijiuyiyiedu.xuetang.module.entity.NewsEntity;

/**
 * Created by xuenhao on 2018/3/19.
 */

public interface CourseView extends BaseView<CourseEntity> {

    @Override
    void showLoading();

    @Override
    void hideLoading();

    @Override
    void showData(CourseEntity data);

    void showNewsData(NewsEntity data);

    @Override
    void showFailureMessage(String msg);

    @Override
    void showErrorMessage();
}
