package com.yijiuyiyiedu.xuetang.module.model;

import com.lzy.okgo.OkGo;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ClassEvaluateEntity;

/**
 * Created by xuenhao on 2018/6/9.
 */

public class ClassEvaluateModel {
    /**
     * 获取评论列表
     * @param curriculum_id
     * @param page
     * @param limit
     * @param callback
     */
    public void getEvaluateList(String curriculum_id, String page, String limit, JsonCallback<ClassEvaluateEntity> callback){
        OkGo.<ClassEvaluateEntity>get(Constant.GET_EVALUATE)
                .params("curriculum_id",curriculum_id)
                .params("page",page)
                .params("limit",limit)
                .execute(callback);
    }
}
