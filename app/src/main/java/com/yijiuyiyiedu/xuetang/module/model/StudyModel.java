package com.yijiuyiyiedu.xuetang.module.model;

import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.StudyEntity;
import com.lzy.okgo.OkGo;

/**
 * Created by xuenhao on 2018/3/20.
 */

public class StudyModel {

    public StudyModel() {
    }

    public void getUserStudyList(String user_id, String page, String limit, JsonCallback<StudyEntity> callback) {
        OkGo.<StudyEntity>post(Constant.USER_STUDY_LIST)
                .tag(this)
                .params("user_id", user_id)
                .params("page", page)
                .params("limit", limit)
                .execute(callback);

    }
}
