package com.yijiuyiyiedu.xuetang.module.persenter;

import com.lzy.okgo.model.Response;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ClassicCourseEntity;
import com.yijiuyiyiedu.xuetang.module.model.ClassicCourseModel;
import com.yijiuyiyiedu.xuetang.module.view.ClassicCourseView;

/**
 * Created by xuenhao on 2018/6/11.
 */

public class ClassicCoursePresenter {

    ClassicCourseModel model;
    ClassicCourseView mView;

    public ClassicCoursePresenter(ClassicCourseView mView) {
        this.mView = mView;
        model = new ClassicCourseModel();
    }

    public void getRecommendData(int page, int limit, int categoryId, int sortBy){
        model.getRecommendCurriculumList(page, limit, categoryId, sortBy, new JsonCallback<ClassicCourseEntity>() {
            @Override
            public void onSuccess(Response<ClassicCourseEntity> response) {
                mView.showRecommendData(response.body());
            }
        });
    }
}
