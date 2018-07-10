package com.yijiuyiyiedu.xuetang.module.model;

import com.lzy.okgo.OkGo;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ClassContentEntity;

/**
 * Created by xuenhao on 2018/6/9.
 */

public class ClassContentModel {

    /**
     * 获取课程目录列表
     * @param curriculumId
     * @param callback
     */
    public void getCurriculumCatalogList(String curriculumId,String token, JsonCallback<ClassContentEntity> callback){
        OkGo.<ClassContentEntity>get(Constant.CURRICULUM_CATALOG_LIST)
                .params("curriculum_id",curriculumId)
                .headers("Authorization",token)
                .execute(callback);
    }
}
