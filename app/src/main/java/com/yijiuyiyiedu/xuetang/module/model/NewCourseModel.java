package com.yijiuyiyiedu.xuetang.module.model;

import com.lzy.okgo.OkGo;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.NewCourseEntity;

/**
 * Created by xuenhao on 2018/6/11.
 */

public class NewCourseModel {

    public void getNewCurriculumList(int page, int limit, JsonCallback<NewCourseEntity> callback){
        OkGo.<NewCourseEntity>get(Constant.NEW_CURRICULUM_LIST)
                .params("page",page)
                .params("limit",limit)
                .execute(callback);

    }
}
