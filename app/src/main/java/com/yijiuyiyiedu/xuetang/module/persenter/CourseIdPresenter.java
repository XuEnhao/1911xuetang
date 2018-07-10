package com.yijiuyiyiedu.xuetang.module.persenter;

import com.lzy.okgo.model.Response;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.CourseIdEntity;
import com.yijiuyiyiedu.xuetang.module.model.CourseIdModel;
import com.yijiuyiyiedu.xuetang.module.view.CourseIdView;

/**
 * Created by xuenhao on 2018/6/13.
 */

public class CourseIdPresenter {
    CourseIdView mView;
    CourseIdModel model;

    public CourseIdPresenter(CourseIdView mView) {
        this.mView = mView;
        model = new CourseIdModel();
    }

    /**
     * 获取已绑定课程id列表
     */
    public void getCourseListData(){
        model.getCourse(new JsonCallback<CourseIdEntity>() {
            @Override
            public void onSuccess(Response<CourseIdEntity> response) {
                mView.showCourseList(response.body());
            }
        });
    }
}
