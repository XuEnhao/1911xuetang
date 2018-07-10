package com.yijiuyiyiedu.xuetang.module.view;

import com.yijiuyiyiedu.xuetang.module.base.BaseView;
import com.yijiuyiyiedu.xuetang.module.entity.ALNAEntity;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.CollectCourseEntity;
import com.yijiuyiyiedu.xuetang.module.entity.CourseDetailsEntity;
import com.yijiuyiyiedu.xuetang.module.entity.PlayVideoEntity;

/**
 * Created by xuenhao on 2018/3/22.
 */

public interface CourseDetailsView extends BaseView<CourseDetailsEntity> {

    @Override
    void showLoading();

    @Override
    void hideLoading();

    @Override
    void showData(CourseDetailsEntity data);

    void showCollectData(CollectCourseEntity data);

    void showVideoData(String data);

    void showALNAData(ALNAEntity data);

    void showSaveSchedule(ClearHistoryEntity data);

    @Override
    void showFailureMessage(String msg);

    @Override
    void showErrorMessage();
}
