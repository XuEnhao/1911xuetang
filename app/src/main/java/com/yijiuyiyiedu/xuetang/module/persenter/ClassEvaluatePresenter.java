package com.yijiuyiyiedu.xuetang.module.persenter;

import com.lzy.okgo.model.Response;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ClassEvaluateEntity;
import com.yijiuyiyiedu.xuetang.module.model.ClassEvaluateModel;
import com.yijiuyiyiedu.xuetang.module.view.ClassEvaluateView;

/**
 * Created by xuenhao on 2018/6/9.
 */

public class ClassEvaluatePresenter {
    ClassEvaluateModel model;
    ClassEvaluateView mView;

    public ClassEvaluatePresenter(ClassEvaluateView mView) {
        this.mView = mView;
        model = new ClassEvaluateModel();
    }

    /**
     * 获取评论列表
     * @param curriculum_id
     * @param page
     * @param limit
     */
    public void getEvaluateData(String curriculum_id, String page, String limit){
        model.getEvaluateList(curriculum_id, page, limit, new JsonCallback<ClassEvaluateEntity>() {
            @Override
            public void onSuccess(Response<ClassEvaluateEntity> response) {
                mView.showData(response.body());
            }
        });
    }
}
