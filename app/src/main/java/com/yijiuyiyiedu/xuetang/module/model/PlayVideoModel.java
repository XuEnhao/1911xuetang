package com.yijiuyiyiedu.xuetang.module.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.CourseDetailsEntity;

/**
 * Created by xuenhao on 2018/3/29.
 */

public class PlayVideoModel {

    /**
     * 获取视频播放信息
     *
     * @param user_id
     * @param curriculum_id
     * @param catalog_id
     * @param callback
     */
    public void getPlayModel(String user_id, String curriculum_id, String catalog_id, StringCallback callback) {
        OkGo.<String>post(Constant.PLAYER_INFO)
                .tag(this)
                .params("user_id", user_id)
                .params("curriculum_id", curriculum_id)
                .params("catalog_id", catalog_id)
                .execute(callback);
    }

    /**
     * 获取地下列表
     *
     * @param id
     * @param user_id
     * @param callback
     */
    public void getCourseDetails(String id, String user_id, JsonCallback<CourseDetailsEntity> callback) {

        OkGo.<CourseDetailsEntity>post(Constant.CURRICULUM_DETSILS)
                .tag(this)
                .params("id", id)
                .params("user_id", user_id)
                .execute(callback);
    }
}
