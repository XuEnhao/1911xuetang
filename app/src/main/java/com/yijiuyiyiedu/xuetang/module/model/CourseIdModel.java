package com.yijiuyiyiedu.xuetang.module.model;

import com.lzy.okgo.OkGo;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.CourseIdEntity;

/**
 * Created by xuenhao on 2018/6/13.
 * 已绑定课程id
 */

public class CourseIdModel {
    public void getCourse(JsonCallback<CourseIdEntity> callback){
        OkGo.<CourseIdEntity>post(Constant.CONVER_COURSE)
                .execute(callback);
    }
}
