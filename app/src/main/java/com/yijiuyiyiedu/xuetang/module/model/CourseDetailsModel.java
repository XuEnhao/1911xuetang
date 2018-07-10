package com.yijiuyiyiedu.xuetang.module.model;

import com.lzy.okgo.callback.StringCallback;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ALNAEntity;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.CollectCourseEntity;
import com.yijiuyiyiedu.xuetang.module.entity.CourseDetailsEntity;
import com.lzy.okgo.OkGo;

/**
 * Created by xuenhao on 2018/3/22.
 * 课程详情
 */

public class CourseDetailsModel {

    /**
     * @param courseId 课程id
     * @param userId   用户id  可选
     * @param callback
     */
    public void getCourseDetails(String courseId, String userId, JsonCallback<CourseDetailsEntity> callback) {
        OkGo.<CourseDetailsEntity>post(Constant.CURRICULUM_DETSILS)
                .tag(this)
                .params("id", courseId)
                .headers("Authorization",userId)
                .execute(callback);
    }

    /**
     * @param userId   用户id 必传
     * @param courseId 课程id 必传
     * @param callback
     */
    public void collectCourse(String userId, String courseId, JsonCallback<CollectCourseEntity> callback) {
        OkGo.<CollectCourseEntity>post(Constant.COLLECT_COURSE)
                .tag(this)
                .params("user_id", userId)
                .params("curriculum_id", courseId)
                .execute(callback);
    }

    /**
     * 获取投屏信息
     * @param curriculum_id
     * @param user_id
     * @param catalog_id
     * @param callback
     */
    public void getALNAData(String curriculum_id,String user_id,String catalog_id,JsonCallback<ALNAEntity> callback){
        OkGo.<ALNAEntity>post(Constant.GET_ALNA_URL)
                .tag(this)
                .params("curriculum_id",curriculum_id)
                .params("user_id",user_id)
                .params("catalog_id",catalog_id)
                .execute(callback);
    }


    /**
     * 获取视频播放信息
     *
     * @param curriculum_id
     * @param catalog_id
     * @param callback
     */
    public void getPlayModel(String curriculum_id, String catalog_id, StringCallback callback) {
        OkGo.<String>post(Constant.PLAYER_INFO)
                .tag(this)
                .params("curriculum_id", curriculum_id)
                .params("catalog_id", catalog_id)
                .execute(callback);
    }


    /**
     * 保存播放进度
     * @param curriculum_id 课程id
     * @param curriculum_catalog_id 小结id
     * @param get_current_time 观看时间
     * @param callback
     */
    public void getSaveSchedule(String curriculum_id,String curriculum_catalog_id,int get_current_time,JsonCallback<ClearHistoryEntity> callback){
        OkGo.<ClearHistoryEntity>post(Constant.SAVE_SCHEDULE)
                .params("curriculum_id",curriculum_id)
                .params("curriculum_catalog_id",curriculum_catalog_id)
                .params("get_current_time",get_current_time)
                .execute(callback);

    }

}
