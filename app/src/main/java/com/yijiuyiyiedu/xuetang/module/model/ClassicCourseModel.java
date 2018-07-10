package com.yijiuyiyiedu.xuetang.module.model;

import com.lzy.okgo.OkGo;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ClassicCourseEntity;

/**
 * Created by xuenhao on 2018/6/11.
 */

public class ClassicCourseModel {

    /**
     * 获取经典好课列表
     * @param page
     * @param limit
     * @param categoryId
     * @param sortBy
     * @param callback
     */
    public void getRecommendCurriculumList(int page, int limit, int categoryId, int sortBy, JsonCallback<ClassicCourseEntity> callback){
        OkGo.<ClassicCourseEntity>get(Constant.RECOMMEND_CURRICULUM_LIST)
                .params("page",page)
                .params("limit",limit)
                .params("category_id",categoryId)
                .params("sort_by",sortBy)
                .execute(callback);
    }
}
