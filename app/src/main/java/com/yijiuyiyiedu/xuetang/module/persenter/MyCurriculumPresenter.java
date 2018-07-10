package com.yijiuyiyiedu.xuetang.module.persenter;

import com.lzy.okgo.model.Response;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.MyCurriculumEntity;
import com.yijiuyiyiedu.xuetang.module.model.MyCurriculumModel;
import com.yijiuyiyiedu.xuetang.module.view.MyCurriculumView;

/**
 * Created by xuenhao on 2018/6/13.
 * 我的课程列表
 */

public class MyCurriculumPresenter {
    MyCurriculumView mView;
    MyCurriculumModel model;

    public MyCurriculumPresenter(MyCurriculumView mView) {
        this.mView = mView;
        model = new MyCurriculumModel();
    }

    /**
     * 获取我的课程列表
     * @param type
     * @param page
     * @param limit
     */
    public void getMyCurriculumData(int type, int page, int limit){
        model.getMyCurriculum(type, page, limit, new JsonCallback<MyCurriculumEntity>() {
            @Override
            public void onSuccess(Response<MyCurriculumEntity> response) {
                mView.showMyCurriculumData(response.body());
            }
        });
    }
}
