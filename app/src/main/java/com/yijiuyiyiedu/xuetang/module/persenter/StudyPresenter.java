package com.yijiuyiyiedu.xuetang.module.persenter;

import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.StudyEntity;
import com.yijiuyiyiedu.xuetang.module.model.StudyModel;
import com.yijiuyiyiedu.xuetang.module.view.StudyView;
import com.lzy.okgo.model.Response;

/**
 * Created by xuenhao on 2018/3/20.
 */

public class StudyPresenter {
    StudyModel model;
    StudyView mView;

    public StudyPresenter(StudyView view) {
        mView = view;
        model = new StudyModel();
    }

    public void getData(String userid, String page, String limit) {
        mView.showLoading();
        model.getUserStudyList(userid, page, limit, new JsonCallback<StudyEntity>() {

            @Override
            public void onSuccess(Response<StudyEntity> response) {
                mView.showData(response.body());
                mView.hideLoading();
            }
        });
    }
}
