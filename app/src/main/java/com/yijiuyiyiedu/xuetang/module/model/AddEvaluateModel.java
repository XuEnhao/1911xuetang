package com.yijiuyiyiedu.xuetang.module.model;

import com.lzy.okgo.OkGo;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.AddEvaluateEntity;

/**
 * Created by xuenhao on 2018/4/8.
 * 添加评价的接口
 */

public class AddEvaluateModel {

    /**
     * 添加评价
     *
     * @param id
     * @param evaluate_content
     * @param score
     * @param user_id
     * @param callback
     */
    public void getAddEvaluate(String id, String evaluate_content, String score, String user_id, JsonCallback<AddEvaluateEntity> callback) {
        OkGo.<AddEvaluateEntity>post(Constant.ADD_EVALUATE)
                .tag(this)
                .params("curriculum_id", id)
                .params("evaluate_content", evaluate_content)
                .params("score", score)
                .params("user_id", user_id)
                .execute(callback);
    }
}
