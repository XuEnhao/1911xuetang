package com.yijiuyiyiedu.xuetang.module.view;

import com.yijiuyiyiedu.xuetang.module.base.BaseView;
import com.yijiuyiyiedu.xuetang.module.entity.CourseDetailsEntity;

/**
 * Created by xuenhao on 2018/3/29.
 */

public interface PlayVideoView extends BaseView<String> {
//    @Override
//    void showLoading();
//
//    @Override
//    void hideLoading();


    void showContentData(CourseDetailsEntity data);

    void showVideoData(String data);

    @Override
    void showFailureMessage(String msg);

    @Override
    void showErrorMessage();
}
