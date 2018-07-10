package com.yijiuyiyiedu.xuetang.module.persenter;

import com.lzy.okgo.model.Response;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.AddEvaluateEntity;
import com.yijiuyiyiedu.xuetang.module.model.AddEvaluateModel;
import com.yijiuyiyiedu.xuetang.module.view.AddEvaluateView;

/**
 * Created by xuenhao on 2018/4/8.
 */

public class AddEvaluatePresenter {

    AddEvaluateModel model;
    AddEvaluateView mView;

    public AddEvaluatePresenter(AddEvaluateView mView) {
        this.mView = mView;
        model = new AddEvaluateModel();
    }

    /**
     * 获取评价信息
     *
     * @param id
     * @param evaluate_content
     * @param score
     * @param user_id
     */
    public void getAddEvaluateData(String id, String evaluate_content, String score, String user_id) {
        model.getAddEvaluate(id, evaluate_content, score, user_id, new JsonCallback<AddEvaluateEntity>() {
            @Override
            public void onSuccess(Response<AddEvaluateEntity> response) {
                mView.showAddEvaluateData(response.body());
            }
        });
    }
}
