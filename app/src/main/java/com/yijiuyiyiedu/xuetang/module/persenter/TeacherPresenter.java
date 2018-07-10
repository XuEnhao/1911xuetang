package com.yijiuyiyiedu.xuetang.module.persenter;

import com.lzy.okgo.model.Response;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.TeacherEntity;
import com.yijiuyiyiedu.xuetang.module.model.TeacherModel;
import com.yijiuyiyiedu.xuetang.module.view.TeacherView;

/**
 * Created by xuenhao on 2018/6/11.
 */

public class TeacherPresenter {

    TeacherModel model;
    TeacherView mView;

    public TeacherPresenter(TeacherView mView) {
        this.mView = mView;
        model = new TeacherModel();
    }

    /**
     * 获取名师列表
     * @param page
     * @param limit
     */
    public void getTeacherData(int page,int limit){
        model.getTeacherList(page, limit, new JsonCallback<TeacherEntity>() {
            @Override
            public void onSuccess(Response<TeacherEntity> response) {
                mView.showTeacherData(response.body());
            }
        });
    }
}
