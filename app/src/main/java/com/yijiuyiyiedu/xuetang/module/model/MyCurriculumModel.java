package com.yijiuyiyiedu.xuetang.module.model;

import com.lzy.okgo.OkGo;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.MyCurriculumEntity;

/**
 * Created by xuenhao on 2018/6/13.
 * 我的课程 二级
 */

public class MyCurriculumModel {

    /**
     * 获取我的课程列表
     * @param type//类型参数 0展示全部1 学习中 2 已完成 3 最近学习
     * @param page
     * @param limit
     * @param callback
     */
    public void getMyCurriculum(int type, int page, int limit, JsonCallback<MyCurriculumEntity> callback){
        OkGo.<MyCurriculumEntity>get(Constant.MY_CURRICULUM)
                .params("type",type)
                .params("page",page)
                .params("limit",limit)
                .execute(callback);
    }

}
