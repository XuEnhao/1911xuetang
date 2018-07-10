package com.yijiuyiyiedu.xuetang.module.model;

import com.lzy.okgo.OkGo;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.TeacherEntity;

/**
 * Created by xuenhao on 2018/6/11.
 */

public class TeacherModel {

    public void getTeacherList(int page, int limit, JsonCallback<TeacherEntity> callback){
        OkGo.<TeacherEntity>get(Constant.TEACHER_LIST)
                .params("page",page)
                .params("limit",limit)
                .execute(callback);
    }
}
