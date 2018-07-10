package com.yijiuyiyiedu.xuetang.module.persenter;

import com.lzy.okgo.model.Response;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.NewCourseEntity;
import com.yijiuyiyiedu.xuetang.module.model.NewCourseModel;
import com.yijiuyiyiedu.xuetang.module.view.NewCourseView;

/**
 * Created by xuenhao on 2018/6/11.
 */

public class NewCoursePresenter {
    NewCourseView mView;
    NewCourseModel model;

    public NewCoursePresenter(NewCourseView mView) {
        this.mView = mView;
        model = new NewCourseModel();
    }

    public void getNewCourseData(int page,int limit){
        model.getNewCurriculumList(page, limit, new JsonCallback<NewCourseEntity>() {
            @Override
            public void onSuccess(Response<NewCourseEntity> response) {
                mView.showNewCourseData(response.body());
            }
        });
    }
}
